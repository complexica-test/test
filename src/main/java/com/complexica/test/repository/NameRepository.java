package com.complexica.test.repository;

import com.complexica.test.model.NameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NameRepository extends JpaRepository<NameEntity, Long> {

    @Query("select ne from NameEntity ne")
    List<NameEntity> findAll();

}
