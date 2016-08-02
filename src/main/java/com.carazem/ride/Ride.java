package com.carazem.ride;

import com.carazem.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "rides")
@ToString
public class Ride {

    public Ride() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ride_id")
    private Long id;

    @Column(name = "city_from")
    private String cityFrom;

    @Column(name = "city_to")
    private String cityTo;

    @Column(name = "ride_date")
    private Timestamp rideDate;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    @Column(name = "seats")
    private int seats;

    @Column(name = "price")
    private BigInteger price;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_ride", joinColumns = { @JoinColumn(name = "ride_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private List<User> passangerList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getPassangerList() {
        return passangerList;
    }

    public void setPassangerList(List<User> passangerList) {
        this.passangerList = passangerList;
    }
}

