package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    // TODO: Write Test Cases Here

       public void testCase01() {
        System.out.println("Start Test case: testCase01");
        
        // Navigate to the Stack Overflow website
        driver.get("https://stackoverflow.com/");
        
        // Verify that the URL contains 'stackoverflow'
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("stackoverflow")) {
            System.out.println("Testcase 01 Passed: URL contains 'stackoverflow'");
        } else {
            System.out.println("Testcase 01 Failed: URL does not contain 'stackoverflow'");
        }
        
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() {
        System.out.println("Start Test case: testCase02");
        
        // Go to the Stack Overflow homepage
        driver.get("https://stackoverflow.com/");
        
        // Find search bar, enter "Python list comprehension" and submit
        WebElement searchBox = driver.findElement((By.xpath("//input[@name='q' ]")));
        searchBox.sendKeys("Python list comprehension");
        searchBox.submit();
        
        // Verify relevant results are displayed
        WebElement resultsElement = driver.findElement((By.xpath("//div[contains(@class, 's-post-summary')]")));
        if (resultsElement.isDisplayed()) {
            System.out.println("Testcase 02 Passed: Relevant results are displayed");
        } else {
            System.out.println("Testcase 02 Failed: No results displayed");
        }
        
        
        System.out.println("end Test case: testCase02");
    }
       public void testCase03() {
        System.out.println("Start Test case: testCase03");
        
        // On the Stack Overflow homepage, select the "Tags" section
        driver.get("https://stackoverflow.com/");
        WebElement tagsLink = driver.findElement(By.xpath("//span[normalize-space()='Tags']"));
        tagsLink.click();
        
        // Search for and select the "javascript" tag
        WebElement tagSearchBox = driver.findElement(By.xpath("//input[@id='tagfilter']"));
        tagSearchBox.sendKeys("javascript");
        
        WebElement javascriptTag = driver.findElement(By.xpath("//a[@href='/questions/tagged/javascript']"));
        javascriptTag.click();
        
        // Verify that only questions tagged with "javascript" are displayed
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("tagged/javascript")) {
            System.out.println("Testcase 03 Passed: Tag filtering successful for javascript");
        } else {
            System.out.println("Testcase 03 Failed: Tag filtering failed");
        }
        
        System.out.println("end Test case: testCase03");
    }


   public void testCase04() {
    System.out.println("Start Test case: testCase04");

    // Navigate to the Stack Overflow Questions page
    driver.get("https://stackoverflow.com/questions");

    // Step 1: Click on the "More" dropdown button first
  WebElement moreButton = driver.findElement(By.xpath("//button[normalize-space()='More']"));
  moreButton.click();

 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement scoreSortButton = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//div[@id='uql-more-popover']//a[contains(normalize-space(), 'Score')] | //a[@data-nav-value='Score']")
    ));  
    scoreSortButton.click();

    // Step 3: Verify that questions are sorted by score
    boolean isSorted = wait.until(ExpectedConditions.urlContains("tab=score"));

    if (isSorted) {
        System.out.println("Testcase 04 Passed: Sorted by Score successfully");
    } else {
        System.out.println("Testcase 04 Failed: Sorting by Score failed");
    }


   }


    


}





