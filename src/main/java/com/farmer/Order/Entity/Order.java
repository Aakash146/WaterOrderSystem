package com.farmer.Order.Entity;

import com.farmer.Order.Enum.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "order_details")
public class Order {

    @Id
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize =  1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "start_date_time", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "completion_time", nullable = false)
    private LocalDateTime completionTime;

    @Column( nullable = false)
    @Enumerated( value = EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farmer farmer;

    public Order() {
        // Do Nothing
    }

    public Order(final UUID orderId, final LocalDateTime startDateTime, final Integer duration, final LocalDateTime completionTime, final OrderStatus status, final Farmer farmer) {
        this.orderId = orderId;
        this.startDateTime = startDateTime;
        this.duration = duration;
        this.completionTime = completionTime;
        this.status = status;
        this.farmer = farmer;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getStartDateTime() {

        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {

        this.startDateTime = startDateTime;
    }

    public Integer getDuration() {

        return duration;
    }

    public void setDuration(Integer duration) {

        this.duration = duration;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }

    public OrderStatus getStatus() {

        return status;
    }

    public void setStatus(OrderStatus status) {

        this.status = status;
    }

    public Farmer getFarmer() {
        return farmer; }

    public void setFarmer(Farmer farmer) {

        this.farmer = farmer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order that = (Order) o;
        return Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderId=" + orderId +
                ", startDateTime=" + startDateTime +
                ", duration=" + duration +
                ", completionTime=" + completionTime +
                ", status=" + status +
                ", farmer=" + farmer +
                '}';
    }

}
