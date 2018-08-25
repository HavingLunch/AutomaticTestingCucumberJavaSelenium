package Features.step_definitions;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TestTemperatureConvert {

	static WebDriver driver = new FirefoxDriver();
	static {
		String workingDir = System.getProperty("user.dir");
		driver.get("file://" + workingDir + "\\index.html");
	}

	@Given("^I input a (?:valid|invalid) input '(.*)' in '(.*)'$")
	public void inputOriginalTemperature(String temperatureNum, String originalUnit) throws InterruptedException {
		driver.findElement(By.linkText("Temperature")).click();
		WebElement fromValue = driver.findElement(By.id("fromValue"));
		fromValue.clear();
		fromValue.sendKeys(temperatureNum);
		WebElement dropDownListBox = driver.findElement(By.id("from"));
		Select clickThis = new Select(dropDownListBox);
		clickThis.selectByVisibleText(originalUnit);
	}

	@And("^I choose '(.*)' as the result unit$")
	public void inputTargetUnit(String targetUnit) throws InterruptedException {
		WebElement dropDownListBox = driver.findElement(By.id("to"));
		Select clickThis = new Select(dropDownListBox);
		clickThis.selectByVisibleText(targetUnit);
		driver.findElement(By.id("submitButton")).click();
	}

	@Then("^I get a result of '(.*)'$")
	public void checkResult(String result) throws IOException, InterruptedException {
		String resultOnPage = driver.findElement(By.id("results")).getText();
		Pattern p = Pattern.compile("((-)?\\d+(\\.\\d+)?)"); // the pattern to search for
		Matcher m = p.matcher(resultOnPage);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(".\\Screenshoot\\"
				+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + ".png"));
		Assert.assertTrue(m.find());
		Assert.assertTrue("The result is wrong!", m.group(0).equals(result));
	}

	@Then("^I get a alert with a message of '(.*)'$")
	public void checkErrorMessage(String errorMessage)
			throws IOException, InterruptedException, HeadlessException, AWTException {
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String popupMessage = alert.getText();
			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, "png", new File(".\\Screenshoot\\"
					+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + ".png"));
			alert.accept();
			Assert.assertTrue(popupMessage.equals(errorMessage));
	}
}
