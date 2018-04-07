public class testClass6 {		//normal class dec

	testClass6 anon1 = new testClass6() { //2 normal class ref, 1 anon class dec
		
		class localClass { //nested class dec 
			localClass bill;	//nested class ref
		};
	};
	
	testClass6 anon2 = new testClass6() { //2 normal class ref, 1 anon class dec
		class localClass {		//nested class dec
			localClass bob;		//nested class ref
		};
	};
	
}


//normal class, 1 dec, 4 ref
//anon class, 2 dec, 0 ref
//nested class, 2 dec, 2 ref