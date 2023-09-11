package kitchenpos.menus.domain.tobe.domain;

import java.util.ArrayList;
import java.util.List;

import kitchenpos.eatinorders.domain.OrderLineItem;

public class Menus {
    private final List<ToBeMenu> value;

    public Menus(List<ToBeMenu> menus) {
        if (menus == null) {
            menus = new ArrayList<>();
        }
        this.value = menus;
    }

    public boolean isSameSize(int request) {
        return value.size() == request;
    }

    public boolean isAllDisplayed() {
        return value.stream()
            .allMatch(ToBeMenu::isDisplayed);
    }

    public boolean hasSameMenuAndPrice(List<OrderLineItem> orderLineItems) {
        return value.stream()
            .allMatch(menu ->
                orderLineItems.stream()
                    .anyMatch(item -> menu.isSameMenuAndPrice(item.getMenuId(), item.getPrice()))
            );
    }
}
