package com.rpx.bsmv2.domain.messagetemplete;

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
@Table(name = "TB_MESSAGE_TEMPLATE")
@SequenceGenerator(name = "SEQ_MESSAGE_TEMPLATE", initialValue = 1, allocationSize = 50)
public class MessageTemplate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MESSAGE_TEMPLATE")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    public MessageTemplate(String title, String message, Boolean isActive) {
        this.title = title;
        this.message = message;
        this.isActive = isActive;
    }
}
