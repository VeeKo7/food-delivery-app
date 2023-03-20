package com.fujitsu.fooddeliveryappserver.dao;

import com.fujitsu.fooddeliveryappserver.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Time;
import java.util.Optional;

/**
 * This code defines a Java interface named "WeatherRepo"
 * that extends the "JpaRepository" interface and defines
 * several methods that retrieve weather data from some underlying data source.
 * */

@Repository
public interface WeatherRepo extends JpaRepository<WeatherData, Integer> {

    Optional<WeatherData> findByWMOStationCode(Optional<Integer> wmoStationCode);

    String findByStationName(String nameOfStation);

    float findByAirTemp(float airTemp);

    float findByWindSpeed(float windSpeed);

    String findByWeatherPhenomenon(String weatherPhenomenon);

    Time findByObservationTimestamp(Time observationTimestamp);

    //void updateByWMOStationCode(int VMOStationCode);

    void deleteByWMOStationCode(int wmoStationCode);
}
