package com.test.example.demo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter @Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "product_recommendation")
public class ProductRecommendation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1762631281607805362L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "priority")
    private int priority;

    @Column(name = "native_identifier", unique = true)
    private final UUID nativeIdentifier = UUID.randomUUID();

    public ProductRecommendation() {
    }

    public ProductRecommendation(
            final Product product,
            final int priority
    ) {
        this.product = product;
        this.priority = priority;
    }
}
