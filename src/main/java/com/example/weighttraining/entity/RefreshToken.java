package com.example.weighttraining.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String refreshUuid;

    @OneToOne
    private User user;

    @Builder
    public RefreshToken( String refreshUuid, User user) {
        this.refreshUuid = refreshUuid;
        this.user = user;
    }

    public void setNewRefresh(){
        this.refreshUuid = String.valueOf(UUID.randomUUID());
    }
}
