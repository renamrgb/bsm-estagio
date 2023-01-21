package com.rpx.bsmv2.domain.accesslevel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "TB_ACCESS_LEVEL")
@SequenceGenerator(name = "SEQ_ACCESS_LEVEL", initialValue = 1, allocationSize = 50)
public class AccessLevel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACCESS_LEVEL")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "AUTHORITY")
    @Enumerated(EnumType.STRING)
    private AccessLevelEnum authority;

    public AccessLevel(AccessLevelEnum authority) {
        this.authority = authority;
    }
}
