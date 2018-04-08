class testClass11								// 1 Normal Declaration
{
    public static void main(String[] args)
    {
        testClass11 test = new testClass11(){	// 2 Normal References, 1 Anonymous Declaration
			
			public void run(){
				public class Local{				// 1 Local Declaration
					
				};
				
				Local local = new Local();		// 2 Local References
			};
        };
         

         
    }
}

// Anonymous Types. Declarations found: 1; references found: 0.
// Local Types. Declarations found: 1; references found: 2.
// Normal Types. Declarations found: 1; references found: 2.