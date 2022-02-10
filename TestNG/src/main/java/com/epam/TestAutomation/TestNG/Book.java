package com.epam.TestAutomation.TestNG;

public class Book {

	private int id;
	private String nameOfTheBook;
	private String authorOfTheBook;
	
	public Book(int id,String nameOfTheBook,String authorOfTheBook) {
		this.authorOfTheBook=authorOfTheBook;
		this.id=id;
		this.nameOfTheBook=nameOfTheBook;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameOfTheBook() {
		return nameOfTheBook;
	}
	public void setNameOfTheBook(String nameOfTheBook) {
		this.nameOfTheBook = nameOfTheBook;
	}
	public String getAuthorOfTheBook() {
		return authorOfTheBook;
	}
	public void setAuthorOfTheBook(String authorOfTheBook) {
		this.authorOfTheBook = authorOfTheBook;
	}
	
	
	
	
}
