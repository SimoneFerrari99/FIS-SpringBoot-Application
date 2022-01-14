package MentcareApplication.ControllersTest.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllAppointmentsPage extends PageObject{

    @FindBy(xpath="//body/div[2]/div[1]/div[1]/div[1]")
    private WebElement confirmationHeader;

    @FindBy(xpath="//body/div[2]/div[1]/div[1]/div[2]/a[1]")
    private WebElement newAppointmentButton;

    @FindBy(xpath="//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/button[1]")
    private WebElement fromMedicButton;


    public AllAppointmentsPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationHeader() {
        return confirmationHeader.getText();
    }

    public void clickNewAppointmentPage() {
        newAppointmentButton.click();
    }

    public FormAppointmentPage clickFromMedicButton(){
        fromMedicButton.submit();
        return new FormAppointmentPage(driver);
    }

    public String findAppointmentByClinic(String clinic){
        return driver.findElement(By.xpath(String.format("//td[contains(text(),'%s')]", clinic))).getText();
    }
}
