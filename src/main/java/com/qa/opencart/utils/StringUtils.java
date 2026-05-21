package com.qa.opencart.utils;

public class StringUtils {
	public static String getRandomEmail() {
		// *********By using Random class for unique email genration**********
		// Random random = new Random();
		// String email = "automationtest" + random.nextInt(1000) + "@gmail.com";

		// By using System.curretTimeMillSec()//Permanat solution
		String email = "automationtest" + System.currentTimeMillis() + "@opencart.com";
		return email;
	}


}