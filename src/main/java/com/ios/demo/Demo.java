
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

public class Demo {
	/*
	 * public static void main(String[] args) { System.out.println("Welcome..!");
	 * 
	 * I i=Hello::new; i.display("Say Somthing...!"); }
	 */
}

@FunctionalInterface
interface I {
	Hello display(String say);
	default void m1() {
		System.out.println("Default..!I");
	}
}

@FunctionalInterface
interface I1 {
	void m3();
	default void m1() {
		System.out.println("Default..!I1");
	}
}

class Hello implements I,I1{
	Hello(String say) {
		System.out.println(say);
	}

	@Override
	public void m1() {
		// TODO Auto-generated method stub
		I.super.m1();
	}

	@Override
	public void m3() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Hello display(String say) {
		// TODO Auto-generated method stub
		return null;
	}
}
