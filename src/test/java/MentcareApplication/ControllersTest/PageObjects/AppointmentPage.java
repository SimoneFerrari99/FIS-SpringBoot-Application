package MentcareApplication.ControllersTest.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppointmentPage extends PageObject{

    @FindBy(xpath="//body[1]/div[2]/div[1]/div[1]")
    private WebElement confirmationHeader;

    public AppointmentPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationHeader() {
        return confirmationHeader.getText();
    }

}