package com.fujitsu.fooddeliveryappserver.controller;

import com.fujitsu.fooddeliveryappserver.dao.WeatherRepo;
import com.fujitsu.fooddeliveryappserver.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

/**
 * This class acts as a REST controller for a Spring-based web application.
 * The controller provides several endpoints for accessing and manipulating weather data.
 * The controller class is annotated with the "@RestController" annotation,
 * which indicates that this is a REST controller that can handle HTTP requests.
 * The controller layer is responsible for translating HTTP requests into method
 * calls on service layer, and then returning the appropriate HTTP
 * response based on the result of the method call
 * */

@RestController
public class WeatherController {

    /**
     * The class has a private field "weatherRepo" which is annotated with the "@Autowired"
     * annotation, which injects a dependency on the "WeatherRepo" interface. This dependency
     * is used to interact with the underlying data source that stores the weather data.
     * */
    @Autowired
    private WeatherRepo weatherRepo;

    /**
     * "saveWeatherData": accepts a POST request with a request body containing a JSON
     * representation of a "WeatherData" object, which is then saved to the underlying data source.
     * */
    @PostMapping("/saveWeatherData")
    public String saveWeatherData(@RequestBody WeatherData weatherData) {
        weatherRepo.save(weatherData);
        return "Weather data saved...";
    }

    /**
     * "getAll": accepts a GET request and returns a list of all
     * weather data stored in the underlying data source.
     * */
    @GetMapping("/getAllWeatherData")
    public List<WeatherData> getAll() {
        return weatherRepo.findAll();
    }

    /**
     * "getByWMOStationCode": accepts a GET request with a path variable "WMOStationCode"
     * and returns an integer representing the weather data for the station with the specified WMO station code.
     */
    @GetMapping("/getWeatherData/{WMOStationCode}")
    public Optional<WeatherData> getByWMOStationCode (@PathVariable Optional<Integer> WMOStationCode) {
        return weatherRepo.findByWMOStationCode(WMOStationCode);
    }

    /**
     * "getByNameOfStation": accepts a GET request with a path variable "nameOfStation"
     * and returns a string representing the weather data for the station with the specified name.
     * */
    @GetMapping("/getWeatherData/{stationName}")
    public String getByNameOfStation(@PathVariable String stationName) {
        return weatherRepo.findByStationName(stationName);
    }

    /**
     * "getWeatherDataByAirTemp": accepts a GET request with a path variable "airTemp"
     * and returns a float representing the weather data for the air temperature with the specified value.
     * */
    @GetMapping("/getWeatherData/{airTemp}")
    public float getWeatherDataByAirTemp(@PathVariable float airTemp) {
        return weatherRepo.findByAirTemp(airTemp);
    }

    /**
     * "getWeatherDataByWindSpeed": accepts a GET request with a path variable "windSpeed"
     * and returns a float representing the weather data for the wind speed with the specified value.
     * */
    @GetMapping("/getWeatherData/{windSpeed}")
    public float getWeatherDataByWindSpeed(@PathVariable float windSpeed) {
        return weatherRepo.findByWindSpeed(windSpeed);
    }

    /**
     * "getByWeatherPhenomenon": accepts a GET request with a path variable "weatherPhenomenon"
     * and returns a string representing the weather data for the specified weather phenomenon.
     * */
    @GetMapping("/getWeatherData/{weatherPhenomenon}")
    public String getByWeatherPhenomenon(@PathVariable String weatherPhenomenon) {
        return weatherRepo.findByWeatherPhenomenon(weatherPhenomenon);
    }

    /**
     * "getByObservationTimestamp": accepts a GET request with a path variable "observationTimestamp"
     * and returns a Time object representing the weather data for the specified observation timestamp.
     * */
    @GetMapping("/getWeatherData/observationTimestamp")
    public Time getByObservationTimestamp(@PathVariable Time observationTimestamp) {
        return weatherRepo.findByObservationTimestamp(observationTimestamp);
    }

    /**
     * PUT request will update the weatherData with specified WMOStationCode.
     * The controller method receives the WMOStationCode as a path variable,
     * and the weatherData as a requested body.
     * */
    @PutMapping("/updateWeatherData/{WMOStationCode}")
    public ResponseEntity<WeatherData> updateByWMOStationCode(@PathVariable int WMOStationCode,
                                                              @RequestBody WeatherData weatherData) {
        /*
        * It first checks if a weatherData with the specified WMOStationCode exists in the repository,
        * and returns a 404 response if it doesn't. If the weatherData exists, the controller updates its
        * weatherPhenomenon, airTemp, windSpeed, and saves it to the weatherRepo, and returns 200 response
        * with the updated weatherData.
        * */
        Optional<WeatherData> optionalWeatherData = weatherRepo.findByWMOStationCode(Optional.of(WMOStationCode));
        if (optionalWeatherData.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        WeatherData existingWeatherData = optionalWeatherData.get();
        existingWeatherData.setWeatherPhenomenon(weatherData.getWeatherPhenomenon());
        existingWeatherData.setAirTemp(weatherData.getAirTemp());
        existingWeatherData.setWindSpeed(weatherData.getWindSpeed());
        weatherRepo.save(existingWeatherData);
        return ResponseEntity.ok(existingWeatherData);
    }

    /**
     * Delete method deletes weatherData with specified WMOStationCode.
     * The controller method receives WMOStationCode as a path variable,
     * checks if weatherData with specified WMOStationCode exists,
     * and returns 404 if it doesn't and if it does, than controller
     * deletes it from the weatherRepo and returns 204 response with no content.
     * */
    @DeleteMapping("deleteWeatherData/{WMOStationCode}")
    public ResponseEntity<HttpStatus> deleteByWMOStationCode(@PathVariable(value = "WMOStationCode") int WMOStationCode) {
        try {
            weatherRepo.deleteByWMOStationCode(WMOStationCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
