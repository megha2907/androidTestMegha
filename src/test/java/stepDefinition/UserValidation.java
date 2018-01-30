package stepDefinition;

import com.pages.HomePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class UserValidation {

	HomePage homePage;

	@Given("^User validates the splash screen open for five sec$")
	public void user_validates_the_splash_screen() throws Throwable {

		homePage = new HomePage(AttachHooks.driver);
		HomePage.obj = homePage;
		homePage.validateSplashScreen();
	}

	

	@Then("^click on any data$")
	public void click_on_any_data() throws Throwable {
		homePage.clickOnData();
	}

	@Then("^User validates (.*) screen$")
	public void user_validates_detail_screen(String title) throws Throwable {
		homePage.validateScreenTitle(title);
	}

	@Then("^Image should load with detail description available on details screen$")
	public void image_should_load_with_detail_description_available_on_details_screen() throws Throwable {
		homePage.validateDetailImageData();
	}

	@Then("^User click on back button$")
	public void user_click_on_back_button() throws Throwable {
		homePage.naviagateToBackScreen();
	}

	@Then("^user Scroll down & click on last item of list$")
	public void user_Scroll_down_click_on_last_item_of_list() throws Throwable {
		homePage.clickOnLastItem();
	}

}
