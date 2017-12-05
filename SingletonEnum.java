package com.src;

enum InstanceEnum {
	INSTANCE;
	
	int i;
	
	public void show(){
		
		System.out.println("Value of variable i :"+i);
	}
}


public class SingletonEnum {
	
	public static void main(String[] args) {

		InstanceEnum obj1 = InstanceEnum.INSTANCE;
		obj1.i = 10;
		obj1.show();
		
		InstanceEnum obj2 = InstanceEnum.INSTANCE;
		obj2.i = 11;
		
		obj1.show();

		
	}
	
}