package com.fujitsu.fooddeliveryappserver.dao;

import com.fujitsu.fooddeliveryappserver.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class WeatherDataDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertOrUpdateWeatherData(WeatherData weatherData) {
        String sql = "INSERT INTO weather_data(WMOStationCode, " +
                "stationName, airTemp, windSpeed, " +
                "weatherPhenomenon, observationTimestamp)"
                + "VALUES(?, ?, ?, ?)"
                + "ON DUPLICATE KEY UPDATE"
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
