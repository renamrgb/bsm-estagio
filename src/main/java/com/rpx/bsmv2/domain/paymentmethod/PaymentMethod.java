package com.rpx.bsmv2.domain.paymentmethod;

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

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "TB_PAYMENT_METHOD")
@SequenceGenerator(name = "SEQ_PAYMENT_METHOD", initialValue = 1, allocationSize = 50)
public class PaymentMethod implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PAYMENT_METHOD")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    public PaymentMethod(String description, boolean isActive) {
        this.description = description;
        this.isActive = isActive;
    }
}
