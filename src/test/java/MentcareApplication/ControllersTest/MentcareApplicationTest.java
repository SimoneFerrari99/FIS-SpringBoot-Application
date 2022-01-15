package MentcareApplication.ControllersTest;

import MentcareApplication.ControllersTest.PageObjects.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.commons.lang3.SystemUtils;

import org.openqa.selenium.WebDriver;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        HomePage homePage = new HomePage(driver);

        String confirmationHomeHeader = homePage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHomeHeader);

        AppointmentPage appointmentPage = homePage.navigateToMoreAppointmentDetails();

        String confirmationAppointmentHeader = appointmentPage.getConfirmationHeader();
        assertEquals("DETTAGLI APPUNTAMENTO", confirmationAppointmentHeader);
    }

    @Test
    public void scenario1_EditAppointment() {
        driver.get("http://localhost:8080/");
        HomePage homePage = new HomePage(driver);

        String confirmationHomeHeader = homePage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHomeHeader);

        FormAppointmentPage formAppointmentPage = homePage.navigateToEditAppointmentDetails();

        String confirmationFormAppointmentHeader = formAppointmentPage.getConfirmationHeader();
        assertEquals("MODIFICA APPUNTAMENTO", confirmationFormAppointmentHeader);
    }

    @Test
    public void scenario1_AllAppoitments() {
        driver.get("http://localhost:8080/");
        HomePage homePage = new HomePage(driver);

        String confirmationHomeHeader = homePage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHomeHeader);

        AllAppointmentsPage allAppointmentsPage = homePage.navigateToAllAppointments();

        String confirmationAllAppointmentsHeader = allAppointmentsPage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI", confirmationAllAppointmentsHeader);
    }

    @Test
    @DirtiesContext
    public void scenario2_AcceptNewRequest() {
        driver.get("http://localhost:8080/");
        HomePage homePage = new HomePage(driver);

        String confirmationHomeHeader = homePage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHomeHeader);

        // Click pulsante di accettazione
        FormPatientPage formPatientPage = homePage.acceptNewRequest();

        String confirmationEditPatientHeader = formPatientPage.getConfirmationHeader();
        assertEquals("Harrison Ford", confirmationEditPatientHeader);

        // Inserimento medico e categoria
        formPatientPage.setMedicInput("1");
        formPatientPage.setProblemCategoryInput("Categoria di Test");

        // Submit del form
        PatientPage patientPage = formPatientPage.submitPatientFormRedirectToPatientPage();

        // Controllo della pagina d'arrivo
        String confirmationPatientHeader = patientPage.getConfirmationHeader();
        assertEquals("Harrison Ford", confirmationPatientHeader);
    }

    @Test
    @DirtiesContext
    public void scenario3_newPatient() {
        driver.get("http://localhost:8080/");
        HomePage homePage = new HomePage(driver);
        NavBarObject navBarObject = new NavBarObject(driver);

        String confirmationHomeHeader = homePage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHomeHeader);

        // Click pulsante pazienti
        AllPatientsPage allPatientsPage = navBarObject.navigateToPatientsPage();

        String confirmationPatientsHeader = allPatientsPage.getConfirmationHeader();
        assertEquals("PAZIENTI", confirmationPatientsHeader);

        // Click pulsante nuovo paziente
        FormPatientPage formPatientPage = allPatientsPage.navigateToNewPatientPage();

        String confirmationFormPatientHeader = formPatientPage.getConfirmationHeader();
        assertEquals("NUOVO PAZIENTE", confirmationFormPatientHeader);

        // Inserimento Nome, cognome, CF, Residenza, Categoria, Problematica, Data Nascita, Medico
        formPatientPage.setNameInput("Simone");
        formPatientPage.setLastnameInput("Ferrari");
        formPatientPage.setCfInput("SF99EI");
        formPatientPage.setResidenceInput("Venezia");
        formPatientPage.setProblemCategoryInput("Problema");
        formPatientPage.setProblemDescriptionInput("Descrizione Problema");
        formPatientPage.setBirthDateInput("09/06/1999");
        formPatientPage.setMedicInput("1");

        // Submit del form
        AllPatientsPage allPatientsPage1 = formPatientPage.submitPatientFormRedirectToAllPatientsPage();

        // Controllo redirect alla pagina pazienti
        String confirmationPatientHeader = allPatientsPage1.getConfirmationHeader();
        assertEquals("PAZIENTI", confirmationPatientHeader);

        // Controllo presenza nuovo paziente
        assertEquals("SF99EI", allPatientsPage1.findPatientByCf("SF99EI"));
    }

    @Test
    @DirtiesContext
    public void scenario4_newAppointmentFromAppointmentsPage(){
        driver.get("http://localhost:8080/");
        HomePage homePage = new HomePage(driver);
        NavBarObject navBarObject = new NavBarObject(driver);

        String confirmationHomeHeader = homePage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHomeHeader);

        // Click pulsante appuntamenti
        AllAppointmentsPage allAppointmentsPage = navBarObject.navigateToAppointmentsPage();

        // Pagina appuntamenti
        String confirmationAllAppointmentsHeader = allAppointmentsPage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI", confirmationAllAppointmentsHeader);

        // Click pulsante nuovo appuntamento
        allAppointmentsPage.clickNewAppointmentPage();
        FormAppointmentPage formAppointmentPage = allAppointmentsPage.clickFromMedicButton();

        String confirmationFormAppointmentHeader = formAppointmentPage.getConfirmationHeader();
        assertEquals("NUOVO APPUNTAMENTO", confirmationFormAppointmentHeader);

        // Inserimento Paziente, Clinica, Data
        formAppointmentPage.setPatientInput("1");
        formAppointmentPage.setClinicInput("ClinicaDiProva");
        formAppointmentPage.setAppointmentDateInput(dtf.format(LocalDate.now()));
        AllAppointmentsPage allAppointmentsPage1 = formAppointmentPage.submitPatientFormRedirectToAllAppointmentsPage();

        // Controllo redirect a pagina appuntamenti e presenza nuovo appuntamento
        String confirmationAllAppointments1Header = allAppointmentsPage1.getConfirmationHeader();
        assertEquals("APPUNTAMENTI", confirmationAllAppointments1Header);

        assertEquals("ClinicaDiProva", allAppointmentsPage1.findAppointmentByClinic("ClinicaDiProva"));
    }

    @Test
    @DirtiesContext
    public void scenario4_newAppointmentFromMedicPage() {
        driver.get("http://localhost:8080/");
        HomePage homePage = new HomePage(driver);
        NavBarObject navBarObject = new NavBarObject(driver);

        String confirmationHomeHeader = homePage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHomeHeader);

        // Click pulsante medici
        AllMedicsPage allMedicsPage = navBarObject.navigateToMedicsPage();

        // Pagina medici
        String confirmationMedicsHeader = allMedicsPage.getConfirmationHeader();
        assertEquals("MEDICI", confirmationMedicsHeader);

        // Click pulsante dettaglio medico
        MedicPage medicPage = allMedicsPage.navigateToMoreMedicDetails();

        String confirmationMedicHeader = medicPage.getConfirmationHeader();
        assertEquals("Dott. BRAD PITT", confirmationMedicHeader);

        // Click aggiunta appuntamento
        FormAppointmentPage formAppointmentPage = medicPage.navigateToNewAppointmentPage();

        String confirmationFormAppointmentHeader = formAppointmentPage.getConfirmationHeader();
        assertEquals("NUOVO APPUNTAMENTO", confirmationFormAppointmentHeader);

        // Inserimento Paziente, Clinica, Data
        formAppointmentPage.setPatientInput("1");
        formAppointmentPage.setClinicInput("ClinicaDiProva");
        formAppointmentPage.setAppointmentDateInput(dtf.format(LocalDate.now()));
        AllAppointmentsPage allAppointmentsPage1 = formAppointmentPage.submitPatientFormRedirectToAllAppointmentsPage();

        // Controllo redirect a pagina appuntamenti e presenza nuovo appuntamento
        String confirmationAllAppointments1Header = allAppointmentsPage1.getConfirmationHeader();
        assertEquals("APPUNTAMENTI", confirmationAllAppointments1Header);

        assertEquals("ClinicaDiProva", allAppointmentsPage1.findAppointmentByClinic("ClinicaDiProva"));
    }

    @Test
    @DirtiesContext
    public void scenario4_newAppointmentFromPatientPage() {
        driver.get("http://localhost:8080/");
        HomePage homePage = new HomePage(driver);
        NavBarObject navBarObject = new NavBarObject(driver);

        String confirmationHomeHeader = homePage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHomeHeader);

        // Click pulsante pazienti
        AllPatientsPage allPatientsPage = navBarObject.navigateToPatientsPage();

        // Pagina pazienti
        String confirmationPatientsHeader = allPatientsPage.getConfirmationHeader();
        assertEquals("PAZIENTI", confirmationPatientsHeader);

        // Click pulsante dettaglio paziente
        PatientPage patientPage = allPatientsPage.navigateToMorePatientsDetails();

        String confirmationPatientHeader = patientPage.getConfirmationHeader();
        assertEquals("Johnny Depp", confirmationPatientHeader);

        // Click aggiunta appuntamento
        FormAppointmentPage formAppointmentPage = patientPage.navigateToNewAppointmentPage();

        String confirmationFormAppointmentHeader = formAppointmentPage.getConfirmationHeader();
        assertEquals("NUOVO APPUNTAMENTO", confirmationFormAppointmentHeader);

        // Inserimento Paziente, Clinica, Data
        formAppointmentPage.setPatientInput("1");
        formAppointmentPage.setClinicInput("ClinicaDiProva");
        formAppointmentPage.setAppointmentDateInput(dtf.format(LocalDate.now()));
        AllAppointmentsPage allAppointmentsPage1 = formAppointmentPage.submitPatientFormRedirectToAllAppointmentsPage();

        // Controllo redirect a pagina appuntamenti e presenza nuovo appuntamento
        String confirmationAllAppointments1Header = allAppointmentsPage1.getConfirmationHeader();
        assertEquals("APPUNTAMENTI", confirmationAllAppointments1Header);

        assertEquals("ClinicaDiProva", allAppointmentsPage1.findAppointmentByClinic("ClinicaDiProva"));
    }

    @Test
    public void scenario5_appointmentDetailsFromHome() {
        driver.get("http://localhost:8080/");
        HomePage homePage = new HomePage(driver);

        String confirmationHomeHeader = homePage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHomeHeader);

        // Click pulsante maggiori dettagli di un appuntamento
        AppointmentPage appointmentPage = homePage.navigateToMoreAppointmentDetails();

        String confirmationAppointmentDetailHeader = appointmentPage.getConfirmationHeader();
        assertEquals("DETTAGLI APPUNTAMENTO", confirmationAppointmentDetailHeader);
    }

    @Test
    @DirtiesContext
    public void scenario5_appointmentDetailsFromAppointments() {
        driver.get("http://localhost:8080/");
        HomePage homePage = new HomePage(driver);
        NavBarObject navBarObject = new NavBarObject(driver);

        String confirmationHomeHeader = homePage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHomeHeader);

        // Click pulsante appuntamenti
        AllAppointmentsPage allAppointmentsPage = navBarObject.navigateToAppointmentsPage();

        // Pagina appuntamenti
        String confirmationAllAppointmentsHeader = allAppointmentsPage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI", confirmationAllAppointmentsHeader);

        // Click pulsante maggiori dettagli di un appuntamento
        AppointmentPage appointmentPage = allAppointmentsPage.navigateToMoreAppointmentDetails();

        String confirmationAppointmentHeader = appointmentPage.getConfirmationHeader();
        assertEquals("DETTAGLI APPUNTAMENTO", confirmationAppointmentHeader);
    }

    @Test
    @DirtiesContext
    public void scenario5_appointmentDetailsFromPatient() throws InterruptedException {
        driver.get("http://localhost:8080/");
        HomePage homePage = new HomePage(driver);
        NavBarObject navBarObject = new NavBarObject(driver);

        String confirmationHomeHeader = homePage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHomeHeader);

        // Click pulsante pazienti
        AllPatientsPage allPatientsPage = navBarObject.navigateToPatientsPage();

        // Pagina pazienti
        String confirmationAllPatientsHeader = allPatientsPage.getConfirmationHeader();
        assertEquals("PAZIENTI", confirmationAllPatientsHeader);

        // Click pulsante maggiori dettagli di un paziente
        PatientPage patientPage = allPatientsPage.navigateToMorePatientsDetails();

        String confirmationPatientHeader = patientPage.getConfirmationHeader();
        assertEquals("Johnny Depp", confirmationPatientHeader);

        // Click pulsante maggiori dettagli di un appuntamento
        AppointmentPage appointmentPage = patientPage.navigateToAppointmentDetailsPage();

        String confirmationPAppointmentHeader = appointmentPage.getConfirmationHeader();
        assertEquals("DETTAGLI APPUNTAMENTO", confirmationPAppointmentHeader);
    }

    @Test
    public void scenario5_appointmentDetailsFromMedic() {
        driver.get("http://localhost:8080/");
        HomePage homePage = new HomePage(driver);
        NavBarObject navBarObject = new NavBarObject(driver);

        String confirmationHomeHeader = homePage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHomeHeader);

        // Click pulsante medici
        AllMedicsPage allMedicsPage = navBarObject.navigateToMedicsPage();

        // Pagina medici
        String confirmationAllMedicsHeader = allMedicsPage.getConfirmationHeader();
        assertEquals("MEDICI", confirmationAllMedicsHeader);

        // Click pulsante maggiori dettagli di un medico
        MedicPage medicPage = allMedicsPage.navigateToMoreMedicDetails();

        String confirmationMedicHeader = medicPage.getConfirmationHeader();
        assertEquals("Dott. BRAD PITT", confirmationMedicHeader);

        // Click pulsante maggiori dettagli di un appuntamento
        AppointmentPage appointmentPage = medicPage.navigateToAppointmentDetailsPage();

        String confirmationAppointmentHeader = appointmentPage.getConfirmationHeader();
        assertEquals("DETTAGLI APPUNTAMENTO", confirmationAppointmentHeader);
    }

    @Test
    @DirtiesContext
    public void scenario6_newCommunication() {
        driver.get("http://localhost:8080/");
        HomePage homePage = new HomePage(driver);

        String confirmationHomeHeader = homePage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHomeHeader);

        // Click pulsante maggiori dettagli di un appuntamento
        AppointmentPage appointmentPage = homePage.navigateToMoreAppointmentDetails();

        String confirmationAppointmentHeader = appointmentPage.getConfirmationHeader();
        assertEquals("DETTAGLI APPUNTAMENTO", confirmationAppointmentHeader);

        // Click pulsante aggiungi comunicazione
        FormCommunicationPage formCommunicationPage = appointmentPage.navigateToNewCommunicationPage();

        String confirmationCommunicationHeader = formCommunicationPage.getConfirmationHeader();
        assertEquals("NUOVA COMUNICAZIONE", confirmationCommunicationHeader);

        // Compilazione form con testo e flag
        formCommunicationPage.setTextinput("Testo di prova per la comunicazione.");
        formCommunicationPage.setMedicCheckboxInput("true");

        // Submit del form
        AppointmentPage appointmentPage1 = formCommunicationPage.submitCommunicationForm();

        // Controllo redirect a pagina appuntamenti e presenza nuovo appuntamento
        String confirmationAppointment1Header = appointmentPage1.getConfirmationHeader();
        assertEquals("DETTAGLI APPUNTAMENTO", confirmationAppointment1Header);

        assertEquals("Testo di prova per la comunicazione.", appointmentPage1.findCommunicationByText("Testo di prova per la comunicazione."));
    }

    @Test
    @DirtiesContext
    public void extra1_editAppointment() {
        driver.get("http://localhost:8080/");
        HomePage homePage = new HomePage(driver);

        String confirmationHomeHeader = homePage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHomeHeader);

        // Click modifica appuntamento
        FormAppointmentPage formAppointmentPage = homePage.navigateToEditAppointmentDetails();

        String confirmationFormAppointmentHeader = formAppointmentPage.getConfirmationHeader();
        assertEquals("MODIFICA APPUNTAMENTO", confirmationFormAppointmentHeader);

        // Modifica di alcuni campi del form appuntamento
        formAppointmentPage.setClinicInput("MODIFICATO");
        formAppointmentPage.setAppointmentDateInput(dtf.format(LocalDate.now().plusDays(2)));

        AppointmentPage appointmentPage = formAppointmentPage.submitPatientFormRedirectToAppointmentPage();

        // Controllo redirect a pagina appuntamento modificato e verifica modifica appuntamento
        String confirmationAppointmentHeader = appointmentPage.getConfirmationHeader();
        assertEquals("DETTAGLI APPUNTAMENTO", confirmationAppointmentHeader);

        assertEquals("MestreMODIFICATO", appointmentPage.findEditedClinic());
        assertEquals(dtf.format(LocalDate.now().plusDays(2)), appointmentPage.findEditedAppointmentDate());
    }

    @Test
    @DirtiesContext
    public void extra2_deleteAppointment() {
        driver.get("http://localhost:8080/");
        HomePage homePage = new HomePage(driver);

        String confirmationHomeHeader = homePage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHomeHeader);

        // Click maggiori dettagli appuntamento
        AppointmentPage appointmentPage = homePage.navigateToMoreAppointmentDetails();

        String confirmationAppointmentHeader = appointmentPage.getConfirmationHeader();
        assertEquals("DETTAGLI APPUNTAMENTO", confirmationAppointmentHeader);

        // Click elimina appuntamento
        AllAppointmentsPage allAppointmentsPage = appointmentPage.clickDeleteAppointmentButton();

        // Controllo redirect a pagina appuntamenti
        String confirmationAllAppointmentHeader = allAppointmentsPage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI", confirmationAllAppointmentHeader);
    }

    @Test
    @DirtiesContext
    public void extra3_deleteRequest() {
        driver.get("http://localhost:8080/");
        HomePage homePage = new HomePage(driver);

        String confirmationHomeHeader = homePage.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHomeHeader);

        // Click pulsante di rifiuto
        HomePage homePage1 = homePage.RejectNewRequest();

        // Verifica di non redirect
        String confirmationHome1Header = homePage1.getConfirmationHeader();
        assertEquals("APPUNTAMENTI DI OGGI", confirmationHome1Header);

        WebElement deletedElement = null;
        try{
            deletedElement = homePage1.findDeletedElement("//body[1]/div[2]/div[3]/div[2]/table[1]/tbody[1]/tr[1]/th[1]");
        }catch(NoSuchElementException e){
            assertNull(deletedElement);
            System.out.println("SUCCESS: Element not found.");
            return;
        }

        System.out.println("FAIL: Element found.");
        assertNull(deletedElement);
    }
}