package com.epam.TestAutomation.TestNG;
import static org.testng.Assert.*;
import org.testng.annotations.*;

public class TestCatalogue {
	Library lib;
	@BeforeGroups(groups = {"basicOperation","Alltests"})
	public void initLibrary(){
		lib=new Library(new Catalogue());
		lib.getCatalouge().addBook(new Book(1,"Ancient India","RC Majumdar"));
		lib.getCatalouge().addBook(new Book(2,"Pax Indica","Shashi Tharoor"));
		lib.getCatalouge().addBook(new Book(3,"My GrandMother","Sudha Murthi"));
	}
	@Test(groups = {"basicOperation","Alltests"})
	public void testCatalogueAddBook()
	{
	int initialSize = lib.getCatalouge().getCatalogueSize();
	lib.getCatalouge().addBook(new Book(4, "Mahabhartha", "VedVyas"));

	assertEquals(lib.getCatalouge().getCatalogueSize(), initialSize + 1);

	}




	@Test(groups = {"basicOperation","Alltests"})
	public void testCatalogueRemoveBook()
	{
	int initialSize = lib.getCatalouge().getCatalogueSize();
	int idToRemove = lib.getCatalouge().getBooks().get(0).getId();

	lib.getCatalouge().removeBook(idToRemove);
	assertEquals(lib.getCatalouge().getCatalogueSize(), initialSize - 1);

	}


	@Test(groups = {"basicOperation","Alltests"})
	public void testCatalogueRemoveNonExistingBook()
	{
	int initialSize = lib.getCatalouge().getCatalogueSize();

	lib.getCatalouge().removeBook(102);
	assertEquals(lib.getCatalouge().getCatalogueSize(), initialSize);

	}

	@Test(groups = {"Alltests"})
	public void testCatalogueSearchByBookName()
	{
	Book book = lib.getCatalouge().searchBook("Pax Indica");

	assertNotNull(book);

	}

	@Test(groups = {"Alltests"})
	public void testCatalogueSearchByBookNamewhichDoesNotExist()
	{
	Book book = lib.getCatalouge().searchBook("Geeta");

	assertNull(book);

	}

	@Test(groups = {"Alltests"})
	public void testCatalogueSearchByAuthorname()
	{
	Book book = lib.getCatalouge().searchBook("Shashi Tharoor");

	assertNotNull(book);

	}

	@Test(groups = {"Alltests"})
	public void testCatalogueSearchByAuthornameWhchDoesNotExist()
	{
	Book book = lib.getCatalouge().searchBook("Chetan");

	assertNull(book);

	}
	
	
	@Test(groups = {"Alltests","basicOperation"})
	public void testAddToLibraryWithSupportedObject() throws Exception
	{
		int initialSize = lib.getCatalouge().getCatalogueSize();
	//	try
		//{
			lib.getCatalouge().addToLibrary(new Book(5, "Mahabhartha", "VedVyas"));
		//}catch(Exception e)
		//{
			// not expecting any exception
		//}
		
		assertEquals(lib.getCatalouge().getCatalogueSize(), initialSize + 1);
		
	}
	
	
	@Test(groups = {"Alltests"}, expectedExceptions = LibraryObjectNotSupported.class)
	public void testAddToLibraryWithUnsupportedObject() throws Exception
	{
		lib.getCatalouge().addToLibrary("New Video");
			
	}
	
	
	@Test(groups = {"Alltests"}, dependsOnMethods = {"testCatalogueAddBook"})
	public void testARemoveNewlyAddedBook()
	{
		int initialSize = lib.getCatalouge().getCatalogueSize();
				
		lib.getCatalouge().removeBook(4);
		assertEquals(lib.getCatalouge().getCatalogueSize(), initialSize - 1);
		
	}
	
	
	@Test(groups = {"Alltests"}, dependsOnGroups = {"basicOperation"})
	public void testARemoveNewlyAddedBook2()
	{
		int initialSize = lib.getCatalouge().getCatalogueSize();
				
		lib.getCatalouge().removeBook(5);
		assertEquals(lib.getCatalouge().getCatalogueSize(), initialSize - 1);
		
	}
	
	
	@Test(groups = {"basicOperation","Alltests"}, dataProvider = "BookNames")
	public void testCatalogueAddBooks(int bookID,String bookName, String authorName)
	{
		System.out.println("Calling test add Books for : " + bookName);
		int initialSize = lib.getCatalouge().getCatalogueSize();
		lib.getCatalouge().addBook(new Book(bookID, bookName, authorName));
		
		assertEquals(lib.getCatalouge().getCatalogueSize(), initialSize + 1);
		
	}
	
	
	@Test(groups = {"basicOperation","Alltests"}, dataProvider = "BookNamesV2")
	public void testCatalogueAddBooks(Book book)
	{
		int initialSize = lib.getCatalouge().getCatalogueSize();
		lib.getCatalouge().addBook(book);
		
		assertEquals(lib.getCatalouge().getCatalogueSize(), initialSize + 1);
		
	}
	
	
	@Test(groups = {"Alltests"})
	@Parameters("searchStr")
	public void testCatalogueSearchByBookNamePara(String searchStr)
	{
		Book book = lib.getCatalouge().searchBook(searchStr);
		assertNotNull(book);
	
	}
	
	
	@DataProvider(name = "BookNames")
	public static Object[][] bookNames()
	{
		return new Object[][] {
			{6,"Harry Potter","J K Rowling"},
			{7,"wings of Fire","APJ Kalam"},
			{8,"Believe","Suresh Raina"},
			{9,"Psycology of Money","Morgan"},
			{10,"Rich Dad Poor Dad","Robert"},
			{11,"Zero to One","Peter"},
			{12,"Arthshashtra","Kautilaya"},
			{13,"כנפי אש","קאוטיליה"},
			{14,"カウティラヤ","カウティラヤ"},
			{15,"考提拉亞","考提拉亞"}
			
		};
	}
	
	
	@DataProvider(name = "BookNamesV2")
	public static Object[][] bookNamesVer2()
	{
		return new Object[][] {{
			new Book(7,"wings of Fire","APJ Kalam")
		}
		};
	}

}
