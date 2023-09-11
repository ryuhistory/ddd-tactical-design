package kitchenpos.deliveryorders.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import kitchenpos.products.domain.tobe.domain.Price;

@Embeddable
public class MenuId {
    @Column(name = "menu_id", columnDefinition = "binary(16)")
    private UUID id;
    @Embedded
    private Price price;

    protected MenuId() {
    }

    public MenuId(UUID id, Price price) {
        this.id = id;
        this.price = price;
    }

    public Price price() {
        return price;
    }
}
