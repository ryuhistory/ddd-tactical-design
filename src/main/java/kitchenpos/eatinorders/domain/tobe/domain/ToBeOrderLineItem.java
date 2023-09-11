package kitchenpos.eatinorders.domain.tobe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kitchenpos.menus.domain.tobe.domain.Quantity;
import kitchenpos.menus.domain.tobe.domain.ToBeMenu;
import kitchenpos.products.domain.tobe.domain.Price;

@Table(name = "tobe_order_line_item")
@Entity
public class ToBeOrderLineItem {
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long seq;

    @ManyToOne(optional = false)
    @JoinColumn(
        name = "menu_id",
        columnDefinition = "binary(16)",
        foreignKey = @ForeignKey(name = "fk_order_line_item_to_menu")
    )
    private ToBeMenu menu;

    @Column(name = "quantity", nullable = false)
    private Quantity quantity;

    protected ToBeOrderLineItem() {
    }

    public ToBeOrderLineItem(ToBeMenu menu, long quantity) {
        this.menu = menu;
        this.quantity = Quantity.of(quantity);
    }

    public Price getOrderPrice() {
        return menu.getPrice().multiply(quantity.getValue());
    }
}
