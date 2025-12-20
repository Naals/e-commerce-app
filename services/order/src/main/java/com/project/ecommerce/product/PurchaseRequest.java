package com.project.ecommerce.product;


import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.*;

@Validated
public record PurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer productId,
        @Positive(message = "Quantity is mandatory")
        double quantity
) {
}
