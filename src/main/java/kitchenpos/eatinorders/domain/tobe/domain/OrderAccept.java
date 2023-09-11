package kitchenpos.eatinorders.domain.tobe.domain;

import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;

import kitchenpos.common.DomainService;

@DomainService
public class OrderAccept {

    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderAccept(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void orderAccept(UUID orderId) {
        
    }
}
