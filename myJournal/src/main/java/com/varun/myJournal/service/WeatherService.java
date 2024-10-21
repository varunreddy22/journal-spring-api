package com.varun.myJournal.service;

import com.varun.myJournal.api.response.WeatherResponse;
import com.varun.myJournal.cache.AppCache;
import com.varun.myJournal.constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//@Component
@Service
public class WeatherService {

  //private static final String apiKey = "68ad515167082ca1886b526ebe648136";

    @Value("${weather.api.key}")
    private String apiKey;

 // private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private AppCache appCache;


  public WeatherResponse getWeather(String city){
      //String finalAPI = API.replace("CITY",city).replace("API_KEY", apiKey);
      //String finalAPI = appCache.appCacheMap.get("weather_api").replace("<city>",city).replace("<apiKey>", apiKey);
      String finalAPI = appCache.appCacheMap.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.CITY,city).replace(PlaceHolders.API_KEY, apiKey);
      ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
      WeatherResponse body = response.getBody();
      return body;
  }
}

//give the necessary apiKey and API that you want to use in this code in apiKey = "", API = ""


//      String finalAPI = API.replace("CITY",city).replace("API_KEY", apiKey);

//Since the above API gets the data in JSON formal, but we want the data in java object to use in this code.
//So we should convert the data from JSON to POJO(Plain Old Java Object).
// We can convert the JSON to POJO online in web. For that we just need
// to check what JSON schema the given API returns.
//For that we created a package called api.response in this project

//we created a class called "WeatherResponse", that contains the POJO that is obtained
// by converting the API's JSON response
//Simply the WeatherResponse class is a response type that is returned by this API


//RestTemplate is a class provided by Spring Framework that allows you to make
// HTTP requests to RESTful web services and interact with them from your Spring Boot
// or Spring MVC applications.

//   restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);

//the exchange method takes the following inputs:
// API that needs to be hit
//type of HTTP request: i.e GET or POST or UPDATE etc.
//does it have any headers(if no headers give null)

//the last argument tells this method to convert the incoming JSON object
// into corresponding java object(WeatherResponse.class)
// ---This is also called De-serialization


// NOTE: In order to use the restTemplate, we need to provide the implementation of it.
//This can be done in main class "JournalApplication.java"


//@Value("${weather.api.key}")
//The @Value annotation in Spring is used for injecting values into fields in Spring-managed beans. It allows you to set values from different sources such as properties files, system environment variables, command-line arguments, or default values. This is especially useful for configuring your application by externalizing the configuration to an application.properties, application.yml, or environment variables.
//Since we do not want to hard code the api key inside the code
//We can use @Value annotation, which can be used to hold the value of a field
//To do that, first we need to add "weather.api.key=our_api_key_value" in the application.properties file
//then we can annotate @Value to access that value.

//when we are using @Value for a field, that field should not be declared as "static"
//because static variables are properties of a class, but not its instances.
//Since Spring’s dependency injection happens when the bean is instantiated.
// Since static fields exist at the class level and are shared across all instances, Spring doesn’t control or manage them. As a result, the value may not get injected properly into static fields.


//AppCache

//There is one more change we have done one more change in this code,
//i.e removing the hard coded api and do some configuration.
//We can do the configuration in two ways
// 1)add api to application.properties(like we did for apiKey)
//or 2) add api to db

//We choose to add the api to db. This can be done through ApplicationCache.
//Here first we create a collection "config_journal_app" in db, which will have the configurations.
//the "config_journal_app" collection will have the weather api.

//We should also create a repository "ConfigJournalAppRepository" and entity "ConfigJournalAppEntity"
// to access this collection "config_journal_app"

//The main purpose of using AppCache is that,
//Calling 'config_journal_app" collection in getWeather()
//will have to hit the mongodb first to get the api, which creates an extra latency
//So instead of calling the db, we can create an AppCache Bean with a @PostConstruct method init
//the @PostConstruct method init() initializes when ever the AppCache Bean is created.


//instead of hard coding the values like "weather-api", <city>, <apiKey>
//we are making use of "enum" in AppCache, and Placeholders interface
//this is a good coding practice to utilize constants like these.
