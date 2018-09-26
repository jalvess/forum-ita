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

public class CadastroUsuarioTeste {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	private String login = "Bond";
	private String senha = "007";
	private String email = "bond@gmail.com";
	private String nome = "James";

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://localhost:8080/forum-coursera/cadastro";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void adicionaUsuarioTestCase() throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.id("nome")).click();
		driver.findElement(By.id("nome")).clear();
		driver.findElement(By.id("nome")).sendKeys(nome);
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys(login);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("senha")).clear();
		driver.findElement(By.id("senha")).sendKeys(senha);
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Senha'])[1]/following::input[2]"))
				.click();
	}

	@Test
	public void logar() {
		driver.get("http://localhost:8080/forum-coursera/");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys(login);
		driver.findElement(By.id("senha")).clear();
		driver.findElement(By.id("senha")).sendKeys(senha);
		driver.findElement(By.id("btn_enviar")).click();
		assertEquals("Bond",
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
