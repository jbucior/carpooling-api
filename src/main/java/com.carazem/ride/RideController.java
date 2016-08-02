package com.carazem.ride;

import com.carazem.ride.dto.SearchRequestDto;
import com.carazem.ride.dto.SearchResponseDto;

import com.carazem.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping(value = "/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @Autowired
    private RideValidator rideValidator;

    @RequestMapping(method = GET)
    public List<SearchResponseDto> search(SearchRequestDto searchRequestDto, Pageable pageable) {
        return rideService.searchRides(searchRequestDto, pageable);
    }

    @RequestMapping(method = POST)
    public void addRide(@RequestBody @Validated Ride ride) {
         rideService.addRide(ride);
    }

    @InitBinder("ride")
    public void initRideValidator(WebDataBinder binder) {
        binder.addValidators(rideValidator);
    }

    @RequestMapping(value = "/{id}", method = PUT)
    public void assignToRide(@PathVariable Long id) {
        System.out.println("PUT");
        rideService.assignToRide(id);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void unassignToRide(@PathVariable Long id) {
        System.out.println("DELETE");
        rideService.unassingFromRide(id);
    }

}
