package gov.iti.jets.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    // Initialize orderItems
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private Set<OrderItem> orderItems = new HashSet<>();

    public Order(User user, BigDecimal totalPrice, LocalDateTime dateCreated) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.dateCreated = dateCreated;
        this.orderItems = new HashSet<>(); // Initialize orderItems
    }
}