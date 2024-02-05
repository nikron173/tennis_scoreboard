package com.nikron.tennis.repository;

import java.util.List;
import java.util.Optional;

public interface Repository <K, E>{
    Optional<E> findById(K id);
    List<E> findAll();
    void delete(K id);
    E update(K id, E e);

    E save(E e);
}
