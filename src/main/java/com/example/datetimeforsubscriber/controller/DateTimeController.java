package com.example.datetimeforsubscriber.controller;

import com.example.datetimeforsubscriber.SendSms;
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
    private Clock clock;

    public DateTimeController(DateTimeRepo dateTimeRepo) {
        this.dateTimeRepo = dateTimeRepo;
    }

    @PostMapping
    public Map<String, Object> getDate(@RequestBody USSDrequestDto ussDrequestDto) {
        String date = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm:ss").format(LocalDateTime.now());


        DateTimeModel checkSubs = dateTimeRepo.findByNumber(ussDrequestDto.getMsisdn());
        if (checkSubs == null ) {
            checkSubs = new DateTimeModel();
            SendSms.sendToProvider(ussDrequestDto.getMsisdn(), "Иқдом! Бо 27,5 сомонӣ аз YouTube бо тахфифи 50% беохир лаззат баред!\n" +
                    "Пайваст кардан *0078#. Маъл. 800.");
            checkSubs.setIsSend(true);
            checkSubs.setNumber(ussDrequestDto.getMsisdn());
            checkSubs.setRequest(ussDrequestDto.getMessage());
            checkSubs.setResponse(date);
            dateTimeRepo.save(checkSubs);
        }
        if (!checkSubs.getIsSend()) {
            SendSms.sendToProvider(ussDrequestDto.getMsisdn(), "Иқдом! Бо 27,5 сомонӣ аз YouTube бо тахфифи 50% беохир лаззат баред!\n" +
                    "Пайваст кардан *0078#. Маъл. 800.");
            checkSubs.setIsSend(true);
            dateTimeRepo.save(checkSubs);

        }

        Map<String, Object> map = new HashMap<>();
        map.put("menu", date);
        map.put("refusalUssd", "Айни замон хизматрасони дастнорас аст. Амалиетро дертар такрор намоед./ В данный момент сервис временно не доступен. Повторите попытку позже");
        map.put("typeUssd", false);
        map.put("ussdTails", false);


        return map;
    }

}
