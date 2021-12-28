package MentcareApplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.commons.lang3.SystemUtils;

import org.openqa.selenium.WebDriver;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MentcareApplicationTest {
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private WebDriver driver;

    @Before
    public void setUp() {
        org.openqa.selenium.chrome.ChromeOptions chrome_options = new ChromeOptions();
        chrome_options.addArguments("--headless");
        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_win32_96/chromedriver.exe").toString());
        } else if (SystemUtils.IS_OS_MAC) {
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_mac64_96/chromedriver").toString());
        } else if (SystemUtils.IS_OS_LINUX) {
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_linux64_96/chromedriver").toString());
        }
        if (driver == null)
            driver = new ChromeDriver(chrome_options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void scenario1_MoreDetils() {
        driver.get("http://localhost:8080/");

        String todayAppointments = driver.findElement(By.xpath("//b[contains(text(),'APPUNTAMENTI DI OGGI')]")).getText();
        assertEquals("APPUNTAMENTI DI OGGI", todayAppointments);

        WebElement moreDetails = driver.findElement(By.xpath("//tbody/tr[1]/td[4]/a[1]"));
        moreDetails.click();
        String title1 = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("DETTAGLI APPUNTAMENTO", title1);
    }

    @Test
    public void scenario1_EditAppointment() {
        driver.get("http://localhost:8080/");

        String todayAppointments = driver.findElement(By.xpath("//b[contains(text(),'APPUNTAMENTI DI OGGI')]")).getText();
        assertEquals("APPUNTAMENTI DI OGGI", todayAppointments);

        WebElement edit = driver.findElement(By.xpath("//tbody/tr[1]/td[4]/a[2]"));
        edit.click();
        String title = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("MODIFICA APPUNTAMENTO ID: 1", title);
    }

    @Test
    public void scenario1_AllAppoitments() {
        driver.get("http://localhost:8080/");

        String todayAppointments = driver.findElement(By.xpath("//b[contains(text(),'APPUNTAMENTI DI OGGI')]")).getText();
        assertEquals("APPUNTAMENTI DI OGGI", todayAppointments);

        WebElement all = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[2]/a[1]"));
        all.click();
        String title = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]")).getText();
        assertEquals("APPUNTAMENTI", title);
    }

    @Test
    public void scenario2_AcceptNewRequest() {
        driver.get("http://localhost:8080/");

        String todayAppointments = driver.findElement(By.xpath("//b[contains(text(),'APPUNTAMENTI DI OGGI')]")).getText();
        assertEquals("APPUNTAMENTI DI OGGI", todayAppointments);

        // Click pulsante di accettazione
        WebElement accept = driver.findElement(By.xpath("//tbody/tr[1]/td[3]/a[1]"));
        accept.click();

        String title = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("Harrison Ford", title);

        // Inserimento medico
        WebElement medic = driver.findElement(By.xpath("//select[@id='medic']"));
        medic.sendKeys("1");

        // Submit del form
        WebElement submitButton = driver.findElement(By.xpath("//body/div[2]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/button[1]"));
        submitButton.submit();

        // Controllo della pagina d'arrivo
        String patient = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("Harrison Ford", patient);

    }

    @Test
    public void scenario3_newPatient() {
        driver.get("http://localhost:8080/");

        String todayAppointments = driver.findElement(By.xpath("//b[contains(text(),'APPUNTAMENTI DI OGGI')]")).getText();
        assertEquals("APPUNTAMENTI DI OGGI", todayAppointments);

        // Click pulsante pazienti
        /*WebElement patientsButton = driver.findElement(By.xpath("//a[contains(text(),'Pazienti')]"));
        patientsButton.click();*/
        driver.navigate().to("http://localhost:8080/pazienti/");

        String patientsTitle = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]")).getText();
        assertEquals("PAZIENTI", patientsTitle);

        // Click pulsante nuovo paziente
        WebElement newPatientButton = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[2]/a[1]"));
        newPatientButton.click();

        String newPatientPageTitle = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("NUOVO PAZIENTE", newPatientPageTitle);

        // Inserimento Nome, cognome, CF, Residenza, Categoria, Problematica, Data Nascita, Medico
        WebElement name = driver.findElement(By.xpath("//input[@id='nome']"));
        name.sendKeys("Simone");

        WebElement lastname = driver.findElement(By.xpath("//input[@id='cognome']"));
        lastname.sendKeys("Ferrari");

        WebElement cf = driver.findElement(By.xpath("//input[@id='codiceFiscale']"));
        cf.sendKeys("SF99EI");

        WebElement residence = driver.findElement(By.xpath("//input[@id='residenza']"));
        residence.sendKeys("Venezia");

        WebElement problemCategory = driver.findElement(By.xpath("//input[@id='categoria']"));
        problemCategory.sendKeys("Problema");

        WebElement problemDescription = driver.findElement(By.xpath("//input[@id='categoria']"));
        problemDescription.sendKeys("Descrizione Problema");

        WebElement birthDate = driver.findElement(By.xpath("//input[@id='dataNascita']"));
        birthDate.sendKeys("09/06/1999");

        WebElement medic = driver.findElement(By.xpath("//select[@id='medic']"));
        medic.sendKeys("1");

        // Submit del form
        WebElement submitButton = driver.findElement(By.xpath("//body/div[2]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/button[1]"));
        submitButton.submit();

        // Controllo redirect alla pagina pazienti
        String patientsTitle2 = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]")).getText();
        assertEquals("PAZIENTI", patientsTitle2);

        // Controllo presenza nuovo paziente
        String patientCF = driver.findElement(By.xpath("//th[contains(text(),'SF99EI')]")).getText();
        assertEquals("SF99EI", patientCF);
    }

    @Test
    public void scenario4_newAppointmentFromAppointmentsPage() {
        driver.get("http://localhost:8080/");

        String todayAppointments = driver.findElement(By.xpath("//b[contains(text(),'APPUNTAMENTI DI OGGI')]")).getText();
        assertEquals("APPUNTAMENTI DI OGGI", todayAppointments);

        // Click pulsante appuntamenti
        /*WebElement appointmentsButton = driver.findElement(By.xpath("//a[contains(text(),'Appuntamenti')]"));
        appointmentsButton.click();*/
        driver.navigate().to("http://localhost:8080/appuntamenti/");

        // Pagina appuntamenti
        String appointentsPageTitle = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]")).getText();
        assertEquals("APPUNTAMENTI", appointentsPageTitle);

        // Click pulsante nuovo appuntamento
        WebElement newAppointmentButton = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[2]/a[1]"));
        newAppointmentButton.click();

        WebElement fromMedicButton = driver.findElement(By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/button[1]"));
        fromMedicButton.submit();

        String newAppointmentPageTitle = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("NUOVO APPUNTAMENTO", newAppointmentPageTitle);

        // Inserimento Paziente, Clinica, Data
        WebElement patient = driver.findElement(By.xpath("//select[@id='patient']"));
        patient.sendKeys("1");

        WebElement clinic = driver.findElement(By.xpath("//input[@id='clinic']"));
        clinic.sendKeys("ClinicaDiProva");

        WebElement appointmentDateForm = driver.findElement(By.xpath("//input[@id='appointmentDate']"));
        appointmentDateForm.sendKeys(dtf.format(LocalDate.now()));

        WebElement submitButton = driver.findElement(By.xpath("//body/div[2]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/button[1]"));
        submitButton.submit();

        // Controllo redirect a pagina appuntamenti e presenza nuovo appuntamento
        String appointmentsPageTitle2 = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]")).getText();
        assertEquals("APPUNTAMENTI", appointmentsPageTitle2);

        String newAppointmentByClinic = driver.findElement(By.xpath("//td[contains(text(),'ClinicaDiProva')]")).getText();
        assertEquals("ClinicaDiProva", newAppointmentByClinic);
    }

    @Test
    public void scenario4_newAppointmentFromMedicPage() {
        driver.get("http://localhost:8080/");

        String todayAppointments = driver.findElement(By.xpath("//b[contains(text(),'APPUNTAMENTI DI OGGI')]")).getText();
        assertEquals("APPUNTAMENTI DI OGGI", todayAppointments);

        // Click pulsante medici
        /*WebElement medicsButton = driver.findElement(By.xpath("//a[contains(text(),'Medici')]"));
        medicsButton.click();*/
        driver.navigate().to("http://localhost:8080/medici/");

        // Pagina medici
        String medicsPageTitle = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]")).getText();
        assertEquals("MEDICI", medicsPageTitle);

        // Click pulsante dettaglio medico
        WebElement medicDetailsButton = driver.findElement(By.xpath("//tbody/tr[1]/td[3]/a[1]"));
        medicDetailsButton.click();

        String medicPageTitle = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("Dott. BRAD PITT", medicPageTitle);

        // Click aggiunta appuntamento
        WebElement newAppointmentButton = driver.findElement(By.xpath("//body/div[2]/div[4]/div[1]/div[1]/div[1]/div[2]/form[1]/button[1]"));
        newAppointmentButton.click();

        String newAppointmentPageTitle = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("NUOVO APPUNTAMENTO", newAppointmentPageTitle);

        // Inserimento Paziente, Clinica, Data
        WebElement patient = driver.findElement(By.xpath("//select[@id='patient']"));
        patient.sendKeys("1");

        WebElement clinic = driver.findElement(By.xpath("//input[@id='clinic']"));
        clinic.sendKeys("ClinicaDiProva");

        WebElement appointmentDateForm = driver.findElement(By.xpath("//input[@id='appointmentDate']"));
        appointmentDateForm.sendKeys(dtf.format(LocalDate.now()));

        WebElement submitButton = driver.findElement(By.xpath("//body/div[2]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/button[1]"));
        submitButton.submit();

        // Controllo redirect a pagina appuntamenti e presenza nuovo appuntamento
        String appointmentsPageTitle2 = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]")).getText();
        assertEquals("APPUNTAMENTI", appointmentsPageTitle2);

        String newAppointmentByClinic = driver.findElement(By.xpath("//td[contains(text(),'ClinicaDiProva')]")).getText();
        assertEquals("ClinicaDiProva", newAppointmentByClinic);
    }

    @Test
    public void scenario4_newAppointmentFromPatientPage() {
        driver.get("http://localhost:8080/");

        String todayAppointments = driver.findElement(By.xpath("//b[contains(text(),'APPUNTAMENTI DI OGGI')]")).getText();
        assertEquals("APPUNTAMENTI DI OGGI", todayAppointments);

        // Click pulsante pazienti
        /*WebElement patientsButton = driver.findElement(By.xpath("//a[contains(text(),'Medici')]"));
        patientsButton.click();*/
        driver.navigate().to("http://localhost:8080/pazienti/");

        // Pagina pazienti
        String patientsPageTitle = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]")).getText();
        assertEquals("PAZIENTI", patientsPageTitle);

        // Click pulsante dettaglio paziente
        WebElement patientDetailsButton = driver.findElement(By.xpath("//tbody/tr[1]/td[4]/a[1]"));
        patientDetailsButton.click();

        String patientPageTitle = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("Johnny Depp", patientPageTitle);

        // Click aggiunta appuntamento
        WebElement newAppointmentButton = driver.findElement(By.xpath("//body/div[2]/div[4]/div[1]/div[2]/form[1]/button[1]"));
        newAppointmentButton.click();

        String newAppointmentPageTitle = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("NUOVO APPUNTAMENTO", newAppointmentPageTitle);

        // Inserimento Clinica, Data
        WebElement clinic = driver.findElement(By.xpath("//input[@id='clinic']"));
        clinic.sendKeys("ClinicaDiProva");

        WebElement appointmentDateForm = driver.findElement(By.xpath("//input[@id='appointmentDate']"));
        appointmentDateForm.sendKeys(dtf.format(LocalDate.now()));

        WebElement submitButton = driver.findElement(By.xpath("//body/div[2]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/button[1]"));
        submitButton.submit();

        // Controllo redirect a pagina appuntamenti e presenza nuovo appuntamento
        String appointmentsPageTitle2 = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]")).getText();
        assertEquals("APPUNTAMENTI", appointmentsPageTitle2);

        String newAppointmentByClinic = driver.findElement(By.xpath("//td[contains(text(),'ClinicaDiProva')]")).getText();
        assertEquals("ClinicaDiProva", newAppointmentByClinic);
    }

    @Test
    public void scenario5_appointmentDetailsFromHome() {
        driver.get("http://localhost:8080/");

        String todayAppointments = driver.findElement(By.xpath("//b[contains(text(),'APPUNTAMENTI DI OGGI')]")).getText();
        assertEquals("APPUNTAMENTI DI OGGI", todayAppointments);

        // Click pulsante maggiori dettagli di un appuntamento
        WebElement moreDetails = driver.findElement(By.xpath("//tbody/tr[1]/td[4]/a[1]"));
        moreDetails.click();

        String appointentPageTitle = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("DETTAGLI APPUNTAMENTO", appointentPageTitle);

    }

    @Test
    public void scenario5_appointmentDetailsFromAppointments() {
        driver.get("http://localhost:8080/");

        String todayAppointments = driver.findElement(By.xpath("//b[contains(text(),'APPUNTAMENTI DI OGGI')]")).getText();
        assertEquals("APPUNTAMENTI DI OGGI", todayAppointments);

        // Click pulsante appuntamenti
        /*WebElement appointmentsButton = driver.findElement(By.xpath("//a[contains(text(),'Appuntamenti')]"));
        appointmentsButton.click();*/
        driver.navigate().to("http://localhost:8080/appuntamenti/");

        // Pagina appuntamenti
        String appointentsPageTitle = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]")).getText();
        assertEquals("APPUNTAMENTI", appointentsPageTitle);

        // Click pulsante maggiori dettagli di un appuntamento
        WebElement moreDetails = driver.findElement(By.xpath("//tbody/tr[1]/td[4]/a[1]"));
        moreDetails.click();

        String appointentPageTitle = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("DETTAGLI APPUNTAMENTO", appointentPageTitle);

    }

    @Test
    public void scenario5_appointmentDetailsFromPatient() {
        driver.get("http://localhost:8080/");

        String todayAppointments = driver.findElement(By.xpath("//b[contains(text(),'APPUNTAMENTI DI OGGI')]")).getText();
        assertEquals("APPUNTAMENTI DI OGGI", todayAppointments);

        // Click pulsante pazienti
        /*WebElement patientsButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/div[1]/div[1]/ul[1]/li[2]"));
        patientsButton.click();*/
        driver.navigate().to("http://localhost:8080/pazienti/");

        // Pagina pazienti
        String patientsPageTitle = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]")).getText();
        assertEquals("PAZIENTI", patientsPageTitle);

        // Click pulsante maggiori dettagli di un paziente
        WebElement moreDetails = driver.findElement(By.xpath("//tbody/tr[1]/td[4]/a[1]"));
        moreDetails.click();

        String patientPageTitle = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("Johnny Depp", patientPageTitle);

        // Click pulsante maggiori dettagli di un appuntamento
        /*WebElement appointmentMoreDetails = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[4]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/a[1]"));
        appointmentMoreDetails.click();*/
        driver.navigate().to("http://localhost:8080/appuntamento/1");


        String appointentPageTitle = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("DETTAGLI APPUNTAMENTO", appointentPageTitle);

    }

    @Test
    public void scenario5_appointmentDetailsFromMedic() {
        driver.get("http://localhost:8080/");

        String todayAppointments = driver.findElement(By.xpath("//b[contains(text(),'APPUNTAMENTI DI OGGI')]")).getText();
        assertEquals("APPUNTAMENTI DI OGGI", todayAppointments);

        // Click pulsante medici
        /*WebElement medicButton = driver.findElement(By.xpath("//a[contains(text(),'Medici')]"));
        medicButton.click();*/
        driver.navigate().to("http://localhost:8080/medici/");

        // Pagina medici
        String medicsPageTitle = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]")).getText();
        assertEquals("MEDICI", medicsPageTitle);

        // Click pulsante maggiori dettagli di un medico
        WebElement moreDetails = driver.findElement(By.xpath("//tbody/tr[1]/td[3]/a[1]"));
        moreDetails.click();

        String medicPageTitle = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("Dott. BRAD PITT", medicPageTitle);

        // Click pulsante maggiori dettagli di un appuntamento
        WebElement appointmentMoreDetails = driver.findElement(By.xpath("//body[1]/div[2]/div[4]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[3]/a[1]"));
        appointmentMoreDetails.click();

        String appointentPageTitle = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("DETTAGLI APPUNTAMENTO", appointentPageTitle);

    }

    @Test
    public void scenario6_newCommunication() {
        driver.get("http://localhost:8080/");

        String todayAppointments = driver.findElement(By.xpath("//b[contains(text(),'APPUNTAMENTI DI OGGI')]")).getText();
        assertEquals("APPUNTAMENTI DI OGGI", todayAppointments);

        // Click pulsante maggiori dettagli di un appuntamento
        WebElement moreDetails = driver.findElement(By.xpath("//tbody/tr[1]/td[4]/a[1]"));
        moreDetails.click();

        String appointentPageTitle = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("DETTAGLI APPUNTAMENTO", appointentPageTitle);

        // Click pulsante aggiungi comunicazione
        WebElement addCommunication = driver.findElement(By.xpath("//body/div[2]/div[4]/div[1]/div[2]/a[1]"));
        addCommunication.click();

        String newCommunicationPageTitle = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("NUOVA COMUNICAZIONE", newCommunicationPageTitle);

        // Compilazione form con testo e flag
        WebElement text = driver.findElement(By.xpath("//textarea[@id='testoComunicazione']"));
        text.sendKeys("Testo di prova per la comunicazione.");

        WebElement checkbox = driver.findElement(By.xpath("//input[@id='checkMedico']"));
        checkbox.sendKeys("true");

        // Submit del form
        WebElement submitButton = driver.findElement(By.xpath("//body/div[2]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/button[1]"));
        submitButton.submit();

        // Controllo redirect a pagina appuntamenti e presenza nuovo appuntamento
        String appointentPageTitle2 = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("DETTAGLI APPUNTAMENTO", appointentPageTitle2);

        String newCommunication = driver.findElement(By.xpath("//td[contains(text(),'Testo di prova per la comunicazione.')]")).getText();
        assertEquals("Testo di prova per la comunicazione.", newCommunication);

    }
}