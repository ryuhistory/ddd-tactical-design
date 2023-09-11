package kitchenpos.eatinorders.domain.tobe.domain;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "eat_in_orders")
@Entity
public class EatInOrder {
    @Column(name = "id", columnDefinition = "binary(16)")
    @Id
    private UUID id;

    @Column(name = "order_master_id", columnDefinition = "binary(16)")
    private UUID orderMasterId;

    @Embedded
    private EatInOrderLineItems orderLineItems;

    @ManyToOne
    @JoinColumn(
        name = "order_table_id",
        columnDefinition = "binary(16)",
        foreignKey = @ForeignKey(name = "fk_orders_to_order_table")
    )
    private ToBeOrderTable orderTable;

    protected EatInOrder() {
    }

    public EatInOrder(UUID orderMasterId, List<EatInOrderLineItem> eatInOrderLineItems, ToBeOrderTable orderTable) {
        validationOfOrderTable(orderTable);
        validationOfMaster(orderMasterId);
        this.orderMasterId = orderMasterId;
        this.orderLineItems = new EatInOrderLineItems(eatInOrderLineItems);
        this.orderTable = orderTable;
    }

    private void validationOfMaster(UUID orderMasterId) {
        if (orderMasterId == null) {
            throw new IllegalArgumentException("주문이 먼저 생성되어야 합니다.");
        }
    }

    private void validationOfOrderTable(ToBeOrderTable orderTable) {
        if (orderTable == null) {
            throw new NoSuchElementException("매장 테이블이 등록되 있어야 합니다.");
        }
        if (orderTable.isOccupied()) {
            throw new IllegalArgumentException("매장 테이블에 앉은 후 주문 할 수 있습니다.");
        }
    }
}
