package mainFiles;
import java.util.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.Collator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;


public class TypeFinder {
	
	  boolean DEBUG = false;		// Prints out additional information for debugging purposes.

	  int  referenceCount = 0;
	  int  declerationCount = 0;
	  int anonClassCount = 0;
	  boolean containsPackage = false;	// DO NOT CHANGE
	  boolean findAllTypes = false;
	  String javaType = "";
	  String directory = "";
	  public String outputString;
	  public List<String> allOutputStrings = new ArrayList<String>();
	  Map<String, List<Integer>> allTypes = new HashMap<String, List<Integer>>();
	  
	  
	  public static void main(String[] args) {
		  
		  TypeFinder finder = new TypeFinder();
		  finder.run(args);
		  finder.printAnswer();
	  }
	  
	  public void run(String[] args) {
		  

		  if (args.length == 1) {
			  directory = args[0];
			  findAllTypes = true;
		  } else if (args.length == 2) {
			  directory = args[0];	
			  javaType = args[1];	//Need to use to count which java type you want
		  } else {
			  throw new IllegalArgumentException("Incorrect number of arguments");
		  }

		  if (javaType.contains(".")) containsPackage = true;
			
		  		  
		  try {
			  parseDirectory(directory);
		  } catch (IOException e) {
			  e.printStackTrace();
			  System.exit(0);
		  	}
		  if (findAllTypes) {
			  List<String> allTypesList = new ArrayList<String>();
			  allTypesList.addAll(allTypes.keySet());
			  java.util.Collections.sort(allTypesList, Collator.getInstance());

			  for (String name: allTypesList) {
				  allOutputStrings.add(name + ". Declarations found: " + allTypes.get(name).get(0) + "; references found: " + allTypes.get(name).get(1) + ".");
			  }
			    
			  if (DEBUG) {
				  for (String output: allOutputStrings) System.out.println(output);
			  }
		  } else {
			outputString = javaType + ". Declarations found: " + declerationCount + "; references found: " + referenceCount + ".";
			if (DEBUG) System.out.println(outputString);
		  }

	  }
	  
