package kitchenpos.deliveryorders.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kitchenpos.eatinorders.domain.Order;

public interface DeliveryOrderRepository {
    DeliveryOrder save(Order order);

    Optional<DeliveryOrder> findById(UUID id);

    List<DeliveryOrder> findAll();
}
