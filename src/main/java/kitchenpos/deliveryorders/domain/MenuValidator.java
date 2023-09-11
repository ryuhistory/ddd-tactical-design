package kitchenpos.deliveryorders.domain;

import java.util.stream.Collectors;

import kitchenpos.common.DomainService;
import kitchenpos.eatinorders.domain.Order;
import kitchenpos.eatinorders.domain.OrderLineItem;
import kitchenpos.menus.domain.tobe.domain.Menus;
import kitchenpos.menus.domain.tobe.domain.ToBeMenuRepository;

@DomainService
public class MenuValidator {
    private final ToBeMenuRepository menuRepository;

    public MenuValidator(ToBeMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void validation(final Order request) {
        final Menus menus = new Menus(
            menuRepository.findAllByIdIn(
                request.getOrderLineItems().stream()
                    .map(OrderLineItem::getMenuId)
                    .collect(Collectors.toList())
            ));
        if (!menus.isSameSize(request.getOrderLineItems().size())) {
            throw new IllegalArgumentException();
        }
        if (!menus.isAllDisplayed()) {
            throw new IllegalStateException();
        }
        if (!menus.hasSameMenuAndPrice(request.getOrderLineItems())) {
            throw new IllegalStateException();
        }
    }

}
