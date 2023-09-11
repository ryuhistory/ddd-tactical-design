package kitchenpos.eatinorders.domain.tobe.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import kitchenpos.products.domain.tobe.domain.Price;

@Embeddable
public class EatInOrderLineItems {
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
        name = "eat_in_order_id",
        nullable = false,
        columnDefinition = "binary(16)",
        foreignKey = @ForeignKey(name = "fk_eat_in_order_line_item_to_orders")
    )
    private List<EatInOrderLineItem> values;

    protected EatInOrderLineItems() {
    }

    public EatInOrderLineItems(List<EatInOrderLineItem> orderLineItems) {
        if (Objects.isNull(orderLineItems) || orderLineItems.isEmpty()) {
            throw new IllegalArgumentException("주문 내역이 없습니다.");
        }
        this.values = orderLineItems;
    }

    public Price sumOfAmount() {
        return values.stream()
            .map(EatInOrderLineItem::amount)
            .reduce(Price.ZERO, Price::add);
    }
}
