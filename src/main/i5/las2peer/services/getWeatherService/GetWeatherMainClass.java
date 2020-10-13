package i5.las2peer.services.getWeatherService;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import i5.las2peer.api.ManualDeployment;
import i5.las2peer.restMapper.RESTService;
import i5.las2peer.restMapper.annotations.ServicePath;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import com.google.gson.Gson;

// TODO Describe your own service
/**
 * las2peer-Template-Service
 * 
 * This is a template for a very basic las2peer service that uses the las2peer WebConnector for RESTful access to it.
 * 
 * Note: If you plan on using Swagger you should adapt the information below in the SwaggerDefinition annotation to suit
 * your project. If you do not intend to provide a Swagger documentation of your service API, the entire Api and
 * SwaggerDefinition annotation should be removed.
 * 
 */
// TODO Adjust the following configuration
@Api
@SwaggerDefinition(
		info = @Info(
				title = "las2peer Template Service",
				version = "1.0.0",
				description = "A las2peer Template Service for demonstration purposes.",
				termsOfService = "http://your-terms-of-service-url.com",
				contact = @Contact(
						name = "Tran Lan Anh",
						url = "https://las2peer.org",
						email = "tran@dbis.rwth-aachen.de"),
				license = @License(
						name = "your software license name",
						url = "http://your-software-license-url.com")))
@ServicePath("weather")
@ManualDeployment
// TODO Your own service class
public class GetWeatherMainClass extends RESTService {
	
	private String appid;
	
	public GetWeatherMainClass() {
		
		setFieldValues();
	}

	/**
	 * Template of a get function.
	 * @return Returns an HTTP response with the username as string content.
	 */
	
	// Get weather's info of a city 
	@GET
	@Path("/getTemp/{city}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWeather(@PathParam("city") String city) {
				
		String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + appid;
		Request requestUrl = new Request.Builder().url(url).build();			  			  
		OkHttpClient client = new OkHttpClient();
		Gson gsonObj = new Gson();		
		WeatherInfo result = null;
		String onAction = "retrieving HTML";		
		
		try {	
				okhttp3.Response resp = client.newCall(requestUrl).execute();		    
		        ResponseBody info = resp.body();		        
		        result = gsonObj.fromJson(info.string(), WeatherInfo.class);				
				return Response.status(Status.OK).entity(result).build();
				
		} catch (Exception e) {
		        e.printStackTrace();
		        return internalError(onAction);
		}	     
	}

	// return response's error 
	private Response internalError(String onAction) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Internal error while " + onAction + "!")
					.type(MediaType.TEXT_PLAIN).build();
	}
	
}