package com.epam.TestAutomation.TestNG;
import static org.testng.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.*;

public class TestLibraryClass {
	Library lib;
	
	
	@BeforeGroups(groups= "AllTests")
	public void InitLibrary() {
		lib=new Library(new Catalogue());
		lib.setAdmin(new Admin(lib));
		//lib.setMembers(new ArrayList<Member>());
	}
	
	
	
	@Test(groups= {"BasicTests","AllTests"},dataProvider= "MemberData")
	public void testAddNewMember(String ID, String FirstName,String LastName,String City,String age) {
		int pre=lib.getMembers().size();
		lib.addNewMember(new Member(Integer.parseInt(ID),FirstName,LastName,City,Integer.parseInt(age)));
		assertEquals(lib.getMembers().size(), pre+1);
	}
	
	 
	 
	 @Test(groups= { "SecondaryTests","AllTests"},dataProvider="MemberData",dependsOnGroups="BasicTests")
	 public void testIssueBook(String ID, String FirstName,String LastName,String City,String age) throws UserInactiveException,BookNotFoundException,MemberNotFoundException{
		 lib.getCatalouge().addBook(new Book(1,"Ancient India","RC Majumdar"));
		 
		 lib.getAdmin().issueBookToMember(Integer.parseInt(ID), 1);
		 
		ArrayList<Book> books=lib.searchMemberByID(Integer.parseInt(ID)).getIssuedBookList();
		boolean flag=false;
		for(Book b:books) {
			if(b.getId()==1) {
				//assert(true);
				flag=true;
			}
		}
			assert(flag);
	 }
	 @Test(groups= {"TertiaryTests","AllTests"},expectedExceptions=UserInactiveException.class,dataProvider="MemberData",dependsOnGroups="SecondaryTests",dependsOnMethods="testBookNotFoundException")
	 public void testUserInactiveException(String ID, String FirstName,String LastName,String City,String age)throws UserInactiveException,BookNotFoundException,MemberNotFoundException {
		 int id=Integer.parseInt(ID);
		 Member member=lib.searchMemberByID(id);
		 member.deactivateUser();
		 lib.getAdmin().issueBookToMember(id, 1);
	 }
	 
	 @Test(groups= {"TertiaryTests","AllTests"},expectedExceptions=MemberNotFoundException.class,dependsOnGroups="SecondaryTests",dependsOnMethods={"testBookNotFoundException","testUserInactiveException"})
	 public void testMemberNotFoundException()throws UserInactiveException,BookNotFoundException,MemberNotFoundException {
		 lib.getAdmin().issueBookToMember(6, 1);
	 }
	 
	 
	 @Test(groups= {"TertiaryTests","AllTests"},expectedExceptions=BookNotFoundException.class,dataProvider="MemberData",dependsOnGroups="SecondaryTests")
	 public void testBookNotFoundException(String ID, String FirstName,String LastName,String City,String age)throws UserInactiveException,BookNotFoundException,MemberNotFoundException {
		 int id=Integer.parseInt(ID);
		 //Member member=lib.searchMemberByID(id);
		// member.deactivateUser();
		 lib.getAdmin().issueBookToMember(id, 2);
	 }
	 
	 
	 
	 @DataProvider(name = "MemberData")
	    public String[][] provider() throws InterruptedException
	    {
	        String[] data= null;
	        String returnObj[][] = null;
	        String csvFile = System.getProperty("user.dir")+ "/src/resources/MemberData.csv";
	        BufferedReader br = null;
	        String line = "";
	        String StringSplit = ",";
	        ArrayList<String> content = new ArrayList<String>();

	        try {
	            br = new BufferedReader(new FileReader(csvFile));
	            int datalength = 0;
	            int listsize =0;;
	            
	            while ((line = br.readLine()) != null) {
	                content.add(line);
	            }
	          System.out.println(content);
	            
	            listsize = content.size();
	           
	            returnObj = new String[listsize][5];
	            
	            for (int i = 0; i<listsize; i++) {
	                
	                data = content.get(i).split(StringSplit);
	                returnObj[i][0]=data[0];
	                returnObj[i][1]=data[1];
	                returnObj[i][2]=data[2];
	                returnObj[i][3]=data[3];
	                returnObj[i][4]=data[4];
	            }
	            

	        } catch (Exception e) {
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (Exception e) {
	                }
	            }
	        }
	        return returnObj;

	    }
}

