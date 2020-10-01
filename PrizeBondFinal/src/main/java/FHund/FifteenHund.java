package FHund;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.config.BaseConfig;
import com.page.model.BondListPage;
import com.util.ExplicitWait;
import com.util.Highlighter;
import com.util.TakeAppScreenShot;

public class FifteenHund {
	
	static WebDriver driver=null;

	public static void main(String[] args) throws Throwable {
		int[] bondValue = { 1500, 15000, 25000 }; // ,200,500,1000,1500, , 15000, 25000

		for (int i = 0; i < bondValue.length; i++) {
			ArrayList<String> myList = new ArrayList<>();
			myList = FifteenHund.getExcelData(String.valueOf(bondValue[i]));
			System.out.println(myList);
			TakeAppScreenShot.emptyScreenShotFolder();
			FifteenHund.testData(myList, bondValue[i]);
			System.out.println("Finish Testing: " + bondValue[i]);
			driver.quit();
		}
		System.out.println("Finish Testing Successfully");
	}

	public static void testData(ArrayList<String> bondNumber, int bondValue) throws Throwable {
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

		driver = new ChromeDriver();
		if (bondValue == 1500) {
			driver.get(BaseConfig.getconfig("URL"));
			Assert.assertEquals(driver.getTitle(), "1500 Prize Bond List 2020 - Prize Bond Rs 1500 Draw Result");
		} else if(bondValue == 15000) {
			driver.get(BaseConfig.getconfig("URL1"));
			Assert.assertEquals(driver.getTitle(), "15000 Prize Bond List 2020 - Prize Bond Rs 15000 Draw Result");
		} else if(bondValue==25000) {
			driver.get(BaseConfig.getconfig("URL2"));
			Assert.assertEquals(driver.getTitle(), "RS. 25000 Prize Bond List 2020 - Prize Bond 25000 Draw Result");
		} else {
			System.out.println("Invalid Bond Value");
			return;
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		BondListPage pfs = new BondListPage(driver);
		//System.out.println(driver.getCurrentUrl());
		//System.out.println(driver.getTitle());
		
//
		/*if (bondValue == 1500) {

			Thread.sleep(3000);

			pfs.getFHPB().click();
		}*/

		List<WebElement> rows = pfs.getTabrow();
		String[] arrData = new String[10];
		//System.out.println("Total Rows: " + rows.size());
		DateFormat format;
		Date drawDate = null, currDate;
		currDate = new Date();
		int priceValue = 0;
		String winMessage = null;
		WebElement viewDetail;
		int lineClick = 0;

		format = new SimpleDateFormat("d MMMM, yyyy", Locale.ENGLISH);
		// for (int i = 1; i < rows.size(); i++) { // runs 32 times (rows)
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j <= 6; j++) {				
				String x = pfs.madhuri(i, j).getText();

				if (j == 3) {
					drawDate = format.parse(x);
				//	System.out.println("Date: " + drawDate);
				} else if (j == 6) {
				//	System.out.println("View Detail: " + x);

					if (drawDate.compareTo(currDate) < 0) {
						lineClick++;
					//	System.out.println("Draw Date: " + drawDate + " Line Click: " + lineClick);

					//	Thread.sleep(3000);
						new ExplicitWait().getExplicitWait(driver, pfs.Sushmeta(lineClick));
						viewDetail = pfs.Sushmeta(lineClick);
						Actions ac = new Actions(driver);
						ac.moveToElement(viewDetail).build().perform();
						Highlighter.getcolor(driver, viewDetail, "red");
						TakeAppScreenShot.captureScreenShot(driver, "View Detail_"+bondValue+"_"+lineClick);
						ac.click().build().perform();
						for (int k = 0; k < bondNumber.size(); k++) {

							pfs.getRowChange().clear();
							
							new ExplicitWait().getExplicitWait(driver, pfs.getRowChange());
							pfs.getRowChange().sendKeys(bondNumber.get(k).toString());
							//Thread.sleep(2000);
						//	pfs.getRowChange().submit();
							pfs.getRowChange().sendKeys(Keys.ENTER);
							Thread.sleep(2000);

							winMessage = pfs.getwinMessage().getText();
							if (winMessage.equalsIgnoreCase(bondNumber.get(k).toString())) {

								priceValue = Integer.parseInt(pfs.getpValue().getText());
								System.out.println("Draw Date: " + drawDate + " won the price: "
										+ bondNumber.get(k).toString() + " Amount= " + priceValue);
							} else {
								System.out.println("Draw Date: " + drawDate + "Bond #: " + bondNumber.get(k).toString()
										+ " " + winMessage);
							}
						}
						Thread.sleep(2000);
						driver.navigate().back();
						Thread.sleep(2000);
					}
				}
			}
		}
	}

	public static ArrayList getExcelData(String sheetName) throws Throwable {
		FileInputStream fis = new FileInputStream("./DataDriven/BondNumbers.xlsx");
		// FileInputStream fis = new FileInputStream("./DataDriven/pbonds.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		ArrayList<String> list = new ArrayList<>();

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> columns = firstRow.cellIterator();
				int k = 0;
				int columnNum = 0;
				while (columns.hasNext()) {
					Cell value = columns.next();
					if (value.getStringCellValue().equalsIgnoreCase("BondNum"))
						;
					{
						columnNum = k;
					}
					k++;
				}
				while (rows.hasNext()) {
					Row rowData = rows.next();
					Iterator<Cell> cv = rowData.cellIterator();
					while (cv.hasNext()) {
						Cell c = cv.next();
						if (c.getCellType() == CellType.STRING) {
							list.add(c.getStringCellValue());
						} else if (c.getCellType() == CellType.NUMERIC) {
							list.add(NumberToTextConverter.toText(c.getNumericCellValue()));
						}
					}
				}
			}
		}
		return list;
	}
}