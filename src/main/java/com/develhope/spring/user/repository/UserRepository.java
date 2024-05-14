package com.develhope.spring.user.repository;

import com.develhope.spring.user.entity.User;
import com.develhope.spring.vehicles.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {

}
