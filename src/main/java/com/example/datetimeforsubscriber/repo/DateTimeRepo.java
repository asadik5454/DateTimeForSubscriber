package com.example.datetimeforsubscriber.repo;

import com.example.datetimeforsubscriber.model.DateTimeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DateTimeRepo extends JpaRepository<DateTimeModel,Long> {

    @Query("SELECT d from DateTimeModel d WHERE d.createdAt > ?1 and  d.number = ?2")
    DateTimeModel findByNum(LocalDateTime dateTime, String number);

    DateTimeModel findByNumberAndIsSendFalse(String number);

    DateTimeModel findByNumber(String number);

}
