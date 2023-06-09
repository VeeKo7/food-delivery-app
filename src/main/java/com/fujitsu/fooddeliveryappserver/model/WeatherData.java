package com.fujitsu.fooddeliveryappserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Time;

/**
 * class represents
 * the data we want
 * to fetch from URL
 * */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "weather_data")
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int WMOStationCode;
    private String stationName;
    private float airTemp;
    private float windSpeed;
    private String weatherPhenomenon;
    private Time observationTimestamp;
}