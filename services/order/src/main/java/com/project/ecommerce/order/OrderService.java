package com.project.ecommerce.order;

import com.project.ecommerce.customer.CustomerClient;
import com.project.ecommerce.exception.BusinessException;
import com.project.ecommerce.orderline.OrderLineService;
import com.project.ecommerce.product.ProductClient;
import com.project.ecommerce.product.PurchaseRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;

    public @Nullable Integer createOrder(@Valid OrderRequest request) {

        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with provided ID: " + request.customerId()));

        this.productClient.purchaseProducts(request.products());

        var order = orderRepository.save(orderMapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        return null;
    }

    public @Nullable List<OrderResponse> findAllOrders() {
    }

    public @Nullable OrderResponse findById(Integer orderId) {
    }
}
