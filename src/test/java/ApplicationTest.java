import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

public class ApplicationTest {
	private WebDriver driver;

	@Before
	public void setup() {
		System.setProperty("webdrive.chrome.driver","chromedriver.exe");
		driver=new ChromeDriver();
	}

	@After
	public void teardown() {
		driver.quit();
	}
	
	@Test
	public void LoadLoginPage() {
		driver.navigate().to("http://localhost:8090/samplewebapp");
		String title = driver.getTitle();
		assertTrue(title.contains("Login"));
		
		driver.findElement(By.linkText("Go To Home")).click();
		String title1 = driver.getTitle();
		assertTrue(title1.contains("Home"));
	}
}