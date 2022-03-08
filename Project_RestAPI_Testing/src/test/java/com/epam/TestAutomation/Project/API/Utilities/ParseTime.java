package com.epam.TestAutomation.Project.API.Utilities;

public class ParseTime {

	public static int convertIntoMinutes(String string) {
		// TODO Auto-generated method stub
		String arr[]=string.split(" ");
		String hours=arr[0].substring(0, arr[0].length()-1);
		String minutes=arr[1].substring(0, arr[1].length()-1);
		int h=Integer.parseInt(hours);
		int m=Integer.parseInt(minutes);
		return m+(h*60);
	}

	public static int getDuration(String string, String string2) {
		// TODO Auto-generated method stub
		String arr[]=string.split(":");
		String arr1[]=string2.split(":");
		int m1=Integer.parseInt(arr[0]);
		int m2=Integer.parseInt(arr[1]);
		int total1=(m1*60)+m2;
		m1=Integer.parseInt(arr1[0]);
	    m2=Integer.parseInt(arr1[1]);
	    int total2=(m1*60)+m2;
		return Math.abs(total1-total2);
	}

}
