package pageObjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.DriverManager;

public class StaTravelTourPage extends BasePage

{

	Actions actions = new Actions(driver);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, 20);

	@FindBy(how = How.CSS, using = "#sta-cookie-save-all-button")
	public WebElement cookiesButton;

	@FindBy(how = How.CSS, using = "div.sta-sw-landscape-row.sta-sw-tours-where>div>input[class*='sta-tour-search-text']")
	public WebElement destinationText;

	@FindBy(how = How.CSS, using = "div.sta-standardSearch.sta-qbtb_tours > form > fieldset.sta-submit.sta-contain > p > button")
	public WebElement findATourButton;

	@FindBy(how = How.CSS, using = "#sortSelect")
	public WebElement sortByDropDown;

	@FindBy(how = How.CSS, using = "#btnViewMore")
	public WebElement viewMorwButton;

	@FindBy(how = How.CSS, using = "#products .trip-img-group")
	public List<WebElement> allProductList;

	@FindBy(how = How.XPATH, using = "//ul[@id='ui-id-18']//a[@class='ui-corner-all']")
	public List<WebElement> autoPopulatedList;

	public StaTravelTourPage open(String url) {

		System.out.println("Page Opened");
		DriverManager.getDriver().navigate().to(url);
		return (StaTravelTourPage) openPage(StaTravelTourPage.class);
	}

	public void acceptCookies() {

		cookiesButton.click();
	}

	public void enterDestination(String country) throws InterruptedException {

		destinationText.sendKeys(country);

	}

	public void selectAutoSuggestOption(String selectTour) throws InterruptedException {

		List<WebElement> elements = autoPopulatedList;

		System.out.println(elements.size());
		if (elements != null) {
			for (Iterator<WebElement> w = elements.iterator(); w.hasNext();) {
				WebElement item = w.next();
				System.out.println(item.getText());

				if (item.getText().equals(selectTour)) {
					System.out.println("Trying to select: " + selectTour);

					// item.click();
					js.executeScript("arguments[0].click();", item);

					break;

				}

			}

		}
	}

	public void findTour() {
		wait.until(ExpectedConditions.elementToBeClickable(findATourButton));
		// findATourButton.click();
		js.executeScript("arguments[0].click();", findATourButton);
	}

	public void selectSortBy(String sortByText) {

		new Select(sortByDropDown).selectByVisibleText(sortByText);
	}

	public void clickOnViewMore() {
		viewMorwButton.click();
	}

	public int countAllProduct() {
		List<WebElement> elements = allProductList;
		int totalCountOfProducts = elements.size();
		System.out.println("total product" + totalCountOfProducts);
		return totalCountOfProducts;
	}

	public String getPageTitle()

	{
		System.out.println(driver.getTitle());
		return driver.getTitle();
	}

	public boolean isDestinationDisplayed() {
		System.out.println(destinationText.isDisplayed());
		return destinationText.isDisplayed();
	}

	public String getDestinationClassName() {
		System.out.println(destinationText.getAttribute("class"));
		return destinationText.getAttribute("class");
	}

	public boolean isFindTourEnabled() {
		System.out.println(findATourButton.isEnabled());
		return findATourButton.isEnabled();
	}

	public String getCurrentUrl() {
		String currentUrl = getCurrentURL();
		System.out.println(currentUrl);

		return currentUrl;
	}

	public String getSortByText() {
		Select select = new Select(sortByDropDown);
		System.out.println(select.getFirstSelectedOption().getText());

		return select.getFirstSelectedOption().getText();
	}

	public boolean isViewMoreDisplayed() {
		System.out.println(viewMorwButton.isDisplayed());
		return viewMorwButton.isDisplayed();
	}
}
