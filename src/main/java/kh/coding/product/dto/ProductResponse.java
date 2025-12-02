package kh.coding.product.dto;

import lombok.Builder;

@Builder
public record ProductResponse(
        String uuid,
        String name,
        Double price,
        String description,
        String image
) {
    public static ProductResponseBuilder builder() {
        return new ProductResponseBuilder();
    }
}