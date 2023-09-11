package kitchenpos.deliveryorders.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import kitchenpos.menus.domain.tobe.domain.Quantity;
import kitchenpos.products.domain.tobe.domain.Price;

@Table(name = "delivery_order_line_item")
@Entity
public class DeliveryOrderLineItem {
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long seq;

    @Embedded
    private MenuId menu;

    @Embedded
    private Quantity quantity;

    protected DeliveryOrderLineItem() {
    }

    public DeliveryOrderLineItem(MenuId menu, long quantity) {
        this.menu = menu;
        this.quantity = Quantity.of(quantity);
    }

    public Price amount() {
        return menu.price().multiply(quantity.value());
    }
}
