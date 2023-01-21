package com.rpx.bsmv2.domain.treatmentproduct;

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
@Table(name = "TB_TREATMENT_PRODUCT")
@SequenceGenerator(name = "SEQ_TREATMENT_PRODUCT", initialValue = 1, allocationSize = 50)
public class TreatmentProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TREATMENT_PRODUCT")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    public TreatmentProduct(String description, Boolean isActive) {
        this.description = description;
        this.isActive = isActive;
    }
}
