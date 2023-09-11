package kitchenpos.eatinorders.domain.tobe.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import kitchenpos.products.domain.tobe.domain.Price;

@Embeddable
public class OrderLineItems {
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
        name = "order_id",
        nullable = false,
        columnDefinition = "binary(16)",
        foreignKey = @ForeignKey(name = "fk_order_line_item_to_orders")
    )
    private List<ToBeOrderLineItem> orderLineItems;

    protected OrderLineItems() {
    }

    public OrderLineItems(List<ToBeOrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public Price getSumOfOrderMenus() {
        return orderLineItems.stream()
            .map(ToBeOrderLineItem::getOrderPrice)
            .reduce(Price.ZERO, Price::add);
    }
}
