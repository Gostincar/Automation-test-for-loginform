import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class comtradeShopLoginForm {

    /**
     * Initialization of Selenium WebDriver
     */
    public static final WebDriver driver = new ChromeDriver();

    /**
     * Global define WebDriver wait
     */
    WebDriverWait wait = new WebDriverWait(driver, 10);

    /**
     * Global variables - locators
     */
    String urlEnvironment = "https://www.comtradeshop.com/";
    String signInLinkText = "Prijavi se";
    String submitButton = "send2";
    String elementEmail = "email";
    String elementLogOutFromAccount = "Izloguj se";
    String elementValidationMessage = "div.alert.alert-danger";
    String elementWelcomeMessage = "div.welcome-msg";

    /**
     * Global variables - Test data
     */
    String wrongTestDataEmailAddress = "petar.petrovic@test.rs";
    String validTestDataEmailAddress = "pedja1984@gmail.com";
    String elementPassword = "pass";
    String testDataPassword = "test123";


    /**
     * Global variables - Expected result
     */
    String expectedResultForValidationMessage = "Došlo je do greške. Proverite unete podatke.";


    /**
     * Actions which are propagated on all tests
     */
    @Before
    public void OpenApplication () {

        //Open URL Environment
        driver.get(urlEnvironment);

        //Maximize windows
        driver.manage().window().maximize();
        System.out.println("01.Successfully open home page");
    }

    /**
     * 01.TC Login form - Submit empty field
     */
    @Test
    public void Test1() throws Exception {

        //Logic for wait element
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(signInLinkText)));

        //Click on SignIn button
        driver.findElement(By.linkText(signInLinkText)).click();
        System.out.println("02.Click on SignIn button");

        //Wait element
        wait.until(ExpectedConditions.elementToBeClickable(By.id(submitButton)));

        //Click on Submit button on Login form
        driver.findElement(By.id(submitButton)).click();
        System.out.println("03.Click on Submit button");

        //Wait element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(elementValidationMessage)));

        //Verify that alert message is present
        if (driver.findElement(By.cssSelector(elementValidationMessage)) != null) {

            String actualResultForValidationMessage = driver.findElement(By.cssSelector(elementValidationMessage)).getText();
            Assert.assertEquals(expectedResultForValidationMessage,actualResultForValidationMessage);
            System.out.println("04.Validation message is present and user can't access on his account");

        }else {

            throw new Exception("04.ERROR - Validation message isn't present");
        }
    }

    /**
     * 02.TC Login form - Submit only email field
     */
    @Test
    public void Test2() throws Exception {

        //Logic for wait element
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(signInLinkText)));

        //Click on SignIn button
        driver.findElement(By.linkText(signInLinkText)).click();
        System.out.println("02.Click on SignIn button");

        //Wait element
        wait.until(ExpectedConditions.elementToBeClickable(By.id(submitButton)));

        //Input data in email field
        driver.findElement(By.id(elementEmail)).sendKeys(wrongTestDataEmailAddress);
        System.out.println("03.Enter data in Email field");

        //Click on Submit button on Login form
        driver.findElement(By.id(submitButton)).click();
        System.out.println("04.Click on Submit button");

        //Wait element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(elementValidationMessage)));

        //Verify that alert message is present
        if (driver.findElement(By.cssSelector(elementValidationMessage)) != null) {

            String actualResultForValidationMessage = driver.findElement(By.cssSelector(elementValidationMessage)).getText();
            Assert.assertEquals(expectedResultForValidationMessage,actualResultForValidationMessage);
            System.out.println("05.Validation message is present and user can't access on his account");

        }else {

            throw new Exception("05.ERROR - Validation message isn't present");
        }
    }

    /**
     * 03.TC Login form - Submit only password field
     */
    @Test
    public void Test3() throws Exception {

        //Logic for wait element
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(signInLinkText)));

        //Click on SignIn button
        driver.findElement(By.linkText(signInLinkText)).click();
        System.out.println("02.Click on SignIn button");

        //Wait element
        wait.until(ExpectedConditions.elementToBeClickable(By.id(submitButton)));

        //Input data in password field
        driver.findElement(By.id(elementPassword)).sendKeys(testDataPassword);
        System.out.println("03.Enter data in Password field");

        //Click on Submit button on Login form
        driver.findElement(By.id(submitButton)).click();
        System.out.println("04.Click on Submit button");

        //Verify that alert message is present
        if (driver.findElement(By.cssSelector(elementValidationMessage)) != null) {

            String actualResultForValidationMessage = driver.findElement(By.cssSelector(elementValidationMessage)).getText();
            Assert.assertEquals(expectedResultForValidationMessage,actualResultForValidationMessage);
            System.out.println("05.Validation message is present and user can't access on his account");

        }else {

            throw new Exception("05.ERROR - Validation message isn't present");
        }
    }

    /**
     * 04.TC Login form - Submit valid data for username and password
     */
    @Test
    public void Test4() throws Exception {

        //Logic for wait element
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(signInLinkText)));

        //Click on SignIn button
        driver.findElement(By.linkText(signInLinkText)).click();
        System.out.println("02.Click on SignIn button");

        //Wait element
        wait.until(ExpectedConditions.elementToBeClickable(By.id(submitButton)));

        //Input data in email field
        driver.findElement(By.id(elementEmail)).sendKeys(validTestDataEmailAddress);
        System.out.println("03.Enter data in Email field");

        //Input data in password field
        driver.findElement(By.id(elementPassword)).sendKeys(testDataPassword);
        System.out.println("04.Enter data in Password field");

        //Click on Submit button on Login form
        driver.findElement(By.id(submitButton)).click();
        System.out.println("05.Click on Submit button");

        //Wait element
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(elementLogOutFromAccount)));

        //Verify that alert message is present
        if (driver.findElement(By.cssSelector(elementWelcomeMessage)) != null) {

            System.out.println("06.Validation message is not present and user can access on his account");

            //Wait element
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText(elementLogOutFromAccount)));

            //Click on button for Logout from user account
            driver.findElement(By.linkText(elementLogOutFromAccount)).click();

            //Close browser if test is finish
            driver.close();

        }else {

            throw new Exception("06.ERROR - Validation message is present and user can't access on his account");
        }
    }
}
