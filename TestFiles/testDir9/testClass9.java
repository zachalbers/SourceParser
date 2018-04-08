public class testClass9 {						// 1 Normal Declaration
	
	testClass9 anonymous = new testClass9(){	// 2 Normal Reference, 1 Anonymous Declaration
	
		public class nested1 {					// 1 Nested Declaration
				
		};
		
	};
				
	public class nested2 {						// 1 Nested Declaration
			
					
		public class nested3 {					// 1 Nested Declaration
			nested3 test;						// 1 Nested Reference	
					
		}
	}
	
}

// Anonymous Types. Declarations found: 1; references found: 0.
// Nested Types. Declarations found: 3; references found: 1.
// Normal Types. Declarations found: 1; references found: 2.