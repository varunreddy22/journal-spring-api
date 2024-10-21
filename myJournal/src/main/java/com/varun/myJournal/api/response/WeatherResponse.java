package com.varun.myJournal.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {


    private Current current;

    @Getter
    @Setter
    public class Current{

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescription;

        private int feelslike;
    }
    //@JsonProperty provides fine-grained control over how Java objects and
    // JSON fields map to each other, making it very useful for situations where
    // there is a mismatch between field names or when working with immutable objects.

    //Eg, weather_descriptions is a field in API's JSON response,
    //but we want to give different naming to that field in our code
    //So, we use @JsonProperty to map the JSON field in and java code.

    // If there is no difference in naming we do not use @JsonProperty
}
