package com.carazem.ride.dto;

import com.carazem.ride.Ride;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

public class SearchResponseDto {

    private String cityFrom;
    private String cityTo;
    private java.sql.Timestamp rideDate;
    private String driverName;
    private String driverSurname;
    private BigInteger price;

    public SearchResponseDto(String cityFrom, String cityTo, Timestamp rideDate, String driverName, String driverSurname, BigInteger price) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.rideDate = rideDate;
        this.driverName = driverName;
        this.driverSurname = driverSurname;
        this.price = price;
    }

    public SearchResponseDto(Ride ride){
        this.cityFrom = ride.getCityFrom();
        this.cityTo = ride.getCityTo();
        this.rideDate = ride.getRideDate();
        this.price = ride.getPrice();
        this.driverName = ride.getDriver().getFirstName();
        this.driverSurname = ride.getDriver().getSecondName();
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public Timestamp getRideDate() {
        return rideDate;
    }

    public void setRideDate(Timestamp rideDate) {
        this.rideDate = rideDate;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverSurname() {
        return driverSurname;
    }

    public void setDriverSurname(String driverSurname) {
        this.driverSurname = driverSurname;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }
}
