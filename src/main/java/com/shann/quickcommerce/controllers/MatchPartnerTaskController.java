package com.shann.quickcommerce.controllers;

import com.shann.quickcommerce.dtos.MatchPartnerTaskRequestDto;
import com.shann.quickcommerce.dtos.MatchPartnerTaskResponseDto;
import com.shann.quickcommerce.dtos.ResponseStatus;
import com.shann.quickcommerce.services.MatchPartnerTaskService;
import org.springframework.web.bind.annotation.*;

/**
 * MatchPartnerTaskController is responsible for handling requests related to matching partners with
 * tasks. It provides endpoints to create, update, and retrieve match partner tasks.
 */
@RestController
@RequestMapping("/match-partner-task")
public class MatchPartnerTaskController {
  private MatchPartnerTaskService matchPartnerTaskService;

  public MatchPartnerTaskController(MatchPartnerTaskService matchPartnerTaskService) {
    this.matchPartnerTaskService = matchPartnerTaskService;
  }

  /**
   *
   * @param requestDto
   * @return MatchPartnerTaskResponseDto
   */
  @PostMapping("")
  public MatchPartnerTaskResponseDto matchPartnersAndTasks(@RequestBody MatchPartnerTaskRequestDto requestDto) {
    var responseDto = new MatchPartnerTaskResponseDto();
    try{
      var partnerTaskMappings = matchPartnerTaskService.matchPartnerTask(requestDto.getPartnerIds(), requestDto.getTaskIds());
        responseDto.setPartnerTaskMappings(partnerTaskMappings);
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
    } catch (Exception exception) {
      responseDto.setResponseStatus(ResponseStatus.FAILURE);
    }
    return responseDto;
  }
}
