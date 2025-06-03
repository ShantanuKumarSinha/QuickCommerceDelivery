package com.shann.quickcommerce.controllers;


import com.shann.quickcommerce.dtos.BuildBatchedTaskRouteRequestDto;
import com.shann.quickcommerce.dtos.BuildBatchedTaskRouteResponseDto;
import com.shann.quickcommerce.dtos.ResponseStatus;
import com.shann.quickcommerce.services.BatchedTaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batched-task")
public class BatchedTaskController {

    private BatchedTaskService batchedTaskService;

    public BatchedTaskController(BatchedTaskService batchedTaskService) {
        this.batchedTaskService = batchedTaskService;
    }

    @PostMapping("/build-route")
    public BuildBatchedTaskRouteResponseDto buildRoute(@RequestBody BuildBatchedTaskRouteRequestDto requestDto){
        var responseDto = new BuildBatchedTaskRouteResponseDto();
        try{
           var routes = batchedTaskService.buildRoute(requestDto.getBatchedTaskId());
           responseDto.setRouteToBeTaken(routes);
           responseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception exception) {
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
