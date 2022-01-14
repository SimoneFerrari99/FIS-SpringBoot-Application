package MentcareApplication.ControllersTest.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllMedicsPage extends PageObject{

    @FindBy(xpath="//body/div[2]/div[1]/div[1]")
    private WebElement confirmationHeader;

    @FindBy(xpath="//tbody/tr[1]/td[3]/a[1]")
    private WebElement moreMedicDetailsButton;

    public AllMedicsPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationHeader() {
        return confirmationHeader.getText();
    }

    public MedicPage navigateToMoreMedicDetails() {
        moreMedicDetailsButton.click();
        return new MedicPage(driver);
    }

}