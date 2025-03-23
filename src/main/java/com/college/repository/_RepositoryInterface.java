package com.college.repository;
import com.college.domain.Guest;
import java.util.Map;

public interface iRepository<T, ID> {

    T create(T entity);
    T read(ID id);
    T update(T entity);
    boolean delete(ID id);
    Map<ID, T> getAll();
}
