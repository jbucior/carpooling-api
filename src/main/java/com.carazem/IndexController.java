package com.carazem;

import com.carazem.config.ConfigService;
import com.carazem.config.Keys;
import com.carazem.ride.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {

    @Autowired
    private ConfigService configService;

    @Autowired
    private RideService rideService;

    @RequestMapping("/")
    public String home() {
        System.out.println(configService.get(Keys.SERVER_PORT));

        return "Dziala backend!";
    }

}
