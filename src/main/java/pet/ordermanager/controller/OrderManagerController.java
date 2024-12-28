package pet.ordermanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet.ordermanager.model.OrderCreateResult;
import pet.ordermanager.model.OrderInfo;
import pet.ordermanager.service.OrderManagerService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderManagerController {
    private final OrderManagerService orderManagerService;

    @PostMapping("newOrder")
    public ResponseEntity<OrderCreateResult> newOrder(@RequestBody OrderInfo orderInfo) {
        try {
            var orderId = orderManagerService.newOrder(orderInfo);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new OrderCreateResult("SUCCESS", orderId, null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new OrderCreateResult("ERROR", null, e.getMessage()));
        }
    }

    @GetMapping("getFullOrder")
    public ResponseEntity<Object> getFullOrder(@RequestHeader("orderId") UUID orderId) {
        try {
            var fullOrder = orderManagerService.getFullOrder(orderId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(fullOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("getCustomerOrders")
    public ResponseEntity<Object> getCustomerOrders(@RequestHeader("userId") UUID userId) {
        try {
            var fullOrders = orderManagerService.getCustomerOrders(userId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(fullOrders);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("getGoodOrders")
    public ResponseEntity<Object> getGoodOrders(@RequestHeader("goodId") UUID goodId) {
        try {
            var fullOrders = orderManagerService.getGoodOrders(goodId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(fullOrders);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("getOrderHistory")
    public ResponseEntity<Object> getOrderHistory(@RequestHeader("orderId") UUID orderId) {
        try {
            var orderHistory = orderManagerService.getOrderHistory(orderId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(orderHistory);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
