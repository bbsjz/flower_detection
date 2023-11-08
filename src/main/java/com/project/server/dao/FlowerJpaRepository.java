package com.project.server.dao;

import com.project.server.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FlowerJpaRepository extends JpaRepository<Flower,Integer> {
    @Transactional
    @Modifying
    @Query(value="select * from flower where name_zh like %:name% or name_en like %:name%",nativeQuery = true)
    List<Flower> findByNameLike(@Param("name") String name);
}
