package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException {
		System.out.println(req);
		if (req==null) {
			PrintStream logs =  new PrintStream(new FileOutputStream("logging.txt"));
			//RestAssured.baseURI="https://rahulshettyacademy.com";
			 req =  new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUrl")).addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(logs))
					.addFilter(ResponseLoggingFilter.logResponseTo(logs))
					.build();
			return req;
		}
		
		return req;
		
	}
	
	
	public String getGlobalValues(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("D:\\workspace\\eclipse2018\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	
	public String getJsonPath(Response resp, String key) {
		String response = resp.asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		return js.get(key).toString();
	}
	
}
