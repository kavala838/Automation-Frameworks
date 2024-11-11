package com.epam.TestAutomation.TestNG;

import java.util.ArrayList;

public class Admin {
	private Library library;
	
	public Admin(Library library) {
		this.library=library;
	}
	//public Library getLibrary() {
	//	return this.library;
	//}
	
	public void addNewMember(Member member) {
		this.library.addNewMember(member);
	}
	
	
	public void issueBookToMember(int MemberID, int BookID)throws UserInactiveException,BookNotFoundException,MemberNotFoundException  {
		Member member=library.searchMemberByID(MemberID);
		if(member==null)
			throw new MemberNotFoundException();
		if(member.getStatus()==false) {
			throw new UserInactiveException();	
		}
		Book book=library.getCatalouge().searchBookByID(BookID);
		if(book==null)
			throw new BookNotFoundException();
		member.issueBook(book);
	}
	
	
	
}
