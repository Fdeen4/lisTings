package com.example.demo;
import org.springframework.data.repository.CrudRepository;

public interface LisTingsRepository extends CrudRepository<LisTings, Long>  {
    Iterable<LisTings> findAllByOrderByDateDesc();
}
