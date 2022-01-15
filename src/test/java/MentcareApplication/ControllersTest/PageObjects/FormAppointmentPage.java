package MentcareApplication.ControllersTest.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormAppointmentPage extends PageObject{

    @FindBy(xpath="//body[1]/div[2]/div[1]/div[1]/span[1]")
    private WebElement confirmationHeader;

    @FindBy(xpath="//select[@id='patient']")
    private WebElement patientInput;

    @FindBy(xpath="//input[@id='clinic']")
    private WebElement clinicInput;

    @FindBy(xpath="//input[@id='appointmentDate']")
    private WebElement appointmentDateInput;

    @FindBy(xpath="//body/div[2]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/button[1]")
    private WebElement submitFormButton;

    public FormAppointmentPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationHeader() {
        return confirmationHeader.getText();
    }

    public void setPatientInput(String patient) {
        patientInput.sendKeys(patient);
    }

    public void setClinicInput(String clinic) {
        clinicInput.sendKeys(clinic);
    }

    public void setAppointmentDateInput(String appointmentDate) {
        appointmentDateInput.sendKeys(appointmentDate);
    }

    public AllAppointmentsPage submitPatientFormRedirectToAllAppointmentsPage(){
        submitFormButton.submit();
        return new AllAppointmentsPage(driver);
    }

    public AppointmentPage submitPatientFormRedirectToAppointmentPage(){
        submitFormButton.submit();
        return new AppointmentPage(driver);
    }

}