package com.college.service;

public interface IService <T,ID> {
    T create(T t);

    void delete(ID id);

    void update(T t);

    T read(ID id);
}
