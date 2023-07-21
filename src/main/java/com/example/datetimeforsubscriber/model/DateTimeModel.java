package com.example.datetimeforsubscriber.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "date_time_new")
public class DateTimeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "number", length = 150)
    private String number;

    @Column(name = "request", length = 150)
    private String request;

    @Column(name = "response", length = 150)
    private String response;
    @Column(name = "sms_is_sended", length = 150)
    private Boolean isSend=false;
    @CreationTimestamp
    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime createdAt;

}
