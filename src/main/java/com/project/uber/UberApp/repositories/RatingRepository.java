package com.project.uber.UberApp.repositories;

import com.project.uber.UberApp.entities.Driver;
import com.project.uber.UberApp.entities.Rating;
import com.project.uber.UberApp.entities.Ride;
import com.project.uber.UberApp.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);
}

