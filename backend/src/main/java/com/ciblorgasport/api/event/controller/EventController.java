package com.ciblorgasport.api.event.controller;

import com.ciblorgasport.api.common.ApiResponse;
import com.ciblorgasport.api.event.dto.CreateEventRequest;
import com.ciblorgasport.api.event.dto.EventResponse;
import com.ciblorgasport.api.event.dto.RescheduleEventRequest;
import com.ciblorgasport.api.event.service.EventService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ApiResponse<List<EventResponse>> getAllEvents() {
        return ApiResponse.success("Events retrieved successfully", eventService.getAllEvents());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<EventResponse> createEvent(@Valid @RequestBody CreateEventRequest request) {
        return ApiResponse.success("Event created successfully", eventService.createEvent(request));
    }

    @PatchMapping("/{eventId}/cancel")
    public ApiResponse<EventResponse> cancelEvent(@PathVariable Long eventId) {
        return ApiResponse.success("Event cancelled successfully", eventService.cancelEvent(eventId));
    }

    @PatchMapping("/{eventId}/reschedule")
    public ApiResponse<EventResponse> rescheduleEvent(
            @PathVariable Long eventId,
            @Valid @RequestBody RescheduleEventRequest request) {
        return ApiResponse.success("Event rescheduled successfully", eventService.rescheduleEvent(eventId, request));
    }
}