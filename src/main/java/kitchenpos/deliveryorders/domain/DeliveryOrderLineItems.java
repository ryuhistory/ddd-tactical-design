package kitchenpos.deliveryorders.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import kitchenpos.products.domain.tobe.domain.Price;

@Embeddable
public class DeliveryOrderLineItems {
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
        name = "delivery_order_id",
        nullable = false,
        columnDefinition = "binary(16)",
        foreignKey = @ForeignKey(name = "fk_eat_in_order_line_item_to_orders")
    )
    private List<DeliveryOrderLineItem> values;

    protected DeliveryOrderLineItems() {
    }

    public DeliveryOrderLineItems(List<DeliveryOrderLineItem> orderLineItems) {
        if (Objects.isNull(orderLineItems) || orderLineItems.isEmpty()) {
            throw new IllegalArgumentException("주문 내역이 없습니다.");
        }
        this.values = orderLineItems;
    }

    public Price sumOfAmount() {
        return values.stream()
            .map(DeliveryOrderLineItem::amount)
            .reduce(Price.ZERO, Price::add);

    }
}
