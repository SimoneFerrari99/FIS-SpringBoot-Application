package MentcareApplication.ControllersTest.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormPatientPage extends PageObject{

    @FindBy(xpath="//body[1]/div[2]/div[1]/div[1]")
    private WebElement confirmationHeader;

    @FindBy(xpath="//input[@id='nome']")
    private WebElement nameInput;

    @FindBy(xpath="//input[@id='cognome']")
    private WebElement lastnameInput;

    @FindBy(xpath="//input[@id='codiceFiscale']")
    private WebElement cfInput;

    @FindBy(xpath="//input[@id='residenza']")
    private WebElement residenceInput;

    @FindBy(xpath="//input[@id='categoria']")
    private WebElement problemCategoryInput;

    @FindBy(xpath="//textarea[@id='descProblematica']")
    private WebElement problemDescriptionInput;

    @FindBy(xpath="//input[@id='dataNascita']")
    private WebElement birthDateInput;

    @FindBy(xpath="//select[@id='medic']")
    private WebElement medicInput;

    @FindBy(xpath="//body/div[2]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/button[1]")
    private WebElement submitFormButton;

    public FormPatientPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationHeader() {
        return confirmationHeader.getText();
    }

    public void setNameInput(String name) {
        nameInput.sendKeys(name);
    }

    public void setLastnameInput(String lastname) {
        lastnameInput.sendKeys(lastname);
    }

    public void setCfInput(String cf) {
        cfInput.sendKeys(cf);
    }

    public void setResidenceInput(String residence) {
        residenceInput.sendKeys("Venezia");
    }

    public void setProblemCategoryInput(String problemCategory) {
        problemCategoryInput.sendKeys("Problema");
    }

    public void setProblemDescriptionInput(String problemDescription) {
        problemDescriptionInput.sendKeys("Descrizione Problema");
    }

    public void setBirthDateInput(String birthDate) {
        birthDateInput.sendKeys("09/06/1999");
    }

    public void setMedicInput(String medic) {
        medicInput.sendKeys("1");
    }

    public PatientPage submitPatientFormRedirectToPatientPage(){
        submitFormButton.click();
        return new PatientPage(driver);
    }

    public AllPatientsPage submitPatientFormRedirectToAllPatientsPage(){
        submitFormButton.click();
        return new AllPatientsPage(driver);
    }
}