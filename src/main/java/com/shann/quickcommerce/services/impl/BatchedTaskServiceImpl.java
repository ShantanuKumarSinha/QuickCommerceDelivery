package com.shann.quickcommerce.services.impl;

import com.shann.quickcommerce.adapters.MapsAdapter;
import com.shann.quickcommerce.entities.Location;
import com.shann.quickcommerce.exceptions.BatchedTaskNotFoundException;
import com.shann.quickcommerce.repositories.BatchedTaskRepository;
import com.shann.quickcommerce.repositories.DropTaskRepository;
import com.shann.quickcommerce.services.BatchedTaskService;
import java.util.List;
import org.springframework.stereotype.Service;

/** BatchedTaskServiceImpl is a service implementation for building routes for batched tasks. */
@Service
public class BatchedTaskServiceImpl implements BatchedTaskService {
  BatchedTaskRepository batchedTaskRepository;
  private DropTaskRepository taskRepository;
  private MapsAdapter mapsAdapter;

  /** Constructor for BatchedTaskServiceImpl. */
  public BatchedTaskServiceImpl(
      BatchedTaskRepository batchedTaskRepository,
      DropTaskRepository taskRepository,
      MapsAdapter mapsAdapter) {
    this.batchedTaskRepository = batchedTaskRepository;
    this.taskRepository = taskRepository;
    this.mapsAdapter = mapsAdapter;
  }

  /**
   * Builds a route for a batched task based on its ID.
   *
   * @param batchedTaskId the ID of the batched task to build a route for
   * @return a list of locations representing the route
   * @throws BatchedTaskNotFoundException if the batched task is not found
   */
  @Override
  public List<Location> buildRoute(long batchedTaskId) throws BatchedTaskNotFoundException {
    var batchedTask =
        batchedTaskRepository
            .findById(batchedTaskId)
            .orElseThrow(() -> new BatchedTaskNotFoundException("Batched task not found"));
    var locations = batchedTask.getTasks().stream().map(task -> task.getDropLocation()).toList();
    List<Location> finalizedLocations = mapsAdapter.buildRoute(locations);
    return finalizedLocations;
  }
}
