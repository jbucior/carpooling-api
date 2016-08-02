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
    @Setter
    @Getter
    private Long id;

    @Column(name = "city_from")
    @Setter
    @Getter
    private String cityFrom;

    @Column(name = "city_to")
    @Setter
    @Getter
    private String cityTo;

    @Column(name = "ride_date")
    @Setter
    @Getter
    private Timestamp rideDate;

    @OneToOne
    @JoinColumn(name = "driver_id")
    @Setter
    @Getter
    private User driver;

    @Column(name = "seats")
    @Setter
    @Getter
    private int seats;

    @Column(name = "price")
    @Setter
    @Getter
    private BigInteger price;

    @Column(name = "description")
    @Setter
    @Getter
    private String description;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_ride", joinColumns = { @JoinColumn(name = "ride_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
    @Setter
    @Getter
    private List<User> passangerList;
}

