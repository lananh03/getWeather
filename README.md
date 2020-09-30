
### las2peer-GetWeather-Service

This service is based on las2peer and can be used to get weather information of a city using weather API "openweathermap.io".  

<p>The result returned in JSON form and can be display in HTML form on a web frontend.</p>
<p>There is an input box allowing user enter the name of the city he/she wants to know about the weather. The web frontend will then display name and temperature of the respective city.  
</p>

#### Build Service
Build the project with the following command 
<p>'ant all'</p>

### Start Service
To start GetWeather Service, run the start-script from the bin/ directory

/bin/start_network.bat

Once it start successfully, the getWeather service is available at the link as follow:

http://localhost:8080/weather/

The result in json form will be available at:

http://localhost:8080/weather/getTemp/{city name}



