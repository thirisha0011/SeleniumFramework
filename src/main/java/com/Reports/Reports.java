package com.Reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.customException.FolderNotCreated;
import com.excelSheet.DataProviders;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Reports extends DataProviders{
	
	private static ExtentReports reports;
	private static ExtentSparkReporter extentx;
	private static File report;
	private static WebDriver driver;
	private static String reportFolderName;
	static ExtentTest chileNode;
	
	public ExtentReports createReport(String className) throws FolderNotCreated, IOException {
		String Document_Title = getData("Document Title");
		String Report_Name = getData("Report Name");
		String Company_Name = getData("Company Name");
		String Project_Name = getData("Project Name");
		String OS = getData("OS");
		String date_time_Pattern = getData("Date&Time Pattern");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(date_time_Pattern);
		LocalDateTime now = LocalDateTime.now();
		String dateTime = dtf.format(now);
		String name = className+System.currentTimeMillis();
		reportFolderName = name;
		
		report = new File("./Reports/"+name+"/Screenshot/");
		if(!report.isDirectory())
			if(report.mkdirs())
				System.out.println(name+" Folder Created...!");
			else
				throw new FolderNotCreated(name+" Folder not created...!");
		reports = new ExtentReports();
		extentx = new ExtentSparkReporter("./Reports/"+name+"/Report.html");
		extentx.config().setDocumentTitle(Document_Title);
		extentx.config().setReportName(Report_Name);
		extentx.config().setTheme(Theme.STANDARD);
		reports.attachReporter(extentx);
		
		reports.setSystemInfo("Company Name", Company_Name);
		reports.setSystemInfo("Project Name", Project_Name);
		reports.setSystemInfo("OS", OS);
		reports.setSystemInfo("Date and Time", dateTime);
		return reports;
	}
	
	public ExtentTest createTest(String testName, String description, WebDriver driver) {
		Reports.driver = driver;
		return reports.createTest(testName, description);
	}
	
	public ExtentTest createNode(ExtentTest createTest, String msg) {
		ExtentTest chileNode = createTest.createNode(msg);
		return chileNode;
	}
	
	public static void log(ExtentTest createTest,String msg, ReportStatus reportStatus) throws IOException {
		
		String imagePath = System.getProperty("user.dir")+"/Reports/"+reportFolderName+"/Screenshot/";
		String path = System.getProperty("user.dir")+"/test-recordings/"+ScreenRecorderUtil.getVideoFileName();
		
		switch (reportStatus) {
		
		case BUSINESSSTEP:
			chileNode = createTest.createNode(msg);
		break;
		
		case VIDEO:
			createTest.log(Status.INFO, msg+"     "+"<a href = '"+path+"'><span class = 'label info'>Download Video</span></a>");
		break;
			
		case PASS:
			String imageName = takeFullScreenShot();		
			createTest.pass(msg, MediaEntityBuilder.createScreenCaptureFromPath(imagePath+imageName).build());
		break;
		
		case FAIL:
			String imageName1 = takeFullScreenShot();
			createTest.fail(msg, MediaEntityBuilder.createScreenCaptureFromPath(imagePath+imageName1).build());
		break;
		
		case Pass:
			String imageName2 = takeScreenShot();
			createTest.pass(msg, MediaEntityBuilder.createScreenCaptureFromPath(imagePath+imageName2).build());
		break;
		
		case Fail:
			String imageName3 = takeScreenShot();
			createTest.fail(msg, MediaEntityBuilder.createScreenCaptureFromPath(imagePath+imageName3).build());
		break;
		
		case pass:
			createTest.log(Status.PASS, msg);
		break;	
		
		case fail:
			createTest.log(Status.FAIL, msg);
		break;
		default:
			break;
		}
	}
	
	public void closeReport() {
		reports.flush();
	}
	
	public static String takeScreenShot() throws IOException {
		String imageName = "img_"+getRandomNumber()+".png";
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File("./Reports/"+reportFolderName+"/Screenshot/"+imageName); 
		FileUtils.copyFile(SrcFile, DestFile);
		return imageName;
	}
	
	public static String takeFullScreenShot() throws IOException {
		String imageName = "img_"+getRandomNumber()+".png";
		Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		ImageIO.write(fpScreenshot.getImage(),"PNG",new File("./Reports/"+reportFolderName+"/Screenshot/"+imageName));
		return imageName;
	}
	
	public static String getBase64Screenshot(String path) throws IOException {
		String encodedBase64 = null;
		try {
			File finalDestination = new File(path);
			FileInputStream fileInputStream =new FileInputStream(finalDestination);
	        byte[] bytes =new byte[(int)finalDestination.length()];
	        fileInputStream.read(bytes);
	        encodedBase64 = new String(Base64.encodeBase64(bytes));

	    }catch (FileNotFoundException e){
	        e.printStackTrace();
	    }
	    return "data:image/png;base64,"+encodedBase64;
	}
	
	public static long getRandomNumber() {
		Random random = new Random();
		return random.nextLong();
	}
}