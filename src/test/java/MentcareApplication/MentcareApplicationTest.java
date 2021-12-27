package MentcareApplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.commons.lang3.SystemUtils;

import org.openqa.selenium.WebDriver;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MentcareApplicationTest {
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private WebDriver driver;

    @Before
    public void setUp() {
        org.openqa.selenium.chrome.ChromeOptions chrome_options = new ChromeOptions();
        //chrome_options.addArguments("--headless");
        if(SystemUtils.IS_OS_WINDOWS){
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_win32_96/chromedriver.exe").toString());
        }
        else if (SystemUtils.IS_OS_MAC){
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_mac64_96/chromedriver").toString());
        }
        else if (SystemUtils.IS_OS_LINUX){
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_linux64_96/chromedriver").toString());
        }
        if (driver == null)
            driver = new ChromeDriver(chrome_options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            //driver.quit();
        }
    }

    @Test
    public void scenario1_MoreDetils(){
        driver.get("http://localhost:8080/");

        String todayAppointments = driver.findElement(By.xpath("//b[contains(text(),'APPUNTAMENTI DI OGGI')]")).getText();
        assertEquals("APPUNTAMENTI DI OGGI", todayAppointments);

        WebElement moreDetails = driver.findElement(By.xpath("//tbody/tr[1]/td[4]/a[1]"));
        moreDetails.click();
        String title1 = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("DETTAGLI APPUNTAMENTO", title1);
    }

    @Test
    public void scenario1_Edit(){
        driver.get("http://localhost:8080/");

        String todayAppointments = driver.findElement(By.xpath("//b[contains(text(),'APPUNTAMENTI DI OGGI')]")).getText();
        assertEquals("APPUNTAMENTI DI OGGI", todayAppointments);

        WebElement edit = driver.findElement(By.xpath("//tbody/tr[1]/td[4]/a[2]"));
        edit.click();
        String title = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]")).getText();
        assertEquals("MODIFICA APPUNTAMENTO ID: 1", title);
    }

    @Test
    public void scenario1_All(){
        driver.get("http://localhost:8080/");

        String todayAppointments = driver.findElement(By.xpath("//b[contains(text(),'APPUNTAMENTI DI OGGI')]")).getText();
        assertEquals("APPUNTAMENTI DI OGGI", todayAppointments);

        WebElement all = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[2]/a[1]"));
        all.click();
        String title = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]")).getText();
        assertEquals("APPUNTAMENTI", title);
    }

    @Test
    public void scenario2(){
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

}
