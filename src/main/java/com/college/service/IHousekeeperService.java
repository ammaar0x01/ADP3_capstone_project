/*  iHousekeeperService.java
    iHousekeeperService
    Author: Muaath Slamong (230074138)
    Date: 22 May 2025
*/

package com.college.service;

import com.college.domain.Housekeeper;
import java.util.List;

public interface IHousekeeperService extends IService<Housekeeper, Integer> {
    Housekeeper findById(Integer id);

    List<Housekeeper> getAll();

}

