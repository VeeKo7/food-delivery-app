package com.fujitsu.fooddeliveryappserver.dao;

import com.fujitsu.fooddeliveryappserver.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * DAO manages weather data in a database
 * */

public class WeatherDataDao {

    //simplifies database access
    private JdbcTemplate jdbcTemplate;

    /**
     * setter - injects jdbcTemplate into WeatherDAO
     * */
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * updates weather data in the database
     * depending on whether the record already
     * exists or not
     * */
    public void insertOrUpdateWeatherData(WeatherData weatherData) {
        String sql = "INSERT INTO weather_data(WMOStationCode, " +
                "stationName, airTemp, windSpeed, " +
                "weatherPhenomenon, observationTimestamp)"
                + "VALUES(?, ?, ?, ?, ?, ?),"
                + "ON DUPLICATE KEY UPDATE,"
                + " airTemp = VALUES(airTemp),"
                + " windSpeed = VALUES(windSpeed),"
                + " weatherPhenomenon = VALUES(weatherPhenomenon)";

        jdbcTemplate.update(sql,
                weatherData.getWMOStationCode(),
                weatherData.getStationName(),
                weatherData.getAirTemp(),
                weatherData.getWindSpeed(),
                weatherData.getWeatherPhenomenon(),
                weatherData.getObservationTimestamp());
    }
}
