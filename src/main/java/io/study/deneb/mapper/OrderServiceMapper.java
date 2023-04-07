package io.study.deneb.mapper;

import io.study.deneb.entity.Order;
import io.study.deneb.entity.OrderItem;
import io.study.deneb.model.OrderDto;
import io.study.deneb.model.OrderItemDto;
import org.mapstruct.*;

import java.util.Optional;
import java.util.Set;

@Mapper
public interface OrderServiceMapper {

  @Mapping(target = "items", qualifiedByName = "orderItemDtoSetToOrderItemSet")
  Order orderDtoToOrder(OrderDto order);

  @IterableMapping(qualifiedByName = "orderItemDtoToOrderItem")
  @Named("orderItemDtoSetToOrderItemSet")
  Set<OrderItem> orderItemDtoSetToOrderItemSet(Set<OrderItemDto> list);

  @Named("orderItemDtoToOrderItem")
  OrderItem orderItemDtoToOrderItem(OrderItemDto item);

  // after setting order
  @AfterMapping
  default void setOrder(@MappingTarget Order order) {

    Optional.ofNullable(order.getConsumer())
      .ifPresent(it -> it.setOrder(order));

    Optional.ofNullable(order.getItems())
      .ifPresent(it -> it.forEach(item -> item.setOrder(order)));
  }

}
