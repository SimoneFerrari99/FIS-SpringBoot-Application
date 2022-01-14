package MentcareApplication.ControllersTest.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MedicPage extends PageObject{

    @FindBy(xpath="//body[1]/div[2]/div[1]/div[1]")
    private WebElement confirmationHeader;

    @FindBy(xpath="//body/div[2]/div[4]/div[1]/div[1]/div[1]/div[2]/form[1]/button[1]")
    private WebElement newAppointmentButton;

    public MedicPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationHeader() {
        return confirmationHeader.getText();
    }

    public FormAppointmentPage navigateToNewAppointmentPage(){
        newAppointmentButton.click();
        return new FormAppointmentPage(driver);
    }
}