/*  IHousekeeperRepository.java
    IHousekeeper Repository
    Author: Muaath Slamong (230074138)
    Date: 23 March 2025
*/

package com.college.repository;

import com.college.entity.Housekeeper;
import java.util.ArrayList;

public interface IHousekeeperRepository extends _RepositoryInterface<Housekeeper, Integer> {
    ArrayList<Housekeeper> getAll();
}
