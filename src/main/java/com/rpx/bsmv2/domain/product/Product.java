package com.rpx.bsmv2.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "TB_PRODUCT")
@SequenceGenerator(name = "SEQ_PRODUCT", initialValue = 1, allocationSize = 50)
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCT")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "STOCK_AMOUNT")
    private Integer stockAmount;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    public Product(String title, BigDecimal price, String brand, Integer stockAmount, Boolean isActive) {
        this.title = title;
        this.price = price;
        this.brand = brand;
        this.stockAmount = stockAmount;
        this.isActive = isActive;
    }
}
