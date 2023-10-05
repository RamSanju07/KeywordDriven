package com.keyword.engine;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.base.BasePage;

public class KeywordEngine {

	public WebDriver driver;
	public Properties prop;

	public BasePage base;

	public WebElement element;

	public static Workbook book;
	public static Sheet sheet;

	public final String FB_SCENARIO_PATH = "C:\\Users\\ramac\\eclipse-neon\\DummyKeywordDrivenFramework\\"
			+ "src\\main\\java\\com\\keyword\\FB scenario.xlsx";

	public void startExecution(String sheetName) throws EncryptedDocumentException, IOException {

		String locatorName = null;

		String locatorValue = null;

		FileInputStream file = null;

		file = new FileInputStream(FB_SCENARIO_PATH);

		book = WorkbookFactory.create(file);

		sheet = book.getSheet(sheetName);

		int k = 0;

		for (int i = 0; i < sheet.getLastRowNum(); i++) {

			try {

				String locatorColumnValue = sheet.getRow(i + 1).getCell(k + 1).toString().trim(); // id=username

				if (!locatorColumnValue.equalsIgnoreCase("NA")) {

					locatorName = locatorColumnValue.split("=")[0].trim(); // id

					locatorValue = locatorColumnValue.split("=")[1].trim(); // username
				}

				String action = sheet.getRow(i + 1).getCell(k + 2).toString().trim(); // action

				String value = sheet.getRow(i + 1).getCell(k + 3).toString().trim(); // value

				switch (action) {
				case "open browser\r\n" + "":

					base = new BasePage();
					prop = base.propertiesInit();

					if (value.isEmpty() || value.equals("NA")) {

						driver = base.driverLaunch(prop.getProperty("browser"));

					} else {

						driver = base.driverLaunch(value);

					}

					break;

				case "enter url\r\n" + "":

					if (value.isEmpty() || value.equals("NA")) {

						driver = base.driverLaunch(prop.getProperty("url"));

					} else {

						driver.get(value);

					}

					break;

				case "quit\r\n" + "":

					driver.quit();

					break;

				default:
					break;
				}

				switch (locatorName) {
				case "id":

					element = driver.findElement(By.id(locatorValue));

					if (action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					}

					locatorName = null;

					break;

				case "xpath":

					element = driver.findElement(By.xpath(locatorValue));

					if (action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					}

					locatorName = null;

					break;

				default:
					break;
				}

			} catch (Exception e) {

			}

		}

	}

}
