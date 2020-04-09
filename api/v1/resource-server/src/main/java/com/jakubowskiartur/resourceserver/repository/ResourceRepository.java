package com.jakubowskiartur.resourceserver.repository;

import com.jakubowskiartur.resourceserver.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}
