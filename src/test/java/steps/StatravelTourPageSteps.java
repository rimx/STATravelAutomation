package steps;

import org.testng.Assert;

import com.aventstack.extentreports.Status;

import ExtentListeners.ExtentManager;
import ExtentListeners.ExtentTestManager;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.StaTravelTourPage;

public class StatravelTourPageSteps extends BaseSteps {

	StaTravelTourPage staTravelTourPage;

	protected Scenario scenario;
	static String scenarioName;

	public void StatravelTourPageSteps(StaTravelTourPage staTravelTourPage) {
		this.staTravelTourPage = staTravelTourPage;
	}

	@Before
	public synchronized void before(Scenario scenario) {

		this.scenario = scenario;
		scenarioName = scenario.getName();
		ExtentTestManager.startTest("Scenario: " + scenario.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Scenario started : - " + scenario.getName());

		setUpFramework();

	}

	@After
	public void after(Scenario scenario) {

		if (scenario.isFailed()) {

			ExtentTestManager.logFail("Scenario Failed");
			ExtentTestManager.addScreenShotsOnFailure();
		} else {

			ExtentTestManager.scenarioPass();
		}

		ExtentManager.getReporter().flush();

		quit();

	}

	@Given("^launch browser '(.*?)'$")
	public void launch_browser(String browserName) throws Throwable {

		openBrowser(browserName);
		ExtentTestManager.logInfo("Browser open " +browserName );
	}

	@When("^I am on \"([^\"]*)\"$")
	public void i_am_on(String url) throws Throwable {

		staTravelTourPage = new StaTravelTourPage().open(url);
		staTravelTourPage.acceptCookies();
		ExtentTestManager.logInfo("The Url is: " + url + "\n The Title of the page is: " + staTravelTourPage.getPageTitle().toString() );

		Assert.assertEquals(staTravelTourPage.getPageTitle().toString(), "Worldwide Tours | STA Travel");
		Assert.assertTrue(staTravelTourPage.isDestinationDisplayed());

	}

	@When("^I select country '(.*?)' from the AJAX$")
	public void i_select_country_from_the_AJAX(String country) throws Throwable {

		staTravelTourPage.enterDestination(country);
		staTravelTourPage.selectAutoSuggestOption(country);// can be written in
															// a separate step
															// for multiple test
		ExtentTestManager.logInfo("The selected Country is: " +country);
		String destinationClass = staTravelTourPage.getDestinationClassName();
		Assert.assertEquals(destinationClass,
				"sta-tour-search-text sta-ui-autocomplete-input sta-adventure-link sta-non-touch ui-autocomplete-input");
		Assert.assertTrue(staTravelTourPage.isFindTourEnabled());

	}

	@When("^I click on Find a Tour button$")
	public void i_click_on_Find_a_Tour_button() throws Throwable {
		staTravelTourPage.findTour();
        
		ExtentTestManager.logInfo("The Current Url is: " + staTravelTourPage.getCurrentUrl());
		String currentUrl = staTravelTourPage.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("https://tours.statravel.co.uk/search?Query=countries"));

	}

	@When("^I change the sort order on the search results to '(.*?)'$")
	public void i_change_the_sort_order_on_the_search_results_to_Price_Low_High(String sortBy) throws Throwable {

		staTravelTourPage.selectSortBy(sortBy);
		ExtentTestManager.logInfo("Search is sort by: " + sortBy);
		Assert.assertEquals(staTravelTourPage.getSortByText(), sortBy);
	}

	@When("^I click on View More button on the page to display more or all results$")
	public void i_click_on_View_More_button_on_the_page_to_display_more_or_all_results() throws Throwable {
		staTravelTourPage.clickOnViewMore();
		Assert.assertFalse(staTravelTourPage.isViewMoreDisplayed());
	}

	@Then("^I verify if the (\\d+) matches found text is equal to the actual number of results displayed$")
	public void i_verify_if_the_matches_found_text_is_equal_to_the_actual_number_of_results_displayed(int matchFound)
			throws Throwable {
		int actualCount = staTravelTourPage.countAllProduct();
		ExtentTestManager.logInfo("Total Count on search: " + actualCount);
		Assert.assertEquals(actualCount, matchFound);
	}

}
