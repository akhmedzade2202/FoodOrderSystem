package com.example.foodordersystem.service;

import com.example.foodordersystem.dto.OrderRequestDto;
import com.example.foodordersystem.dto.OrderResponseDto;
import com.example.foodordersystem.entity.Food;
import com.example.foodordersystem.entity.Order;
import com.example.foodordersystem.enums.OrderStatus;
import com.example.foodordersystem.enums.Payment;
import com.example.foodordersystem.exception.NotFoundException;
import com.example.foodordersystem.repository.FoodRepository;
import com.example.foodordersystem.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;

    @Test
    void testCreateOrder_withCardPayment_success() {
        OrderRequestDto requestDto = new OrderRequestDto();
        requestDto.setAddress("Baku");
        requestDto.setRestaurantName("Pizza Hut");
        requestDto.setPayment(Payment.CARD);
        requestDto.setCardNumber("1234567890123456");
        requestDto.setFoodNames(List.of("Pizza", "Burger"));


        Food pizza = new Food();
        pizza.setId(1L);
        pizza.setFoodName("Pizza");
        pizza.setDescription("Cheesy and delicious");

        Food burger = new Food();
        burger.setId(2L);
        burger.setFoodName("Burger");
        burger.setDescription("Juicy beef burger");


        Order savedOrder = new Order();
        savedOrder.setAddress("Baku");
        savedOrder.setRestaurantName("Pizza Hut");
        savedOrder.setPayment(Payment.CARD);
        savedOrder.setCardNumber("1234567890123456");
        savedOrder.setFoods(List.of(pizza, burger));
        savedOrder.setStatus(OrderStatus.PENDING);


        when(foodRepository.findByFoodName("Pizza")).thenReturn(Optional.of(pizza));
        when(foodRepository.findByFoodName("Burger")).thenReturn(Optional.of(burger));
        verify(orderRepository).save(Mockito.any());


        OrderResponseDto response = orderService.createOrder(requestDto);

        assertEquals("Baku", response.getAddress());
        assertEquals("Pizza Hut", response.getRestaurantName());
        assertEquals(Payment.CARD, response.getPayment());
        assertEquals("1234567890123456", response.getCardNumber());
        assertEquals(OrderStatus.PENDING, response.getStatus());
        assertEquals(List.of("Pizza", "Burger"), response.getFoodNames());

        verify(foodRepository, times(1)).findByFoodName("Pizza");
        verify(foodRepository, times(1)).findByFoodName("Burger");
        // verify(orderRepository, times(1)).save(any(Order.class));
        verify(orderRepository, times(1)).save(Mockito.<Order>any());


    }

    @Test
    void testCreateOrder_withInvalidCardNumber_shouldThrowException() {
        OrderRequestDto requestDto = new OrderRequestDto();
        requestDto.setPayment(Payment.CARD);
        requestDto.setCardNumber("12345");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> orderService.createOrder(requestDto)
        );

        assertEquals("Card Number must consist of exactly 16 characters", exception.getMessage());
    }

    @Test
    void testCreateOrder_withEmptyFoodList_shouldThrowException() {
        OrderRequestDto requestDto = new OrderRequestDto();
        requestDto.setPayment(Payment.CASH);
        requestDto.setFoodNames(Collections.emptyList());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> orderService.createOrder(requestDto)
        );

        assertEquals("Food list cannot be empty", exception.getMessage());
    }

    @Test
    void testCreateOrder_withUnknownFood_shouldThrowNotFoundException() {
        OrderRequestDto requestDto = new OrderRequestDto();
        requestDto.setPayment(Payment.CASH);
        requestDto.setFoodNames(List.of("UnknownFood"));

        when(foodRepository.findByFoodName("UnknownFood")).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> orderService.createOrder(requestDto)
        );

        assertEquals("Food not found: UnknownFood", exception.getMessage());
    }

    @BeforeEach
    public void setUp() {
        // Initialize a sample Order object
        order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.PENDING);
    }

    @Test
    public void testUpdateOrderStatus_Success() {
        // Given
        Long orderId = 1L;
        OrderStatus newStatus = OrderStatus.CONFIRMED;

        // When
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);

        Order updatedOrder = orderService.updateOrderStatus(orderId, newStatus);

        // Then
        assertEquals(newStatus, updatedOrder.getStatus());
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testUpdateOrderStatus_NotFound() {
        // Given
        Long orderId = 1L;
        OrderStatus newStatus = OrderStatus.CONFIRMED;

        // When
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class, () -> orderService.updateOrderStatus(orderId, newStatus));
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, never()).save(Mockito.<Order>any());
    }



    @Test
    public void testGetAllOrders() {

        Order order1 = new Order();
        order1.setId(1L);
        order1.setStatus(OrderStatus.PENDING);
        order1.setAddress("Address 1");
        order1.setRestaurantName("Restaurant 1");

        Order order2 = new Order();
        order2.setId(2L);
        order2.setStatus(OrderStatus.CONFIRMED);
        order2.setAddress("Address 2");
        order2.setRestaurantName("Restaurant 2");
        // Given
        List<Order> orders = Arrays.asList(order1, order2);

        // When
        when(orderRepository.findAll()).thenReturn(orders);

        // Then
        List<Order> allOrders = orderService.getAllOrders();
        assertNotNull(allOrders);
        assertEquals(2, allOrders.size());
        assertEquals(order1.getId(), allOrders.get(0).getId());
        assertEquals(order2.getId(), allOrders.get(1).getId());

        verify(orderRepository, times(1)).findAll();
    }
}
