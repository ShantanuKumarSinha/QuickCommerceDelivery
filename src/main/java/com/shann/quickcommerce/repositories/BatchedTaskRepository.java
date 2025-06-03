package com.shann.quickcommerce.repositories;

import com.shann.quickcommerce.entities.BatchedTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** BatchedTaskRepository is a Spring Data JPA repository for managing BatchedTask entities. */
@Repository
public interface BatchedTaskRepository extends JpaRepository<BatchedTask, Long> {}
