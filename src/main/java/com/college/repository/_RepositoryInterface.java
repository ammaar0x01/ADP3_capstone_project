/* Guest.java
Guest Repository Class
Author: Zaid Theunissen (221084142)
Date: 26 March 2025
*/

package com.college.repository;

import java.util.Map;

public interface _RepositoryInterface<T, ID> {

    T create(T t);
    T read(ID id);
    T update(T t);
    boolean delete(ID id);
    Map<ID, T> getAll();
}
