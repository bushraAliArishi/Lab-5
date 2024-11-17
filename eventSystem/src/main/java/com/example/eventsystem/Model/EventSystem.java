package com.example.eventsystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class EventSystem {
    private String eventName,id,description;
    private LocalDate startDate,endDate;
    private int capacity;
}
