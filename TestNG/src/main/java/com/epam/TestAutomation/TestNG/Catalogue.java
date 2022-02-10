package com.epam.TestAutomation.TestNG;

import java.util.ArrayList;
import java.util.Iterator;

public class Catalogue {

	private ArrayList<Book> books;

	public Catalogue() {

		this.books = new ArrayList<Book>();

	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void addBook(Book newBook) {
		this.books.add(newBook);
	}

	public void removeBook(int bookID) {
		for (Iterator iterator = books.iterator(); iterator.hasNext();) {
			Book book = (Book) iterator.next();
			if (book.getId() == bookID) {
				iterator.remove();
			}

		}
	}

	public Book searchBook(String searchString) {
		for (Iterator iterator = books.iterator(); iterator.hasNext();) {
			Book book = (Book) iterator.next();
			if (book.getNameOfTheBook().contains(searchString) || book.getAuthorOfTheBook().contains(searchString)) {
				return book;
			}

		}
		return null;
	}
	
	public Book searchBookByID(int ID) {
		for (Iterator iterator= books.iterator(); iterator.hasNext();) {
			Book book = (Book) iterator.next();
			if (book.getId()==ID) {
				return book;
			}

		}
		return null;
	}

	public int getCatalogueSize() {
		return books.size();
	}

	public void addToLibrary(Object obj) throws Exception {
		if (obj instanceof Book) {
			addBook((Book) obj);
		} else {
			throw new LibraryObjectNotSupported();
		}
	}

}
