/*  iService.java
    iService
    Author: Muaath Slamong (230074138)
    Date: 22 May 2025
*/


package com.college.service;

public interface IService  <H, ID>{

    H create(H t);

    H read(ID id);

    H update(H t);

    boolean delete(ID id);
}
