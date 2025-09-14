package com.shann.quickcommerce.dtos;

import com.shann.quickcommerce.entities.Product;
import lombok.Data;

import java.util.List;

@Data
public class GenerateRecommendationsResponseDto {

    private List<Product> recommendations;
    private ResponseStatus responseStatus;
}
