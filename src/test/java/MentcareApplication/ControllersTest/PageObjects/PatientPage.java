package MentcareApplication.ControllersTest.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PatientPage extends PageObject{

    @FindBy(xpath="//body[1]/div[2]/div[1]/div[1]")
    private WebElement confirmationHeader;

    @FindBy(xpath="//body/div[2]/div[4]/div[1]/div[2]/form[1]/button[1]")
    private WebElement newAppointmentButton;

    @FindBy(xpath="/html[1]/body[1]/div[2]/div[4]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/a[1]")
    private WebElement appointmentDetailsButton;

    public PatientPage(WebDriver driver) {
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
        //appointmentDetailsButton.click(); // org.openqa.selenium.ElementClickInterceptedException: element click intercepted: Element is not clickable at point (675, 762)
        driver.navigate().to("http://localhost:8080/appuntamento/1");
        return new AppointmentPage(driver);
    }

}