package kitchenpos.eatinorders.domain;

public enum OrderStatus {
    WAITING, ACCEPTED, SERVED, DELIVERING, DELIVERED, COMPLETED;

    public static OrderStatus defaultOrderStatus() {
        return WAITING;
    }
}
