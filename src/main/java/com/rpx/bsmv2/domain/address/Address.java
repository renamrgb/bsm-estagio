package com.rpx.bsmv2.domain.address;

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
@Table(name = "TB_ADDRESS")
@SequenceGenerator(name = "SEQ_ADDRESS", initialValue = 1, allocationSize = 50)
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADDRESS")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "ZIP_CODE", length = 8)
    private String zipCode;

    //publicArea representa o logradouro
    @Column(name = "PUBLIC_AREA")
    private String publicArea;

    //district reprensta o bairro
    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "COMPLEMENT")
    private String complement;

    @Column(name = "CITY")
    private String city;

    @Column(name = "UF", length = 2)
    private String uf;
}
