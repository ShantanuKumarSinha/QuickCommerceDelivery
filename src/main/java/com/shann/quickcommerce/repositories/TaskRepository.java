package com.shann.quickcommerce.repositories;

import com.shann.quickcommerce.entities.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TaskRepository is a Spring Data JPA repository for managing Task entities.
 * It provides methods to perform CRUD operations and custom queries on Task data.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * Finds tasks by their IDs.
     *
     * @param taskIds the list of task IDs to search for
     * @return a list of tasks matching the provided IDs
     */
  List<Task> findTasksByIdIn(List<Long> taskIds);
}
