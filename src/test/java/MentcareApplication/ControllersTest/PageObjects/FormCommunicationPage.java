package MentcareApplication.ControllersTest.PageObjects;

import MentcareApplication.ControllersTest.PageObjects.AllAppointmentsPage;
import MentcareApplication.ControllersTest.PageObjects.AppointmentPage;
import MentcareApplication.ControllersTest.PageObjects.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormCommunicationPage extends PageObject {

    @FindBy(xpath="//body[1]/div[2]/div[1]/div[1]")
    private WebElement confirmationHeader;

    @FindBy(xpath="//textarea[@id='testoComunicazione']")
    private WebElement textInput;

    @FindBy(xpath="//input[@id='checkMedico']")
    private WebElement medicCheckboxInput;

    @FindBy(xpath="//body/div[2]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/button[1]")
    private WebElement submitFormButton;

    public FormCommunicationPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationHeader() {
        return confirmationHeader.getText();
    }

    public void setTextinput(String text) {
        textInput.sendKeys(text);
    }

    public void setMedicCheckboxInput(String medicCheckbox) {
        medicCheckboxInput.sendKeys(medicCheckbox);
    }

    public AppointmentPage submitCommunicationForm(){
        submitFormButton.submit();
        return new AppointmentPage(driver);
    }
}
