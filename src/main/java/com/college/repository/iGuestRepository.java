package com.college.repository;

import com.college.domain.Guest;
import java.util.List;
import java.util.Map;

public interface iGuestRepository extends _RepositoryInterface<Guest, Integer> {
    List<Guest> findByName(String name);
}
