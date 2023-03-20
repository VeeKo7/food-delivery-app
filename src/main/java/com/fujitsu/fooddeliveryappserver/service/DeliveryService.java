package com.fujitsu.fooddeliveryappserver.service;

import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * In this service we define a REST service endpoint for
 * calculating the delivery fee based on the input parameters
 * 'city', 'vehicleType', and 'weatherConditions'.
 * */
/*
* Java API for RESTful Web Services
* */
@Service
@Path("/delivery")
public class DeliveryService {

    @GET
    @Path("/calculateFee")
    /*
    * annotation used to specify the media type
    * response returned by a resource,
    * application/json used to exchange data in JSON format
    * */
    @Produces(MediaType.APPLICATION_JSON)
    /*
    * @QueryParam extracts query parameters from the URI
    * of a resource method
    * */
    public Response calculateDeliveryFee(@QueryParam("city") String city,
                                         @QueryParam("vehicleType") String vehicleType,
                                         @QueryParam("weatherCondition") String weatherCondition) {
        double regionalBaseFee = getRegionalBaseFee(city, vehicleType);
        double extraFee = getExtraFee(weatherCondition);
        double totalFee = regionalBaseFee + extraFee;

        // return the total fee as a JSON object
        return Response.ok("{\"totalFee\": " + totalFee + "}").build();
    }

    private double getExtraFee(String weatherCondition) {
        //TODO: logic
        return 0.0;
    }

    /**
     * calculate the regional base fee based on the business rules
     * */
    private double getRegionalBaseFee(String city, String vehicleType) {
        double regionalBaseFee = 0.0;

        if (city.equalsIgnoreCase("Tallinn")) {
            if (vehicleType.equalsIgnoreCase("car")) {
                regionalBaseFee = 4.0;
            } else if (vehicleType.equalsIgnoreCase("scooter")) {
                regionalBaseFee = 3.5;
            } else if (vehicleType.equalsIgnoreCase("bike")) {
                regionalBaseFee = 3.0;
            }
        } else if (city.equalsIgnoreCase("Tartu")) {
            if (vehicleType.equalsIgnoreCase("car")) {
                regionalBaseFee = 3.5;
            } else if (vehicleType.equalsIgnoreCase("scooter")) {
                regionalBaseFee = 3.0;
            } else if (vehicleType.equalsIgnoreCase("bike")) {
                regionalBaseFee = 2.5;
            }
        } else if (city.equalsIgnoreCase("Pärnu")) {
            if (vehicleType.equalsIgnoreCase("car")) {
                regionalBaseFee = 3.0;
            } else if (vehicleType.equalsIgnoreCase("scooter")) {
                regionalBaseFee = 2.5;
            } else if (vehicleType.equalsIgnoreCase("bike")) {
                regionalBaseFee = 2.0;
            }
        }

        return regionalBaseFee;
    }

    /**
     * calculate any extra fees based on weather data from the database
     * (which is currecnlty not implemented in this code)
     * */
    private double getExtraFee(String weatherCondition,
                               @QueryParam("vehicleType") String vehicleType,
                               @QueryParam("airTemperature") float airTemperature,
                               @QueryParam("windSpeed") float windSpeed) {
        double extraFee = 0.0;

        /*
        * we first check if the vehicle type is scooter or bike, since the extra fee is only applicable for those types
        * */
        if (vehicleType.equalsIgnoreCase("scooter") || vehicleType.equalsIgnoreCase("bike")) {
            // Calculate extra fee based on air temperature
            if (airTemperature < -10) {
                extraFee += 1.0;
            } else if (airTemperature >= -10 && airTemperature < 0) {
                extraFee += 0.5;
            }


            /*
            * Calculate extra fee based on weather phenomenon
            * If the weather condition is glaze, hail, or thunder,
            * we throw a RuntimeException with an error message
            * indicating that the selected vehicle type is forbidden.
            * this code assumes that the air temperature and wind speed
            * values are already retrieved from the database and stored in variables
            * */
            if (weatherCondition.equalsIgnoreCase("snow") || weatherCondition.equalsIgnoreCase("sleet")) {
                extraFee += 1.0;
            } else if (weatherCondition.equalsIgnoreCase("rain")) {
                extraFee += 0.5;
            } else if (weatherCondition.equalsIgnoreCase("glaze") || weatherCondition.equalsIgnoreCase("hail") || weatherCondition.equalsIgnoreCase("thunder")) {
                throw new RuntimeException("Usage of selected vehicle type is forbidden");
            }

            // Calculate extra fee based on wind speed
            if (vehicleType.equalsIgnoreCase("bike")) {
                if (windSpeed >= 10 && windSpeed < 20) {
                    extraFee += 0.5;
                } else if (windSpeed >= 20) {
                    throw new RuntimeException("Usage of selected vehicle type is forbidden");
                }
            }
        }
        return extraFee;
    }
}

