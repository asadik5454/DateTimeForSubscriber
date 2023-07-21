package com.example.datetimeforsubscriber.scheduller;

import com.example.datetimeforsubscriber.model.DateTimeModel;
import com.example.datetimeforsubscriber.repo.DateTimeRepo;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableAsync
@Service
@EnableScheduling
public class UpdateSendedSubscribers {

    private final DateTimeRepo dateTimeRepo;

    public UpdateSendedSubscribers(DateTimeRepo dateTimeRepo) {
        this.dateTimeRepo = dateTimeRepo;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateDateTimeModels() {
        List<DateTimeModel> dateTimeModelList = dateTimeRepo.findAll();
        dateTimeModelList.forEach(dateTimeModel -> dateTimeModel.setIsSend(false));
        dateTimeRepo.saveAll(dateTimeModelList);
    }
}

