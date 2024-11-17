package com.example.eventsystem.Controller;

import com.example.eventsystem.ApiResponse.ApiResponse;
import com.example.eventsystem.Model.EventSystem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/event")
public class EventSystemController {
    ArrayList<EventSystem> eventSystems = new ArrayList<>();

    @GetMapping("/disply")
    public ArrayList<EventSystem> getEvents() {
        return eventSystems;
    }

    @GetMapping("/disply_by_id/{key}")
    public EventSystem getEventsbyId(@PathVariable String key) {
        EventSystem eventSystemSearch = null;
        for (EventSystem eventSystem : eventSystems) {
            if (eventSystem.getId().equals(key)) {
                eventSystemSearch = eventSystem;
            }
        }
        return eventSystemSearch;
    }

    @PostMapping("/add")
    public ApiResponse addEvent(@RequestBody EventSystem event) {
        eventSystems.add(event);
        return new ApiResponse("event added successfully");
    }

    @PutMapping("/update/{key}")
    public ApiResponse updateEvent(@PathVariable int key, @RequestBody EventSystem event) {
        eventSystems.set(key, event);
        return new ApiResponse("event updated successfully");
    }

    @PutMapping("/capacity/{key}/{capacityNum}")
    public ApiResponse updateEventCapacity(@PathVariable int key, @PathVariable int capacityNum) {

        eventSystems.get(key).setCapacity(capacityNum);
        return new ApiResponse("event Capacity updated successfully");
    }

    @DeleteMapping("/delete/{key}")
    public ApiResponse deleteEvent(@PathVariable int key) {
        eventSystems.remove(eventSystems.get(key));
        return new ApiResponse("event deleted successfully");
    }

}
