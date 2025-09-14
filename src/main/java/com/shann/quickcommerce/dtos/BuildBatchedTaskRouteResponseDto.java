package com.shann.quickcommerce.dtos;

import com.shann.quickcommerce.entities.Location;
import lombok.Data;

import java.util.List;

@Data
public class BuildBatchedTaskRouteResponseDto {
    private List<Location> routeToBeTaken;

    private ResponseStatus status;
}
