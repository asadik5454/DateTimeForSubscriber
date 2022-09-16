package com.example.datetimeforsubscriber.controller;

import com.example.datetimeforsubscriber.dto.USSDrequestDto;
import com.example.datetimeforsubscriber.model.DateTimeModel;
import com.example.datetimeforsubscriber.repo.DateTimeRepo;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class DateTimeController {
    private final DateTimeRepo dateTimeRepo;
    private  Clock clock;
    public DateTimeController(DateTimeRepo dateTimeRepo) {
        this.dateTimeRepo = dateTimeRepo;
    }

    @PostMapping
    public Map<String,Object> getDate(@RequestBody USSDrequestDto ussDrequestDto) {
        String date = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm:ss").format(LocalDateTime.now());

        DateTimeModel dateTimeModel = new DateTimeModel();
        dateTimeModel.setNumber(ussDrequestDto.getMsisdn());
        dateTimeModel.setRequest(ussDrequestDto.getMessage());
        dateTimeModel.setResponse(date);
        dateTimeRepo.save(dateTimeModel);

        Map<String, Object> map = new HashMap<>();
        map.put("menu", date);
        map.put("refusalUssd", "Айни замон хизматрасони дастнорас аст. Амалиетро дертар такрор намоед./ В данный момент сервис временно не доступен. Повторите попытку позже");
        map.put("typeUssd", false);
        map.put("ussdTails", false);




        return map;
    }

}
