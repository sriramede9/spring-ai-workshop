package com.sri.spring_ai_workshop.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherTools {

    private final RestClient weatherClient;
    private final RestClient geoClient;

    public WeatherTools(RestClient.Builder builder) {
        this.weatherClient = builder.baseUrl("https://api.open-meteo.com/v1").build();
        this.geoClient = builder.baseUrl("https://geocoding-api.open-meteo.com/v1").build();
    }

    @Tool(description = "Get latitude and longitude for a city name (e.g., 'Toronto', 'Montreal')")
    public String getCityCoordinates(String cityName) {
        return geoClient.get()
                .uri(uri -> uri.path("/search").queryParam("name", cityName).queryParam("count", 1).build())
                .retrieve()
                .body(String.class);
    }

    @Tool(description = "Get the current weather forecast for a specific latitude and longitude")
    public String getWeather(double latitude, double longitude) {
        return weatherClient.get()
                .uri(uri -> uri.path("/forecast")
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("current_weather", true)
                        .queryParam("hourly", "temperature_2m,relative_humidity_2m")
                        .build())
                .retrieve()
                .body(String.class);
    }
}