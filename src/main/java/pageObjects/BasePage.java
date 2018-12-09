package pageObjects;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.DriverManager;

public class BasePage<T> {
	protected WebDriver driver;
	protected WebDriverWait waitDriver;

	public BasePage() {
		this.driver = DriverManager.getDriver();

	}

	public T openPage(Class<T> clazz) {
		T page = null;
		try {
			driver = DriverManager.getDriver();

			page = PageFactory.initElements(driver, clazz);

		} catch (NoSuchElementException e) {

			throw new IllegalStateException(String.format("This is not the %s page", clazz.getSimpleName()));
		}
		return page;
	}

	public String getCurrentURL() {
		Set<String> winids = driver.getWindowHandles();
		Iterator<String> itr = winids.iterator();
		String windowHandle = itr.next();
		String stringURL = driver.switchTo().window(windowHandle).getCurrentUrl();
		return stringURL;
	}

}
