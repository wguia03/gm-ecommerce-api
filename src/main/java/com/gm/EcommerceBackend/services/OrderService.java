package com.gm.EcommerceBackend.services;

import com.gm.EcommerceBackend.entities.*;
import com.gm.EcommerceBackend.exceptions.NotImplementedException;
import com.gm.EcommerceBackend.exceptions.ResourceNotFoundException;
import com.gm.EcommerceBackend.payloads.PurchaseOrderDTO;
import com.gm.EcommerceBackend.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserService userService;
    private final PaymentMethodRepository paymentMethodRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ProductService productService;
    private final DeliveryBranchRepository deliveryBranchRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public PurchaseOrder createOrder(PurchaseOrderDTO purchaseOrderDTO) throws NotImplementedException, ResourceNotFoundException {

        UserEntity user = userService.findUserById(purchaseOrderDTO.userId());
        Cart cart = user.getCart();

        List<CartItem> cartItemList = cart.getCartItemList();

        if (cartItemList.isEmpty()) {
            throw new NoSuchElementException("No cart item found");
        }

        Payment payment = Payment.builder()
                .paymentMethod(paymentMethodRepository.findByName(PaymentMethodEnum.valueOf("CASH")))
                .build();

        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .user(user)
                .payment(payment)
                .orderDate(new Date())
                .deliveryBranch(deliveryBranchRepository.findByName(DeliveryBranchEnum.valueOf(purchaseOrderDTO.deliveryBranch())))
                .build();

        orderRepository.save(purchaseOrder);

        List<OrderItem> orderItemList = cartItemList.stream()
                .filter(cartItem -> {
                    try {
                        return productService.checkProductStock(cartItem.getId(), cartItem.getQuantity());
                    } catch (ResourceNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(cartItem ->
                        OrderItem.builder()
                                .purchaseOrder(purchaseOrder)
                                .quantity(cartItem.getQuantity())
                                .item_price(cartItem.getItem_price())
                                .product(cartItem.getProduct())
                                .build())
                .toList();

        orderItemList.forEach(orderItem -> {
            Product product = orderItem.getProduct();
            product.setStock_quantity(product.getStock_quantity() - orderItem.getQuantity());
            productRepository.save(product);
        });

        orderItemRepository.saveAll(orderItemList);

        purchaseOrder.getPayment().setTotalPrice(cart.getTotal_price());

        OrderStatus orderStatus = orderStatusRepository.findByStatus(OrderStatusEnum.valueOf("CONFIRMED"));
        purchaseOrder.setOrderStatus(orderStatus);

        cartItemRepository.deleteAll(cartItemList);
        cart.setTotal_price(0);
        cartRepository.save(cart);

        return orderRepository.save(purchaseOrder);
    }

    public String getOrderStatus(Integer id) throws ResourceNotFoundException {
        return orderRepository.findById(id)
                .map(order -> order.getOrderStatus().getStatus().name())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }
}
