package com.project.uber.UberApp.strategies;

import com.project.uber.UberApp.entities.RideRequest;



public interface RideFareCalculationStrategy {

    double RIDE_FARE_MULTIPLIER = 10;
    double calculateFare(RideRequest rideRequest);
}
