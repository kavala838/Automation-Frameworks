package com.epam.TestAutomation.TestNG;
import java.util.*;
public class Member {
	
	int MemberID;
	String FirstName, LastName;
	String City;
	int age;
	boolean Active=true;
	ArrayList<Book> issuedBookList=new ArrayList<Book>();
	
	public Member(int MemberID,String FirstName,String LastName,String City, int age) {
		this.MemberID=MemberID;
		this.LastName=LastName;
		this.City=City;
		this.setAge(age);
		this.setFirstName(FirstName);
		Members.addMember(this.MemberID, this);
	}
	
	public ArrayList<Book> getIssuedBookList() {
		return issuedBookList;
	}

	public void setIssuedBookList(ArrayList<Book> issuedBookList) {
		this.issuedBookList = issuedBookList;
	}

	public Member getMemberByID(int MemberID) {
		return this;
	}
	
	public int getMemberID() {
		return MemberID;
	}
	
	private void setMemberID(int memberID) {
		MemberID = memberID;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public void deactivateUser() {
		this.Active=false;
	}
	
	public void activateUser() {
		this.Active=true;
	}
	public boolean getStatus() {
		return this.Active;
	}
	public void issueBook(Book book) {
		this.issuedBookList.add(book);
	}
}
