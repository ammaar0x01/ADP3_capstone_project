/* GuestRepository.java
Guest Repository Class
Author: Zaid Theunissen (221084142)
Date: 26 March 2025
*/
package com.college.repository;

import com.college.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
}