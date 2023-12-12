
package com.ios.demo;
/* 
* import java.util.Arrays; import java.util.function.BiFunction;
* 
* public class Demo {
* 
* public static int add(int a,int b) { return a+b; }
* 
* public int sub(int a,int b) { return a-b; }
* 
* public static void main(String[] args) {
* 
* BiFunction<Integer, Integer, Integer> result=Demo::add; int
* addition=result.apply(10, 20); System.out.println("ADDITION="+addition);
* 
* 
* BiFunction<Integer, Integer, Integer> subResult=new Demo()::sub; int
* sub=subResult.apply(12, 2); System.out.println("SUBTRACTION="+sub);
* 
* BiFunction<Integer, Integer, Integer> mulResult=(p1,p2)->p1*p2; int
* mul=mulResult.apply(10,2); System.out.println("MULTIPLICATION="+mul);
* 
* System.out.println("***************************************");
* 
* String[] stringArray = {"aaa","zzz", "Aditya","Steve", "Rick", "Aditya",
* "Negan", "Lucy", "Sansa", "Jon"}; Method reference to an instance method of
* an arbitrary object of a particular type
* 
* 
* //Arrays.sort(stringArray, String::compareToIgnoreCase);
* Arrays.sort(stringArray, (o1,o2)->o1.compareToIgnoreCase(o2)); for(String
* str: stringArray){ System.out.println(str); }
* 
* } }
* 
* 
* 
* @FunctionalInterface interface I { void m1(); }
* 
* @FunctionalInterface interface I1 extends I{ void m1(); }
*/

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * @FunctionalInterface interface I { Hello display(String say); default void
 * m1() { System.out.println("Default..!I"); } }
 * 
 * @FunctionalInterface interface I1 { void m3(); default void m1() {
 * System.out.println("Default..!I1"); } }
 * 
 * class Hello implements I,I1{ Hello(String say) { System.out.println(say); }
 * 
 * @Override public void m1() { // TODO Auto-generated method stub I.super.m1();
 * }
 * 
 * @Override public void m3() { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override public Hello display(String say) { // TODO Auto-generated method
 * stub return null; } }
 */

public class Demo {
	/*
	 * public static void main(String[] args) { System.out.println("Welcome..!");
	 * 
	 * I i=Hello::new; i.display("Say Something...!"); }
	 */
	public static void main(String args) {
		
		List<Customer> customers = GetCustomers.getCustomers();
		
// using map method get the List<String> emails of Customers..
		//map method is used to transform the data .i.e. one to one mapping
	List<String> emails = customers.stream()
		.map(customer->customer.getEmail())
			.collect(Collectors.toList());
		System.out.println(emails);
		
// using map method get phone numbers in the list....		
		// In this scenario, using map method we are getting List<List<Integer>> phoneNumbers..
	// we are getting the phone numbers as Stream of Stream of Integer
		// so we can use flatMap() method instead of map method
		// because map() method only transform the data but
		 //flatMap() method transform the data + flattering the data
		// it returns stream of stream
		
	List<List<Integer>> phoneNumbers = customers.stream()
		.map(customer->customer.getPhoneNumbers())
		.collect(Collectors.toList());
	
	System.out.println(phoneNumbers);
		
// using flatMap() method.....
	
	List<Integer> phoneNumbersStream= customers.stream()
	.flatMap(customer->customer.getPhoneNumbers().stream()).collect(Collectors.toList());
	
	System.out.println(phoneNumbersStream);
	
//Optional Class***********
	
	String str=null;
	
	//here it return optional instance if String not null else of method throws NullPointerException
		//Optional.of(str)
		//Optional<String> opt=Optional.of(str);
		//System.out.println(opt);
		
	//return Optional instance if string is not null else it return optional empty
		//Optional.ofNullable(str)
		Optional<String> opt1=Optional.ofNullable(str);
		System.out.println(opt1);
		
	//return empty Optional (create new empty optional)	
		//Optional.empty()
		Optional<String> opt2=Optional.empty();
		System.out.println("******"+opt2);
		
	//	Optional.isPresent()
		//If a value is present, returns true otherwise false
		
		 Optional<Customer> optionalCustomer =  GetCustomers.getCustomerByEmail("@gmail.com");
		 if(optionalCustomer.isPresent())
			 System.out.println(optionalCustomer);
		 else
			 System.out.println("Not Found");
		 
	// Optional.ifPresent()
		//If a value is present, performs the given action with the value,otherwise does nothing
		 
		 Optional<Customer> optionalCustomer1 =  GetCustomers.getCustomerByEmail("@gmail.com");
		 optionalCustomer1.ifPresent(cs->System.out.println(cs.getEmail()));
		 
	// Optional.orElse
		//If a value is present, returns the value, otherwise returns other. 
		 Optional<Customer> optionalCustomer2 = 
				 Optional.ofNullable(GetCustomers.getCustomerByEmail("@gmail.com").orElse(new Customer()));
		 
	//Optional.orElseGet
		 //If a value is present, returns the value, otherwise returns the resultproduced by the supplying function
		 Optional<Customer> optionalCustomer3 =  Optional.ofNullable(GetCustomers.getCustomerByEmail("@gmail.com")
				 .orElseGet(()->new Customer()));

		//Optional.orElseThrow
		//If a value is present, returns the value, otherwise throws Customer Exception
		 
		
// Collectors Class::
		 
		 List<String> names =
		          Arrays.asList("Jon", "Ajeet", "Steve",
		             "Ajeet", "Jon", "Ajeet");

		      Map<String, Long> map =
		      names.stream().collect(
		          Collectors.groupingBy(
		             Function.identity(), Collectors.counting()
		          )
		      );

		      System.out.println(map);
		 
		 
		 
		 
	}
	
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Customer {
	private int id;
	private String name;
	private String email;
	private List<Integer> phoneNumbers;
}

class GetCustomers {
	public static List<Customer> getCustomers() {
		return Arrays.asList(new Customer(101,"Kumar","kumar@gmail.com",Arrays.asList(12345,56789))
				,new Customer(107,"Ansh","ansh@gmail.com",Arrays.asList(99999999,7777777))
				,new Customer(111,"Asad","asad@gmail.com",Arrays.asList(12121212,11111111))
				,new Customer(110,"Koushik","koushik@gmail.com",Arrays.asList(34343434,46464646))
				,new Customer(199,"Thakur","thakur@gmail.com",Arrays.asList(909090,8989898)));
	}
	
	public static Optional<Customer> getCustomerByEmail(String email) {		
		return getCustomers().stream()
				.filter(customer->customer.getEmail().equals(email)).findFirst();		 		
	}
	
	
}

