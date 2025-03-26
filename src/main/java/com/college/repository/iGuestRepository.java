/* Guest.java
Guest Repository Class
Author: Zaid Theunissen (221084142)
Date: 26 March 2025
*/

package com.college.repository;

import com.college.domain.Guest;
import java.util.List;
import java.util.Map;

public interface iGuestRepository extends _RepositoryInterface<Guest, Integer> {
    List<Guest> findByName(String name);
}
