package MentcareApplication.ControllersTest.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject{

    @FindBy(xpath="//b[contains(text(),'APPUNTAMENTI DI OGGI')]")
    private WebElement confirmationHeader;

    @FindBy(xpath="//tbody/tr[1]/td[4]/a[1]")
    private WebElement moreAppointmentDetailsButton;

    @FindBy(xpath="//tbody/tr[1]/td[4]/a[2]")
    private WebElement editAppointmentDetailsButton;

    @FindBy(xpath="//body/div[2]/div[1]/div[1]/div[2]/a[1]")
    private WebElement allAppointmentsButton;

    @FindBy(xpath="//tbody/tr[1]/td[3]/a[1]")
    private WebElement acceptNewRequestButton;

    @FindBy(xpath="//tbody/tr[1]/td[3]/a[2]")
    private WebElement rejectNewRequestButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationHeader() {
        return confirmationHeader.getText();
    }

    public AppointmentPage navigateToMoreAppointmentDetails() {
        moreAppointmentDetailsButton.click();
        return new AppointmentPage(driver);
    }

    public FormAppointmentPage navigateToEditAppointmentDetails() {
        editAppointmentDetailsButton.click();
        return new FormAppointmentPage(driver);
    }

    public AllAppointmentsPage navigateToAllAppointments() {
        //allAppointmentsButton.click();
        driver.navigate().to("http://localhost:8080/appuntamenti/");
        return new AllAppointmentsPage(driver);
    }

    public FormPatientPage acceptNewRequest(){
        acceptNewRequestButton.click();
        return new FormPatientPage(driver);
    }

    public HomePage RejectNewRequest(){
        rejectNewRequestButton.click();
        return new HomePage(driver);
    }

    public WebElement findDeletedElement(String s) {
        return driver.findElement(By.xpath(s));
    }
}
