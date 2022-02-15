package com.epam.TestAutomation.TestNG;

import java.util.ArrayList;

public class Library {

	private Catalogue catalouge;
	private Admin admin;
	private ArrayList<Member> members;
	public Library(Catalogue c) {
		this.catalouge=c;
		members=new ArrayList<Member>();
	}
	
	public Catalogue getCatalouge() {
		return catalouge;
	}
	public void setCatalouge(Catalogue catalouge) {
		this.catalouge = catalouge;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public ArrayList<Member> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<Member> members) {
		this.members = members;
	}
	
	public void addNewMember(Member member) {
		members.add(member);
	}
	public Member searchMemberByID(int ID) {
		for(Member m:members) {
			if(m.getMemberID()==ID)
				return m;
		}
		return null;
	}
	
}
