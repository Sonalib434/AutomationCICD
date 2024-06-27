package selenium.pageobjectmodel.Tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataReader {

	@Test
	public void getJsonData() throws IOException {
		String json = FileUtils.readFileToString(new File(
				"C:\\Users\\sonbhatt\\eclipse-workspace\\SeleniumFramworkDesign\\src\\test\\java\\selenium\\pageobjectmodel\\TestData\\PurchaseOrder.json"),
				StandardCharsets.UTF_8);

		// Parse the JSON file
		JSONObject object = (JSONObject) JSONValue.parse(new FileReader(json));

		// Get the JSON array
		System.out.println(object);
	}

	@Test
	public void readTestData() throws StreamReadException, DatabindException, IOException {
		
			ObjectMapper objectMapper = new ObjectMapper();

			File file = new File("C:\\Users\\sonbhatt\\eclipse-workspace\\SeleniumFramworkDesign\\src\\test\\java\\selenium\\pageobjectmodel\\TestData\\PurchaseOrder.json");

			List<Map<String, String>> testData = objectMapper.readValue(file, new TypeReference<List<Map<String, String>>>() {
			});

			System.out.println(testData);
			//return testData;

		
	}
}
