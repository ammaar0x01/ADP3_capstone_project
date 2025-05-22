/*  iHousekeeperService.java
    iHousekeeperService
    Author: Muaath Slamong (230074138)
    Date: 22 May 2025
*/

package com.college.service;

import com.college.entity.Housekeeper;
import java.util.List;
import java.util.Optional;

public interface IHousekeeperService {
    Housekeeper create(Housekeeper housekeeper);
    Housekeeper update(Housekeeper housekeeper);
    void delete(Integer id);
    Optional<Housekeeper> findById(Integer id);
    List<Housekeeper> getAll();
}

