package com.shann.quickcommerce.dtos;

import com.shann.quickcommerce.entities.PartnerTaskMapping;
import lombok.Data;

import java.util.List;

@Data
public class MatchPartnerTaskResponseDto {
    private List<PartnerTaskMapping> partnerTaskMappings;
    private ResponseStatus responseStatus;
}
