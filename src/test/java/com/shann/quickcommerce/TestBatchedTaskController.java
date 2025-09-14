package com.shann.quickcommerce.controllers;

import com.shann.quickcommerce.dtos.BuildBatchedTaskRouteRequestDto;
import com.shann.quickcommerce.dtos.BuildBatchedTaskRouteResponseDto;
import com.shann.quickcommerce.dtos.ResponseStatus;
import com.shann.quickcommerce.entities.BatchedTask;
import com.shann.quickcommerce.entities.Location;
import com.shann.quickcommerce.entities.DropTask;
import com.shann.quickcommerce.repositories.BatchedTaskRepository;
import com.shann.quickcommerce.repositories.DropTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestBatchedTaskController {

    @Autowired
    private BatchedTaskController batchedTaskController;

    @Autowired
    private BatchedTaskRepository batchedTaskRepository;

    @Autowired
    private DropTaskRepository taskRepository;

    private BatchedTask batchedTask;

    @BeforeEach
    public void insertDummyData(){

        DropTask task1 = new DropTask();
        task1.setCustomerId(1L);
        task1.setDropLocation(new Location(10, 10));

        DropTask task2 = new DropTask();
        task2.setCustomerId(2L);
        task2.setDropLocation(new Location(20, 20));

        DropTask task3 = new DropTask();
        task3.setCustomerId(3L);
        task3.setDropLocation(new Location(30, 30));

        task1 = taskRepository.save(task1);
        task2 = taskRepository.save(task2);
        task3 = taskRepository.save(task3);

        batchedTask = new BatchedTask();
        batchedTask.setTasks(Arrays.asList(task1, task2, task3));
        batchedTask.setBatchedAt(new Date());
        batchedTask = batchedTaskRepository.save(batchedTask);

    }

    @Test
    public void buildRoute_BatchedTaskDoesntExist(){
        BuildBatchedTaskRouteRequestDto requestDto = new BuildBatchedTaskRouteRequestDto();
        requestDto.setBatchedTaskId(100L);
        BuildBatchedTaskRouteResponseDto responseDto = batchedTaskController.buildRoute(requestDto);
        assertEquals(ResponseStatus.FAILURE, responseDto.getStatus());
    }

    @Test
    public void buildRoute_Success(){
        BuildBatchedTaskRouteRequestDto requestDto = new BuildBatchedTaskRouteRequestDto();
        requestDto.setBatchedTaskId(batchedTask.getId());
        BuildBatchedTaskRouteResponseDto responseDto = batchedTaskController.buildRoute(requestDto);
        assertEquals(ResponseStatus.SUCCESS, responseDto.getStatus());
        assertEquals(3, responseDto.getRouteToBeTaken().size());
    }

}
