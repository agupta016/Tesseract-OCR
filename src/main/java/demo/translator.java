package demo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class translator {

	public static void main(String[] args) {
	
		System.out.println("Enter input string");
		String text=null;
		  BufferedReader reader =  
                  new BufferedReader(new InputStreamReader(System.in)); 
        
       // Reading data using readLine 
       try {    	   
		    text = reader.readLine();
		    
		    //translate french : Bonjour le monde
		    System.out.println("Translated text: " + translate("fr", "gu", text));
		    
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	
	public static String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE%MCEPASTEBIN%
        String urlStr = "https://script.google.com/macros/s/AKfycbyrBkiMerl23bnHtwlgDOhd8KfHO8mrKFR85su2hN84BXNmmKY/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
       
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

}


