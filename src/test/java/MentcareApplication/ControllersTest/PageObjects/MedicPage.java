package MentcareApplication.ControllersTest.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MedicPage extends PageObject{

    @FindBy(xpath="//body[1]/div[2]/div[1]/div[1]")
    private WebElement confirmationHeader;

    @FindBy(xpath="//body/div[2]/div[4]/div[1]/div[1]/div[1]/div[2]/form[1]/button[1]")
    private WebElement newAppointmentButton;

    @FindBy(xpath="//body[1]/div[2]/div[4]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[3]/a[1]")
    private WebElement appointmentDetailsButton;

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

    public AppointmentPage navigateToAppointmentDetailsPage(){
        appointmentDetailsButton.click();
        return new AppointmentPage(driver);
    }
}