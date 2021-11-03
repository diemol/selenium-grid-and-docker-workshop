package com.example.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URL;
import java.time.Instant;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Execution(ExecutionMode.CONCURRENT)
public class SauceToGoTest {

    private static final Logger LOG = Logger.getLogger(SauceToGoTest.class.getName());
    private static final String SELENIUM_GRID_URL = "http://localhost:4444";
    private static final String SAUCE_BUILD_ID = Instant.now().toString();
    private static final String SAUCE_OPTIONS_CAPS = "sauce:options";
    private static final long SLEEP_TIME = 5000;


    static Stream<Arguments> browsersAndPlatforms() {
        return Stream.of(
          arguments(Browser.CHROME.browserName(), Platform.LINUX.name()),
          arguments(Browser.FIREFOX.browserName(), Platform.LINUX.name()),
          arguments(Browser.EDGE.browserName(), Platform.LINUX.name()),
          arguments(Browser.SAFARI.browserName(), "macOS 11.00")
        );
    }

    public RemoteWebDriver createDriver(String testName, String browserName, String platformName)
      throws Exception {
        LOG.info("Running " + testName);
        URL gridUrl = new URL(SELENIUM_GRID_URL);
        MutableCapabilities capabilities;
        switch (browserName) {
            case "chrome":
                capabilities = new ChromeOptions();
                break;
            case "firefox":
                capabilities = new FirefoxOptions();
                break;
            case "MicrosoftEdge":
                capabilities = new EdgeOptions();
                break;
            case "safari":
                capabilities = new SafariOptions();
                break;
            default:
                throw new Exception("Browser not configured! " + browserName);
        }
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(SAUCE_OPTIONS_CAPS, getSauceOptions(testName));
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(gridUrl, capabilities);
        remoteWebDriver.manage().window().maximize();
        return remoteWebDriver;
    }

    @ParameterizedTest(name = "{index} ==> Browser: {0}, Platform: {1}")
    @MethodSource("browsersAndPlatforms")
    public void longScriptLoadingWebsites(String browserName, String platformName, TestInfo testInfo)
      throws Exception {
        RemoteWebDriver webDriver = createDriver(getTestName(testInfo), browserName, platformName);
        try {
            for (int i = 0; i < 3; i++) {
                webDriver.get("https://www.selenium.dev/");
                // For demo purposes
                Thread.sleep(SLEEP_TIME);
                webDriver.get("https://opensource.saucelabs.com/");
                // For demo purposes
                Thread.sleep(SLEEP_TIME);
            }
        } finally {
            webDriver.quit();
        }
    }

    @ParameterizedTest(name = "{index} ==> Browser: {0}, Platform: {1}")
    @MethodSource("browsersAndPlatforms")
    public void addOneItemToCart(String browserName, String platformName, TestInfo testInfo)
      throws Exception {
        RemoteWebDriver driver = createDriver(getTestName(testInfo), browserName, platformName);
        try {
            loginToSauceDemo(driver, "standard_user", "secret_sauce");
            driver.get("https://www.saucedemo.com/inventory.html");
            driver.findElement(By.className("btn_primary")).click();
            assertEquals("1", driver.findElement(By.className("shopping_cart_badge")).getText());

            driver.get("https://www.saucedemo.com/cart.html");
            assertEquals(1, driver.findElements(By.className("inventory_item_name")).size());
        } finally {
            driver.quit();
        }
    }

    @ParameterizedTest(name = "{index} ==> Browser: {0}, Platform: {1}")
    @MethodSource("browsersAndPlatforms")
    public void validCredentials(String browserName, String platformName, TestInfo testInfo)
      throws Exception {
        RemoteWebDriver driver = createDriver(getTestName(testInfo), browserName, platformName);
        try {
            loginToSauceDemo(driver, "standard_user", "secret_sauce");
            assertTrue(driver.getCurrentUrl().contains("inventory"));
        } finally {
            driver.quit();
        }
    }

    private void loginToSauceDemo(RemoteWebDriver driver, String bad, String bad2)
      throws InterruptedException {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys(bad);
        driver.findElement(By.id("password")).sendKeys(bad2);
        driver.findElement(By.className("btn_action")).click();
        Thread.sleep(SLEEP_TIME);
    }

    private String getTestName(TestInfo testInfo) {
        if (testInfo.getTestMethod().isPresent()) {
            return testInfo.getTestMethod().get().getName() + " - " + testInfo.getDisplayName();
        }
        return testInfo.getDisplayName();
    }

    private MutableCapabilities getSauceOptions(String testName) {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("dataCenter", "US");
        capabilities.setCapability("screenResolution", "1920x1440");
        capabilities.setCapability("username", System.getenv("SAUCE_USERNAME"));
        capabilities.setCapability("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
        capabilities.setCapability("name", testName);
        capabilities.setCapability("build", SAUCE_BUILD_ID);
        return capabilities;
    }

}
