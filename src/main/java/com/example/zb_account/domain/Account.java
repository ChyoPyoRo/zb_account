package com.example.zb_account.domain;

import com.example.zb_account.type.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //PK

    @ManyToOne
    @JoinColumn
    private AccountUser accountUser;

    @Column
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Column
    private Long balance;

    @Column
    private LocalDateTime registeredAt;

    @Column
    private LocalDateTime unRegisteredAt;



}
