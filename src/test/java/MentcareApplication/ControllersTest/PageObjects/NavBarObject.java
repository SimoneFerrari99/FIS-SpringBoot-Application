package MentcareApplication.ControllersTest.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavBarObject extends PageObject{

    @FindBy(xpath="//a[contains(text(),'Medici')]")
    private WebElement medics;

    @FindBy(xpath="//a[contains(text(),'Pazienti')]")
    private WebElement patients;

    @FindBy(xpath="//a[contains(text(),'Appuntamenti')]")
    private WebElement appointments;

    public NavBarObject(WebDriver driver) {
        super(driver);
    }

    public AllPatientsPage navigateToPatientsPage(){
        //patients.click(); //Per qualche ragione a cui non ho trovato risposta, il webElement non risutla cliccabile. "org.openqa.selenium.ElementNotInteractableException: element not interactable"
        driver.navigate().to("http://localhost:8080/pazienti/");
        return new AllPatientsPage(driver);
    }

    public AllMedicsPage navigateToMedicsPage(){
        //medics.click(); // stessa problematica di Patients
        driver.navigate().to("http://localhost:8080/medici/");

        return new AllMedicsPage(driver);
    }

    public AllAppointmentsPage navigateToAppointmentsPage(){
        //appointments.click(); //stessa problematica di Patients
        driver.navigate().to("http://localhost:8080/appuntamenti/");
        return new AllAppointmentsPage(driver);
    }

}
