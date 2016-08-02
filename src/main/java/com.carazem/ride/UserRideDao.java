package com.carazem.ride;

import com.carazem.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRideDao extends JpaRepository<Ride, Long> {
    List<Ride> findByDriverId(Long id);
}