	  public void printAnswer() {
		  if (findAllTypes) {
			  if (!DEBUG) for (String output: allOutputStrings) System.out.println(output);
		  } else {
			  if (!DEBUG) System.out.println(outputString);
		  }
	  }
	  
	  
	  public void parse(String str) {
		  
		  Map options = JavaCore.getOptions();
		  options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_5);
		  options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_5);
		  options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);

		  ASTParser parser = ASTParser.newParser(AST.JLS3);
		  parser.setCompilerOptions(options);
		  parser.setSource(str.toCharArray());
		  parser.setKind(ASTParser.K_COMPILATION_UNIT);
		  parser.setResolveBindings(true);
		  parser.setBindingsRecovery(true);
		  parser.setEnvironment(null, null, null, true);
		  parser.setUnitName("UnitName.java");
				 
			 
		  final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		  
		  if (findAllTypes) visitAllTypes(cu);
		  else visitOneType(cu);
	 
		  
		}
	  
	  public void visitOneType(CompilationUnit cu) {
		  cu.accept(new ASTVisitor() {
				 
			  
				public boolean visit(TypeDeclaration node) {
					String name = node.getName().getFullyQualifiedName();
					ITypeBinding nodeBinding = node.resolveBinding();
					
					if (containsPackage) {
							if (nodeBinding.getTypeDeclaration() != null) {
								name = nodeBinding.getTypeDeclaration().getQualifiedName();
								if (name.equals("")) name = node.getName().getFullyQualifiedName();
							} else if (nodeBinding.getPackage() != null) {
								name = nodeBinding.getPackage().getName() + "." + name;
							}
						
					}
						
					if (javaType.equals(name)) declerationCount++;
					if (DEBUG) System.out.println("Declaration: " +name);
			

					if (node.getSuperclassType() != null) {
						if (containsPackage) {
							ITypeBinding superNodeBinding = node.getSuperclassType().resolveBinding();
							if (superNodeBinding.getPackage() != null) {
								String superClassName = superNodeBinding.getPackage().getName() + "." + node.getSuperclassType();
								if (javaType.equals(superClassName)) referenceCount++;
							}
						} else {
							if (javaType.equals(node.getSuperclassType().toString())) referenceCount++;
						}
						if (DEBUG) System.out.println("This class extends " + node.getSuperclassType());
					}
	
					if (nodeBinding.getInterfaces() != null) {
						ITypeBinding[] interfaces = nodeBinding.getInterfaces();
						if (containsPackage) {
							for (ITypeBinding i : interfaces) {
								if (javaType.equals(i.getQualifiedName())) referenceCount++;
								if (DEBUG) System.out.println("implements Reference: " + i.getQualifiedName());
							}
						} else {
							for (ITypeBinding i : interfaces) {
								if (javaType.equals(i.getName())) referenceCount++;
								if (DEBUG) System.out.println("implements Reference: " + i.getName());
							}
						}
					}

					return super.visit(node); 
				}
				
		
				public boolean visit(VariableDeclarationFragment node) {
					String name;

					if (containsPackage) {
						name = node.resolveBinding().getType().getQualifiedName();
					} else {
						name = node.resolveBinding().getType().getName();

						
					}
			
					if (javaType.equals(name)) referenceCount++;
					if (DEBUG) System.out.println("Variable Reference: " + name);
	
					return super.visit(node);
				}
				
				public boolean visit(ParameterizedType node) {
					List<Type> args = node.typeArguments();
					String name;
					for (Type t : args) {
						ITypeBinding itb = (ITypeBinding) t.resolveBinding();
						if (containsPackage) {
							name = itb.getQualifiedName(); 	
						} else {
							name = itb.getName();
						}
						if (javaType.equals(name)) referenceCount++;
						
						
					}
					return super.visit(node);
				}
				
				public boolean visit(ImportDeclaration node) {
					String name = node.getName().toString();
					String[] importParts = name.split("\\.");
					String[] typeParts = javaType.split("\\.");
					
						boolean match = true;
						for (int i = 0; i < typeParts.length; i ++) {
							if ((typeParts.length - i > 0 && importParts.length - i > 0)
									&& !(typeParts[typeParts.length - (1 + i)].equals(importParts[importParts.length - (1 + i)]))) 
									{ match = false;}
						}
						if (match) referenceCount++;

					return super.visit(node);
				}
				
					
				public boolean visit(MethodDeclaration node) {
					

					String name;
					IMethodBinding imb = node.resolveBinding();

					
					if (node.isConstructor()) {
						if (containsPackage) {
							name = imb.getDeclaringClass().getQualifiedName();
							if (equalsType(name)) referenceCount++;
							if (DEBUG) System.out.println("Constructor Reference: " + name);
						} else {
							if (equalsType(node.getName().getFullyQualifiedName())) referenceCount++;
							if (DEBUG) System.out.println("Constructor Reference: " + node.getName().getFullyQualifiedName());
						}
					}
					
				
					

					if (containsPackage) {
						name = imb.getReturnType().getQualifiedName();
						if (equalsType(name)) referenceCount ++;
						if (DEBUG) System.out.println("Method Return Type Reference: " + name);
					}
					else {

						name = imb.getReturnType().getName();
						if (equalsType(name)) referenceCount ++;
						if (DEBUG) System.out.println("Method Return Type Reference: " + name);

					}
				
					for (Object o : node.parameters()) {
						SingleVariableDeclaration svd = (SingleVariableDeclaration) o;
						if (containsPackage) {
							IVariableBinding nodeBinding = svd.resolveBinding();
							name = nodeBinding.getType().getQualifiedName();
							if (equalsType(name)) referenceCount++;
							if (DEBUG) System.out.println("Parameter Variable Reference: " + name);					
						} else {
							name = svd.getType().toString();
							if (equalsType(name)) referenceCount++;
							if (DEBUG) System.out.println("Parameter Variable Reference: " + name);						
						}
					}
					
					List exceptions = node.thrownExceptions();
					
					for (Object e : exceptions) {
						String exceptionName;
						SimpleName svd = (SimpleName) e;

						if (containsPackage) {
							exceptionName = svd.resolveTypeBinding().getQualifiedName();	
						} else {
							exceptionName = svd.resolveTypeBinding().getName();		
						}
						if (equalsType(exceptionName)) referenceCount ++;			
						if (DEBUG) System.out.println("Exeption Reference Reference: " + name);
					}
					

					return super.visit(node);
				
				
				}
				
						
				public boolean visit(ClassInstanceCreation node) {
					String name;
				
					if (containsPackage) {
						name = node.resolveTypeBinding().getQualifiedName();			
					} else {
						name = node.getType().toString();
					}

					if (equalsType(name)) referenceCount++;
					if (DEBUG) System.out.println("Instance Variable Reference: " + name);
					
					return true; // do not continue 
			}

				
				public boolean visit(AnnotationTypeDeclaration node) {
					String name;
					
					if (containsPackage) {
						name = node.resolveBinding().getQualifiedName();		
					} else {
						name = node.getName().getFullyQualifiedName();
					}
					
					if (equalsType(name)) declerationCount++;	
					if (DEBUG) System.out.println("Declaration: " + name);
					
					return false; // do not continue 
				}
				
				
				public boolean visit(EnumDeclaration node) {
					String name;
					if (containsPackage) {
						name = node.resolveBinding().getQualifiedName();		
					} else {
						name = node.getName().getFullyQualifiedName();
					}
					
					if (equalsType(name)) declerationCount++;
					if (DEBUG) System.out.println("Declaration: " + name);

					
					ITypeBinding nodeBinding = node.resolveBinding();
					if (nodeBinding.getInterfaces() != null) {
						ITypeBinding[] interfaces = nodeBinding.getInterfaces();
						if (containsPackage) {
							for (ITypeBinding i : interfaces) {
								if (equalsType(i.getQualifiedName())) referenceCount++;
								if (DEBUG) System.out.println("implements Reference: " + i.getQualifiedName());
							}
						} else {
							for (ITypeBinding i : interfaces) {
								if (equalsType(i.getName())) referenceCount++;
								if (DEBUG) System.out.println("implements Reference: " + i.getName());
							}
						}
					}

					return false; // do not continue 
				}
				
				
				public boolean visit(CatchClause node) {
					String name;
					ITypeBinding nodeBinding = node.getException().getType().resolveBinding();
					
					if (nodeBinding != null) {
						if (containsPackage) {
							
							name = nodeBinding.getQualifiedName();
							
							if (equalsType(name)) referenceCount++;
						} else {
							name = nodeBinding.getName();
							if (equalsType(name)) referenceCount++;
						}
						if (DEBUG) System.out.println("Reference: "+ name);
					}
					return false;
				}
				
				

			});
	  }
	  
	  
	  public void visitAllTypes(CompilationUnit cu) {
		  cu.accept(new ASTVisitor() {
				 
			  	
				public boolean visit(TypeDeclaration node) {
					String name = node.getName().getFullyQualifiedName();

					
					ITypeBinding nodeBinding = node.resolveBinding();
					

					if (nodeBinding.getTypeDeclaration() != null) {
						name = nodeBinding.getTypeDeclaration().getQualifiedName();
						if (name.equals("")) name = node.getName().getFullyQualifiedName();
					} else if (nodeBinding.getPackage() != null) {
						name = nodeBinding.getPackage().getName() + "." + name;
					}
								
					addToCount(name, 1, 0);
					if (DEBUG) System.out.println("Declaration: " +name);
			

					if (node.getSuperclassType() != null) {
							ITypeBinding superNodeBinding = node.getSuperclassType().resolveBinding();
							if (superNodeBinding.getPackage() != null) {
								String superClassName = superNodeBinding.getPackage().getName() + "." + node.getSuperclassType();
								addToCount(superClassName, 0, 1);
							}

						if (DEBUG) System.out.println("This class extends " + node.getSuperclassType());
					}
	
					if (nodeBinding.getInterfaces() != null) {
						ITypeBinding[] interfaces = nodeBinding.getInterfaces();
							for (ITypeBinding i : interfaces) {
								addToCount(i.getQualifiedName(), 0, 1);
								if (DEBUG) System.out.println("implements Reference: " + i.getQualifiedName());
							}
					}

					return super.visit(node); 
				}
				
		
				public boolean visit(VariableDeclarationFragment node) {
					String name;
					name = node.resolveBinding().getType().getQualifiedName();
					if (name.equals("")) name = node.resolveBinding().getType().getName();
					addToCount(name, 0, 1);
					if (DEBUG) System.out.println("Variable Reference: " + name);
	
					return super.visit(node);
				}
				
				
				public boolean visit(ImportDeclaration node) {
					String name = node.getName().toString();
					addToCount(name, 0, 1);


					return super.visit(node);
				}
				
					
				public boolean visit(MethodDeclaration node) {
					

					String name;
					IMethodBinding imb = node.resolveBinding();

					
					if (node.isConstructor()) {
							name = imb.getDeclaringClass().getQualifiedName();
							if (name.equals("")) name = imb.getDeclaringClass().getName();
							addToCount(name, 0, 1);
							if (DEBUG) System.out.println("Constructor Reference: " + name);
					}
					
				
					
					name = imb.getReturnType().getQualifiedName();
					if (!name.equals("void")) {
						if (name.equals("")) imb.getReturnType().getName();
						addToCount(name, 0, 1);
						if (DEBUG) System.out.println("Method Return Type Reference: " + name);
					}
				
					for (Object o : node.parameters()) {
						SingleVariableDeclaration svd = (SingleVariableDeclaration) o;
						IVariableBinding nodeBinding = svd.resolveBinding();
						name = nodeBinding.getType().getQualifiedName();
						if (name.equals("")) nodeBinding.getType().getName();
						addToCount(name, 0, 1);
						if (DEBUG) System.out.println("Parameter Variable Reference: " + name);					

					}
					

					List exceptions = node.thrownExceptions();
					
					for (Object e : exceptions) {
						String exceptionName;
						SimpleName svd = (SimpleName) e;				
						exceptionName = svd.resolveTypeBinding().getQualifiedName();	
						addToCount(exceptionName, 0, 1);			
						if (DEBUG) System.out.println("Exeption Reference Reference: " + name);
					}
					return super.visit(node);
				}
				
						
				public boolean visit(ClassInstanceCreation node) {
					String name;
				
					name = node.resolveTypeBinding().getQualifiedName();	
					if (name.equals("")) name = node.getType().resolveBinding().getQualifiedName();
					if (name.equals("")) name = node.resolveTypeBinding().getName();



					addToCount(name, 0, 1);
					if (DEBUG) System.out.println("Instance Variable Reference: " + name);
					
					return true; // do not continue 
			}

				
				public boolean visit(AnnotationTypeDeclaration node) {
					String name;
					
					name = node.resolveBinding().getQualifiedName();		
	
					addToCount(name, 1, 0);	
					if (DEBUG) System.out.println("Declaration: " + name);
					
					return false; // do not continue 
				}
				
				public boolean visit(ParameterizedType node) {
					List<Type> args = node.typeArguments();
					String name;
					for (Type t : args) {
						ITypeBinding itb = (ITypeBinding) t.resolveBinding();
						name = itb.getQualifiedName();
						addToCount(name, 0, 1);
					}
					return super.visit(node);
				}
				
				public boolean visit(EnumDeclaration node) {
					String name;

					name = node.resolveBinding().getQualifiedName();		
	
					addToCount(name, 1, 0);
					if (DEBUG) System.out.println("Declaration: " + name);

					
					ITypeBinding nodeBinding = node.resolveBinding();
					if (nodeBinding.getInterfaces() != null) {
						ITypeBinding[] interfaces = nodeBinding.getInterfaces();
						for (ITypeBinding i : interfaces) {
						addToCount(i.getQualifiedName(), 0, 1);

						if (DEBUG) System.out.println("Implements Reference: " + i.getQualifiedName());
						}

					}

					return false; // do not continue 
				}
				
				
				public boolean visit(CatchClause node) {
					String name;
					ITypeBinding nodeBinding = node.getException().getType().resolveBinding();
					
					if (nodeBinding != null) {
						
						name = nodeBinding.getQualifiedName();
						addToCount(name, 0, 1);

						if (DEBUG) System.out.println("Reference: "+ name);
					}
					return false;
				}
				
				public boolean visit(AnonymousClassDeclaration node) {		
					String name;
					
					name = "AnonymousClass" + anonClassCount;
					anonClassCount++;
				    addToCount(name, 1, 0);

				    return true;
				}

				
				
				
				

			});
	  }
	  
	  public void addToCount(String typeName, int addDec, int addRef) {
		  
		  String[] parts = typeName.split("<");
		  typeName = parts[0];
		  
		  List<Integer> currentCount = allTypes.get(typeName);
		  if (currentCount  == null) {
			  allTypes.put(typeName, Arrays.asList(addDec, addRef));
		  } else {
			  allTypes.put(typeName, Arrays.asList(currentCount.get(0) + addDec, currentCount.get(1) + addRef));
		  }
		  
		  if (typeName.endsWith("[]")) addToCount (typeName.substring(0, typeName.length()-2), addDec, addRef);
	  }
	  
	  public boolean equalsType(String nameBeingTested) {

		  if (javaType.equals(nameBeingTested)) return true;
		  if (nameBeingTested.length() > 2) {
			  if ( javaType.equals( nameBeingTested.substring(0, nameBeingTested.length()-2) ) ) return true;
		  }
		  return false;
	  }
	  
	  
  
	  public String readFile(String filePath) throws IOException {
			StringBuilder fileData = new StringBuilder(1000);
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			
			int numRead = 0; 
			char[] buffer = new char[10];

			while ((numRead = reader.read(buffer)) != -1) {
				String readData = String.valueOf(buffer, 0, numRead);
				fileData.append(readData);
				buffer = new char[1024];
			}
	 
			reader.close();
			return  fileData.toString();  
	}
	  
	  
	  
	
	  
	  
	  public void parseDirectory(String filePath) throws IOException {
		  
		  	if (filePath.endsWith(".jar")) parseJarFile(filePath);
		  	else {
			File directory = new File(filePath);	  
			File[] files = directory.listFiles(); 
			
			
			if (files == null) throw new NullPointerException("Directory '" + directory +"' does not exist.");

			  for (File i: files) {

				  String currentFilePath = i.getAbsolutePath();
				  
				  if (i.getName().endsWith(".jar")) {
					  
					  parseJarFile(i.getAbsolutePath());
				  }
				  if (i.getName().endsWith(".java")) parse(readFile(currentFilePath));
				  if (i.isDirectory()) parseDirectory(i.getAbsolutePath());
			  	}	
		  	}
	  	}
	  
	  
	  public void parseJarFile(String fileName) throws IOException {
	  ZipFile zipFile = new ZipFile(fileName);
	  Enumeration<? extends ZipEntry> entries = zipFile.entries();

	  while (entries.hasMoreElements()) {
          ZipEntry elem = entries.nextElement();    

          if (elem.getName().endsWith(".java") || elem.getName().endsWith(".jar")) {
        	  	
        	  	InputStream classIs = zipFile.getInputStream(elem);
        	  	File f = File.createTempFile(elem.getName(), null);
        	  	f.deleteOnExit();
        	  	FileOutputStream resourceOS = new FileOutputStream(f);

        	  	InputStream classIS = zipFile.getInputStream(elem);

        	  	byte[] byteArray = new byte[1024];
        	  	int i;
        	  	while ((i = classIS.read(byteArray)) > 0) {
        	  		resourceOS.write(byteArray, 0, i);
        	  	}
        	  	classIS.close();
        	  	resourceOS.close();
        	  	
        	  	if (elem.getName().endsWith(".java")) parse(readFile(f.getAbsolutePath()));
        	  	else if (elem.getName().endsWith(".jar")) parseJarFile(f.getAbsolutePath());
          }   

	  	}
	  	
	  	zipFile.close();
	  	
	  }
	  
	  
	  
	  
}
