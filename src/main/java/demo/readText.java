package demo;

import java.io.File;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class readText {
	
	
	public static void text(String imgPath) {
		File file= new File(imgPath);
		
		try {
		Tesseract tess = new Tesseract();	
		tess.setDatapath("tessdata");
		tess.setLanguage("eng");
		String text = tess.doOCR(file);
		
		System.out.println("==========================================================");
		System.out.println(text);
		}catch(TesseractException e)
		{
			e.printStackTrace();
		}
				
	}
	
	public static void main(String[] args) throws TesseractException {
		
		String imgPath = "src/main/resources/images/receipt.png";
		text(imgPath);
		
	}
}
