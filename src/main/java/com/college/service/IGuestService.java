package com.college.service;

import com.college.domain.Guest;

import java.util.List;

public interface IGuestService extends IService<Guest, Integer> {

    List<Guest> getAll();

}
