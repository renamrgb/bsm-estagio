package com.rpx.bsmv2.domain.treatment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rpx.bsmv2.domain.employee.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "TB_TREATMENT")
@SequenceGenerator(name = "SEQ_TREATMENT", initialValue = 1, allocationSize = 50)
public class Treatment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TREATMENT")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive;

    @JsonIgnore
    @ManyToMany(mappedBy = "treatments")
    private Set<Employee> employeers;

    public Treatment(Long id) {
        this.id = id;
    }

    public Treatment(String description, BigDecimal price, Boolean isActive) {
        this.description = description;
        this.price = price;
        this.isActive = isActive;
    }


}
