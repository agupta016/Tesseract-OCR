package demo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


public class CaptureImage {

	public static void main(String[] args) throws Exception {
		
		String text=null;
		WebDriver driver = new ChromeDriver();
	
		String url = "https://www.lemonde.fr/";
		driver.get(url);		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);	
		
		driver.findElement(By.xpath("//*[@class='article article--main'] / a[1]")).click();
	    WebElement ele = driver.findElement(By.xpath("//article[@class=\"article__content  old__article-content-single\"]"));
	    
	    
        // get the entire screenshot from the driver of passed WebElement
        BufferedImage img = new AShot().coordsProvider(new WebDriverCoordsProvider())
        		               
        		               .shootingStrategy(ShootingStrategies.viewportPasting(100)) 
        		               .addIgnoredElement(By.cssSelector("#Nav"))
        		               .addIgnoredElement(By.xpath("//section[@class='paywall']"))
        		               .takeScreenshot(driver,ele).getImage();

        //      File srcPath = ((TakesScreenshot)driver.getScreenshotAs(OutputType.FILE);
        //    BufferedImage img = ImageIO.read(srcPath); 

        
		driver.close();
		driver.quit();
		
		// tesseract 
		try {
			Tesseract tess = new Tesseract();	
			tess.setDatapath("tessdata");
			tess.setLanguage("fra");
		    text = tess.doOCR(img);
			
			System.out.println("==========================================================");
			System.out.println(text);
			}catch(TesseractException e)
			{
				e.printStackTrace();
			}
		
				
		System.out.println("--------------------------------------------------------");
		System.out.println("============Transalte into english ================================");
		String tranText = translator.translate("fr", "en", text);
		tranText = tranText.replaceAll("[^A-Za-z0-9\\s\\.\\,]", "");
		String a[] = tranText.split("[.]");
		for(String str:a)
			System.out.println(str);
	
		// save file
		String tarPath = System.getProperty("user.dir") + "/src/main/resources/images/demo1.png";
		 
		ImageIO.write(img,"png",new File(tarPath)); 

	}

}
