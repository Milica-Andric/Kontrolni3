package testovi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import saucedemo.HomePage;
import saucedemo.Inventory;

public class Tests {
	
private static WebDriver driver;
	
	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\drive\\ChromeDriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test(priority = 1)
	public void testLoginUnsuccessful() {
			
		File f = new File("data.xlsx"); 
		try {
			InputStream inp = new FileInputStream(f); 
			XSSFWorkbook wb = new XSSFWorkbook(inp); 
			Sheet sheet = wb.getSheetAt(0); 
			
			SoftAssert sa = new SoftAssert();
			
			for(int i = 0; i <= 12 ; i++) {
				Row row = sheet.getRow(i);
				
				Cell c1 = row.getCell(0);
				Cell c2 = row.getCell(1);
			   	
				String username = c1.toString(); //ubacila sam locked out user u pogresne usere jer i jeste pogresan
				String password = c2.toString();
				
				driver.navigate().to(HomePage.URL);
				HomePage.inputUsername(driver, username);
				HomePage.inputPassword(driver, password);
				HomePage.clickLogin(driver);
				
				String actual = driver.getCurrentUrl();
				String expected = HomePage.URL;
				
				sa.assertEquals(actual, expected);
				
			}  
			sa.assertAll();
			wb.close();
			
		} catch (IOException e) {
			System.out.println("Nije pronadjen fajl!");
			e.printStackTrace();
		}
	}
	@Test(priority = 2)
	public void testLoginSuccesful() {
		File f = new File("data.xlsx"); 
		try {
			InputStream inp = new FileInputStream(f); 
			XSSFWorkbook wb = new XSSFWorkbook(inp); 
			Sheet sheet = wb.getSheetAt(0); 
			
			SoftAssert sa = new SoftAssert();
			
			for(int i = 13; i < 16 ; i++) {
				Row row = sheet.getRow(i);
				
				Cell c1 = row.getCell(0); 
				Cell c2 = row.getCell(1);  
			   	
				String username = c1.toString();
				String password = c2.toString();
				
				driver.navigate().to(HomePage.URL);
				HomePage.inputUsername(driver, username);
				HomePage.inputPassword(driver, password);
				HomePage.clickLogin(driver);
				
				String actual = driver.getCurrentUrl();
				String expected = Inventory.URL;
				
				sa.assertEquals(actual, expected);
				
			}  
			sa.assertAll();
			wb.close();
			
		} catch (IOException e) {
			System.out.println("Nije pronadjen fajl!");
			e.printStackTrace();
		}
	}
	@Test(priority = 3)
	public void testSortItems() {
		driver.navigate().to(Inventory.URL);
		
		double[] niz = {29.99, 9.99, 15.99, 49.99, 7.99, 15.99};
		Inventory.sortiranje(niz);
		Inventory.sortItems(driver);
		
		double[] expected = {7.99, 9.99, 15.99, 15.99, 29.99, 49.99};
		
		Assert.assertEquals(niz, expected);
	}

}
