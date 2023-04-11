package com.test.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter @Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "design_line")
public class DesignLine implements Serializable {

    @Serial
    private static final long serialVersionUID = 2897415904231243434L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "priority")
    private int priority;

    @Column(name = "native_identifier", unique = true)
    private final UUID nativeIdentifier = UUID.randomUUID();

    @OneToMany(
            mappedBy = "designLine",
            fetch = FetchType.LAZY,
            targetEntity = Product.class
    )
    private Set<Product> products = new HashSet<>();

    @Basic
    @Column(name = "last_modified_date", nullable = false)
    private long lastModifiedDate;
}
