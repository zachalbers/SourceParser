class testClass10{					// 1 Normal Declaration
	
    private void testt(){				
        int a = 1;
  
        class Local{						// 1 Local Declaration
             
            public Inner(){

            }	
        }
         
        Local local = new Local();			// 2 Local References
	}
	testClass10 test = new testClass10()	// 2 Normal References
}

// Local Types. Declarations found: 1; references found: 2.
// Normal Types. Declarations found: 1; references found: 2.