/*  HousekeeperRepository.java
    Housekeeper Repository
    Author: Muaath Slamong (230074138)
    Date: 22 May 2025
*/
package com.college.repository;

import com.college.domain.Housekeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HousekeeperRepository extends JpaRepository<Housekeeper, Integer> {
}

