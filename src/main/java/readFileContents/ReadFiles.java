package readFileContents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadFiles {
	
	private String filePath;
	private File file;
	private PDDocument document;
	
	public ReadFiles(String filePath) {
		this.filePath = filePath;
	}
	
	public String readPDFText(String fileName) throws IOException {
		//Loading an existing document
		file = new File(filePath+fileName+".pdf");
		document = PDDocument.load(file);
		//Instantiate PDFTextStripper class
		PDFTextStripper pdfStripper = new PDFTextStripper();
		//Retrieving text from PDF document
		String text = pdfStripper.getText(document);
		System.out.println(text);
		//Closing the document
		document.close();
		return text;
	}
	
	private void readImages() {
		
	}
	
	public List<String[]> readCSV(String fileNames) throws FileNotFoundException, IOException, CsvException {
		String fileName = filePath+fileNames+".csv";
		List<String[]> r;
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            r = reader.readAll();
            r.forEach(x -> System.out.println(Arrays.toString(x)));
        }
		return r;
	}
}