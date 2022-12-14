package com.aston.carservice.service;

import java.util.List;

public interface Service<T, V, I> {

    T getById(I id);

    List<T> getAll();

    T create(V newObject);

    T update(I id, V newObject);

    boolean delete(I id);

}
