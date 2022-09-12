package com.example.datetimeforsubscriber.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "date_time")
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

}
