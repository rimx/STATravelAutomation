package runner;



import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;


	
	

	@CucumberOptions(
			features={"src/test/resources/features/staTravelTourPage.feature"},
			glue="steps",
			tags = {"@StaTourPageFeature"}
			
			)
	public class runCuke 
	{
	

		
		@Test
		public void runCukes() {
			
			new TestNGCucumberRunner(getClass()).runCukes();
			
		}
	}



