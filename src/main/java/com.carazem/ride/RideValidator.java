package com.carazem.ride;

import com.carazem.config.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Component
public class RideValidator implements Validator{

    @Autowired
    private RideService rideService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Ride.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cityFrom", Keys.RIDE_ORIGIN_EMPTY);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cityTo", Keys.RIDE_DESTINATION_EMPTY);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "seats", Keys.RIDE_SEATS_EMPTY);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", Keys.RIDE_PRICE_EMPTY);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rideDate", Keys.RIDE_DATE_EMPTY);

        Ride ride = (Ride) target;

        if(rideService.rideExists(ride)) {
            errors.reject(Keys.RIDE_EXISTS);
        }

        if(ride.getRideDate()!=null) {
            if (ride.getRideDate().toInstant().isBefore(Instant.now())) {
                errors.reject(Keys.RIDE_IN_THE_PAST);
            }
        }
    }
}
