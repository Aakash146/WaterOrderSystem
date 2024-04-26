package com.farmer.Order.Entity;

import com.farmer.Order.Enum.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "order_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "order_generator")
    @GenericGenerator(name = "order_generator", strategy = "org.hibernate.id.UUIDGenerator")
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
    @JoinColumn(name = "farmer_id", nullable = false)
    private Farmer farmer;

}
