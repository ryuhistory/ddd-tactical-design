package kitchenpos.eatinorders.domain.tobe.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import kitchenpos.deliveryorders.domain.MenuId;
import kitchenpos.products.domain.tobe.domain.Price;

@Table(name = "eat_in_order_line_item")
@Entity
public class EatInOrderLineItem {
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long seq;

    @Embedded
    private MenuId menu;

    @Embedded
    private EatInOrderQuantity quantity;

    protected EatInOrderLineItem() {
    }

    public EatInOrderLineItem(MenuId menu, long quantity) {
        this.menu = menu;
        this.quantity = EatInOrderQuantity.of(quantity);
    }

    public Price amount() {
        return menu.price().multiply(quantity.value());
    }
}
