package MentcareApplication.ControllersTest.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppointmentPage extends PageObject{

    @FindBy(xpath="//body[1]/div[2]/div[1]/div[1]")
    private WebElement confirmationHeader;

    @FindBy(xpath="//body/div[2]/div[4]/div[1]/div[2]/a[1]")
    private WebElement addNewCommunicationButton;

    @FindBy(xpath="//body/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/a[1]")
    private WebElement deleteAppointmentButton;

    public AppointmentPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationHeader() {
        return confirmationHeader.getText();
    }

    public FormCommunicationPage navigateToNewCommunicationPage() {
        addNewCommunicationButton.click();
        return new FormCommunicationPage(driver);
    }

    public String findCommunicationByText(String text){
        return driver.findElement(By.xpath(String.format("//td[contains(text(),'%s')]", text))).getText();
    }

    public String findEditedClinic(){
        return driver.findElement(By.xpath("//body/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]")).getText();
    }

    public String findEditedAppointmentDate(){
        return driver.findElement(By.xpath("//body/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]")).getText();
    }

    public AllAppointmentsPage clickDeleteAppointmentButton() {
        deleteAppointmentButton.click();
        return new AllAppointmentsPage(driver);
    }
}