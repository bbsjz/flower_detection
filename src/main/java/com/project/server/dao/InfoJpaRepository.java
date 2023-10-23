package com.project.server.dao;

import com.project.server.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoJpaRepository extends JpaRepository<Info,Integer> {
}
