package com.college.repository;

import com.college.entity.Housekeeper;
import java.util.ArrayList;

public interface IHousekeeperRepository extends _RepositoryInterface<Housekeeper, Integer> {
    ArrayList<Housekeeper> getAll();
}
