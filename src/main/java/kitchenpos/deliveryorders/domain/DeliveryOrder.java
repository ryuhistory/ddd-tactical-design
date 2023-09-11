package kitchenpos.deliveryorders.domain;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "delivery_in_orders")
@Entity
public class DeliveryOrder {
    @Column(name = "id", columnDefinition = "binary(16)")
    @Id
    private UUID id;

    @Column(name = "order_master_id", columnDefinition = "binary(16)")
    private UUID orderMasterId;

    @Embedded
    private DeliveryOrderLineItems orderLineItems;

    @Embedded
    private DeliveryAddress deliveryAddress;

    protected DeliveryOrder() {
    }

    public DeliveryOrder(UUID orderMasterId, List<DeliveryOrderLineItem> orderLineItems, String address) {
        validationOfMaster(orderMasterId);
        this.orderMasterId = orderMasterId;
        this.orderLineItems = new DeliveryOrderLineItems(orderLineItems);
        this.deliveryAddress = DeliveryAddress.of(address);
    }

    private void validationOfMaster(UUID orderMasterId) {
        if (orderMasterId == null) {
            throw new IllegalArgumentException("주문이 먼저 생성되어야 합니다.");
        }
    }
}
