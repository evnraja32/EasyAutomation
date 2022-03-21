package elements;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class Browser {

	protected static RemoteWebDriver driver = null;

	public Browser(String browser) {

		String stepDesc = "Launching web application: url";
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chorme.driver", "/Users/veelluru/Downloads/chromedriver");
			driver = new ChromeDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", "./drivers/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		default:
			throw new InvalidArgumentException("Invalid browser option: " + browser);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		FluentWait<RemoteWebDriver> wait = new FluentWait<RemoteWebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5));


	}
	
	public void closeBrowser(){
		driver.close();
	}
}
