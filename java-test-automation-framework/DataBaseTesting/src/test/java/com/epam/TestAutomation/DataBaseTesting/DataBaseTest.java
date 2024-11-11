package com.epam.TestAutomation.DataBaseTesting;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DataBaseTest {
	Connection cn;

	@BeforeClass
	public void setUp() throws SQLException, ClassNotFoundException {
		// Class.forName("com.mysql.cj.jdbc.Driver");
		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "A1@kavala");
	}

	@Test
	public void testTables() throws SQLException {
		Statement statement = cn.createStatement();
		String query = "show tables";
		statement.executeQuery(query);
	}
	@Test
	public void testSPCityDetails() throws SQLException {
		CallableStatement cst=cn.prepareCall("{CALL 'citydetails'}");
		ResultSet resultset=cst.executeQuery();
		Statement statement = cn.createStatement();
		
		ResultSet actualdata=statement.executeQuery("select * from city");
		assertTrue(copmareResultSet(resultset,actualdata));
	}
	
	public void testSPCityCountsByCountry() throws SQLException {
		CallableStatement cst=cn.prepareCall("{call CityCountsByCountry(?,?,?)}");
		cst.setString(1, "AGF");
		cst.registerOutParameter(2, Types.INTEGER);
		cst.registerOutParameter(3, Types.INTEGER);
		cst.executeQuery();
		
		int cityCount=cst.getInt(2);
		int countryCount=cst.getInt(3);
		Statement statement = cn.createStatement();
		ResultSet actualData=statement.executeQuery("select count(*) from city where countryCode='AFG'");
		actualData.next();
		int expectedCityCount=actualData.getInt(1);
		
		statement = cn.createStatement();
		actualData=statement.executeQuery("select count(*) from country");
		actualData.next();
		int expectedCountryCount=actualData.getInt(1);
		assertEquals(cityCount,expectedCityCount);
		assertEquals(countryCount,expectedCountryCount);
	}

	private boolean copmareResultSet(ResultSet rs1, ResultSet rs2) throws SQLException {
		try {
			while (rs1.next()) {
				rs2.next();
				int count = rs1.getMetaData().getColumnCount();
				for (int i = 1; i <= count; i++) {
					if (!rs1.getString(i).equals(rs2.getString(i))) {
						return false;
					}
				}
			}
		} catch (Exception ex) {
			return false;
		}

		return true;

	}

	@AfterClass
	public void CloseConnection() throws SQLException {
		cn.close();
	}
}
