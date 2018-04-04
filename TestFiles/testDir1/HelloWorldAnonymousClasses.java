// package somepack;
//
// class someClass {
//
//     interface someInterface {
//         public void greet();
//         public void greetSomeone(String someone);
//     }
//
//     public void sayHello() {
//
//
//         class EnglishGreeting implements someInterface {
//             String name = "world";
//             public void greet() {
//                 greetSomeone("world");
//             }
//             public void greetSomeone(String someone) {
//                 name = someone;
//                 System.out.println("Hello " + name);
//             }
//         }
//
//         someInterface englishGreeting = new EnglishGreeting();
//
//         someInterface frenchGreeting = new someInterface() {
//             String name = "tout le monde";
//             public void greet() {
//                 greetSomeone("tout le monde");
//             }
//             public void greetSomeone(String someone) {
//                 name = someone;
//                 System.out.println("Salut " + name);
//             }
//         };
//
//     }
//
// }
