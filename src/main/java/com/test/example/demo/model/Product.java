package com.test.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 980931487634572134L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "native_identifier", unique = true)
    private final UUID nativeIdentifier = UUID.randomUUID();

    @OneToMany(
            mappedBy = "product_content",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            targetEntity = ProductContent.class
    )
    private Set<ProductContent> productContents = new HashSet<>();

    @OneToMany(mappedBy = "beauty_user_product",
            fetch = FetchType.LAZY,
            targetEntity = UserProduct.class)
    private Set<UserProduct> userProducts = new HashSet<>();

    @Column(name = "skin_issues")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = SkinIssues.class, fetch = FetchType.EAGER)
    private Set<SkinIssues> skinIssues = new HashSet<>();

    @OneToMany(mappedBy = "product_recommendation",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            targetEntity = ProductRecommendation.class)
    private Set<ProductRecommendation> recommendations = new HashSet<>();

    @OneToMany(mappedBy = "session_product",
            fetch = FetchType.LAZY,
            targetEntity = SessionProduct.class)
    private Set<SessionProduct> sessionProducts = new HashSet<>();

    @Basic
    @Column(name = "active")
    private boolean active;

    @Basic
    @Column(name = "recently_created")
    private boolean recentlyCreated;

    @Column(name = "release_date")
    private LocalDateTime releaseDate;

    @ManyToOne(
            optional = false,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinColumn(name = "design_line_id", nullable = false)
    private DesignLine designLine;

    @ManyToOne(
            optional = false,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinColumn(name = "device_id", nullable = false)
    private ProductDevice productDevice;

    @Basic
    @Column(name = "shopware_product_id")
    private Long shopwareProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_color_id", nullable = false)
    private Color defaultColor;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            targetEntity = Color.class
    )
    @JoinTable(
            name = "product_color",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    private Set<Color> productColors = new HashSet<>();

    @Basic
    @Column(name = "last_modified_date", nullable = false)
    private long lastModifiedDate;

}
