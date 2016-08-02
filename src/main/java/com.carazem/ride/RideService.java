package com.carazem.ride;

import com.carazem.auth.SecurityService;
import com.carazem.ride.dto.SearchRequestDto;
import com.carazem.ride.dto.SearchResponseDto;
import com.carazem.user.User;
import com.carazem.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RideService {

    @Autowired
    private RideDao rideDao;

    @Autowired
    private UserRideDao userRideDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private SecurityService securityService;

    public List<SearchResponseDto> searchRides(SearchRequestDto searchRequestDto, Pageable pageable) {
        if (searchRequestDto.getUserId() == null) {
            return rideDao.findByCityIgnoreCaseFromAndCityToAndRideDateGreaterThan(searchRequestDto.getCityFrom(), searchRequestDto.getCityTo(), searchRequestDto.getRideDate(), pageable)
                    .stream().map(SearchResponseDto::new).collect(toList());
        }
        return userRideDao.findByDriverId(searchRequestDto.getUserId())
                .stream().map(SearchResponseDto::new).collect(toList());
    }

    public Ride addRide(Ride ride) {
        ride.setDriver(userDao.getOne(securityService.currentUserId()));
        rideDao.save(ride);
        return ride;
    }

    public boolean rideExists(Ride ride) {
        ride.setDriver(userDao.getOne(securityService.currentUserId()));
        Ride fetched = rideDao.findByDriverIdAndRideDate(ride.getDriver().getId(), ride.getRideDate());
        return fetched!=null;
    }


    public void assignToRide(long id) {
        Ride ride = rideDao.findOne(id);
        User passanger = userDao.findOne(securityService.currentUserId());
        if(!ride.getPassangerList().contains(passanger)&& ride.getDriver()!=passanger && ride.getSeats() > ride.getPassangerList().size()) {
            ride.getPassangerList().add(passanger);
            rideDao.save(ride);
            System.out.println("wewnętrzna");
        }
        System.out.println("zewnętrzna");
    }

    public void unassingFromRide(long id) {
        Ride ride = rideDao.findOne(id);
        User passanger = userDao.findOne(securityService.currentUserId());
        if(ride.getPassangerList().contains(passanger)) {
            ride.getPassangerList().remove(passanger);
            rideDao.save(ride);
        }
    }

//    public List<User> showRideUser() {
//        Ride ride = rideDao.findOne(1L);
//        System.out.println(ride.getPassangers().toString());
//        List<User> users = ride.getPassangers();
//        return users;
//    }
}