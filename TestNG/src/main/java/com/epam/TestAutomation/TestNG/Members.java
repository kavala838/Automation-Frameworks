package com.epam.TestAutomation.TestNG;
import java.util.*;
public class Members {
	private static HashMap<Integer,Member> MembersList=new HashMap<Integer,Member>();
	private Members() {}
	static Member getMemberByID(int ID) {
		return MembersList.get(ID);
	}
	static void addMember(int ID,Member member) {
		MembersList.put(ID, member);
	}
}
