package com.training.ers.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class ERSTest {

	String browserName = "edge";
	WebDriver driver;

	@BeforeEach
	public void setup() {
		if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
	}

	@Test
	@DisplayName("Testing login functionality of ERS")
	public void testERSLoginFunctionality() {
		driver.get("http://localhost:8080/servlets-demo/login.html");
		driver.manage().window().maximize();
		// XPATH locators
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("Lisa");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Lisa@123");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[2]/input[2]")).click();
	
		//assert
		assertTrue(driver.findElement(By.id("view")).isDisplayed());
	}

	@AfterEach
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
