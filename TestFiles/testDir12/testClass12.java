class testClass12								// 1 Normal Declaration
{
    public static void main(String[] args){
        testClass11 test = new testClass11(){	// 2 Normal References, 1 Anonymous Declaration
		
			public class nested1 {				// 1 Nested Declaration
				
				public class nested2 {			// 1 Nested Declaration
					nested2 test;				// 1 Nested Reference
				};
			};
			
			public void run(){
				public class Local{				// 1 Local Declaration
					
					public class nested3 {		// 1 Nested Declaration
					};
					
				};
				
				Local local = new Local();		// 2 Local References
			};
        };

    }
}

// Anonymous Types. Declarations found: 1; references found: 0.
// Local Types. Declarations found: 1; references found: 2.
// Nested Types. Declarations found: 3; references found: 1.
// Normal Types. Declarations found: 1; references found: 2.