package com.shann.quickcommerce.services;

import com.shann.quickcommerce.dtos.MatchPartnerTaskRequestDto;
import com.shann.quickcommerce.entities.PartnerTaskMapping;

import java.util.List;

public interface MatchPartnerTaskService {
  public List<PartnerTaskMapping> matchPartnerTask(List<Long> partnerIds, List<Long> taskIds);
}
