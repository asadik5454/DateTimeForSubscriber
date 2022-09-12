package com.example.datetimeforsubscriber.repo;

import com.example.datetimeforsubscriber.model.DateTimeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateTimeRepo extends JpaRepository<DateTimeModel,Long> {
}
