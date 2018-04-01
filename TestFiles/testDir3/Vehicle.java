package package3;


public class Vehicle{




	public interface MyInterface{}


	public enum Day implements MyInterface{
	    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
	    THURSDAY, FRIDAY, SATURDAY
	}


	Day dayVariable1;

	public static void main(String[] args) {

		Vehicle test1 = new Vehicle();


		Day dayVariable2;

		System.out.println(Day.SUNDAY);
	}

}


class Person extends Vehicle {

	}
