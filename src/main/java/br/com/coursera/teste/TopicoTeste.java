package br.com.coursera.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TopicoTeste {
	private WebDriver driver;
	private String baseUrl = "http://localhost:8080/forum-coursera/topico?novo";
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void cadastrarUsuarioTestCase() throws Exception {
		driver.get("http://localhost:8080/forum-coursera/");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys("Bond");
		driver.findElement(By.id("senha")).clear();
		driver.findElement(By.id("senha")).sendKeys("007");
		driver.findElement(By.id("btn_enviar")).click();
		driver.get("http://localhost:8080/forum-coursera/topico?novo");
		driver.findElement(By.id("titulo")).click();
		driver.findElement(By.id("titulo")).clear();
		driver.findElement(By.id("titulo")).sendKeys("Titulo teste");
		driver.findElement(By.id("conteudo")).clear();
		driver.findElement(By.id("conteudo")).sendKeys("Conteudo teste teste teste");
		driver.findElement(By.id("btn-enviar")).click();
		assertEquals("Cadastrado com sucesso!",
				driver.findElement(By
						.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Fórum'])[1]/following::span[1]"))
						.getText());
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
