import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * Dictionary class instantiates a HashMap by importing an Excel file of archaic words and separating out its rows and columns 
 * into Keys and Values that are then stored into a HashMap named dictionary. It also has methods that print out the HashMap, as well
 * as printing out select Keys, along with their values and the HashKey created for it by the Java HashMap API. 
 * This class imports and utilizes the Apache POI development tools in order to import and utilize an Excel file. You must have these tools added to
 * the Java Build Path library of this Java Project in order for it to properly execute.  
 * @author Chris Burkhead
 * Version 1.0
 * CS215-ON
 * Fall 2022 
 *
 */

public class Dictionary {
	
	//declaring HashMap as dictionary
	public static HashMap<String, String> dictionary=new HashMap<String, String>();
	
	public Dictionary() {
		
	}//empty argument constructor
	
	/**
	 * getHashMapData() loads the desired Excel file and changes it into a readable format for Java.
	 * It then determines how many rows it contains and stores that as variable lastRowNum, which it uses to determine how many times a for loop 
	 *  will execute. Each time the for loop executes it separates the two columns of data into cells which are then saved as 
	 *  String variables named Key and Value. Last the Strings of Key and Value are saved into the HashMap dictionary using the put(). After the 
	 *  for loop has finished executing the method returns the fully instantiated HashMap dictionary    
	 * @return dictionary
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static HashMap<String, String> getHashMapData() throws IOException {
		
		//loading the Excel file into the project then changing it to a readable JAVA format and determining how many rows it contains
		try {
			FileInputStream  archaicWords=new FileInputStream("C:\\Users\\chris\\eclipse-workspace\\Assignment5.1_HashMap_CMB\\DictionaryWordValuePairs.xlsx");
			Workbook wordBook=new XSSFWorkbook(archaicWords);
			Sheet sheet1=wordBook.getSheetAt(0);
			int lastRowNum=sheet1.getLastRowNum();
			
			 //for loop instantiates the dictionary HashMap until all rows of the file have been iterated through 
			 for (int i=0;i<=lastRowNum;i++) {
				 Row row=sheet1.getRow(i);
				 Cell keyCell=row.getCell(0);
				 String key=keyCell.getStringCellValue().trim();
				 
				 Cell valueCell=row.getCell(1);
				 String value=valueCell.getStringCellValue().trim();
				 dictionary.put(key, value);
			 }//end for loop
		}//end try
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		return dictionary;
		
	}//end getHashMapData
	
    /**
     * printHashMap() instantiates a new HashMap called printDictionary with the keys and Values of the 
     * dictionary HashMap. It uses a for each loop to iterate through each element of the HashMap and prints it to the screen
     */
	public static void printHashMap() {
		
		try {
			HashMap<String, String> printDictionary=getHashMapData();
			
			//for each loop prints the Key and Value for each element in the HashMap
			for ( Entry<String, String> hashMap:printDictionary.entrySet()) {
					System.out.println("Word: "+hashMap.getKey()+" - Definition = "+hashMap.getValue());
			}//end for loop
		
		}//end try 
		
		catch (IOException e) {
			e.printStackTrace();
		}//end catch IOException
		
	}//end printHashMap
	
	
	/**
	 * takeFive() takes five Keys from the dictionary HashMap and using the Java API get() method for HashMaps retrieves the value for that key.
	 * It also uses the hashCode() method from the Java API to retrieve the Hash key that was created from the key for that value. 
	 * All elements are then printed to the screen. 
	 */
	public static void takeFive() {
		
		System.out.println();
		System.out.println("caducity: "+dictionary.get("caducity")+". HashKey= "+"caducity".hashCode());
		System.out.println("picaroon: "+dictionary.get("picaroon")+". HashKey= "+"picaroon".hashCode());
		System.out.println("crumpet: "+dictionary.get("crumpet")+". HashKey= "+"crumpet".hashCode());
		System.out.println("moil: "+dictionary.get("moil")+". HashKey= "+"moil".hashCode());
		System.out.println("small beer: "+dictionary.get("small beer")+". HashKey= "+"small beer".hashCode());
	}//end takeFive
	
}//end class
