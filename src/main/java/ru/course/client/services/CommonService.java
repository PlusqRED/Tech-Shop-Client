package ru.course.client.services;

import java.util.List;

public interface CommonService<T> {

    void deleteById(Long id);

    List<T> findAll();

    void save(T model);
}
