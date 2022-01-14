package MentcareApplication.ControllersTest.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllPatientsPage extends PageObject{

    @FindBy(xpath="//body/div[2]/div[1]/div[1]/div[1]")
    private WebElement confirmationHeader;

    @FindBy(xpath="//body/div[2]/div[1]/div[1]/div[2]/a[1]")
    private WebElement newPatientButton;

    @FindBy(xpath="//tbody/tr[1]/td[4]/a[1]")
    private WebElement morePatientDetailsButton;

    public AllPatientsPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationHeader() {
        return confirmationHeader.getText();
    }

    public FormPatientPage navigateToNewPatientPage(){
        newPatientButton.click();
        return new FormPatientPage(driver);
    }

    public PatientPage navigateToMorePatientsDetails() {
        morePatientDetailsButton.click();
        return new PatientPage(driver);
    }

    public String findPatientByCf(String cf){
        return driver.findElement(By.xpath(String.format("//th[contains(text(),'%s')]", cf))).getText();
    }
}