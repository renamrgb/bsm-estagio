package com.rpx.bsmv2.domain.user;

import com.rpx.bsmv2.domain.accesslevel.AccessLevel;
import com.rpx.bsmv2.domain.address.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "TB_USER")
@SequenceGenerator(name = "SEQ_USER", initialValue = 1, allocationSize = 50)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "IS_ADMIN")
    private Boolean isAdmin;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "RG")
    private String rg;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCESS_LEVEL_ID")
    private AccessLevel accessLevel;

    public User() {
        this.address = new Address();
        this.accessLevel = new AccessLevel();
    }
}
