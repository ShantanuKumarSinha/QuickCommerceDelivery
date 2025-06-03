package com.shann.quickcommerce.services;

import com.shann.quickcommerce.entities.Location;
import com.shann.quickcommerce.exceptions.BatchedTaskNotFoundException;

import java.util.List;

public interface BatchedTaskService {

    public List<Location> buildRoute(long batchedTaskId) throws BatchedTaskNotFoundException;
}
