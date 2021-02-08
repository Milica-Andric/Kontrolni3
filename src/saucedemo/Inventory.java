package saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Inventory {
	
	public static final String URL = "https://www.saucedemo.com/inventory.html";
	private static final String SORTBUTTON_XPATH = "//*[@id=\"inventory_filter_container\"]/select";
	
	public static void sortItems(WebDriver driver) {
		WebElement btn = driver.findElement(By.xpath(SORTBUTTON_XPATH));
		btn.click();
		Select sel = new Select(btn);
		sel.selectByVisibleText("Price (low to high)");
	}
	public static void sortiranje(double[]niz) {
		for (int i = 0; i<niz.length; i++) {
			for (int j = i+1 ; j<niz.length; j++) {
				if (niz[i] > niz[j]) {
				double pom = niz[i];
				niz[i] = niz[j];   
				niz[j] = pom;
				}
			}
		}
	}
	

}
