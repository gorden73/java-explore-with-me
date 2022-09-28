package ru.practicum.ewm.apis.nonauthorizedusers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.models.dtos.events.EventFullDto;
import ru.practicum.ewm.models.dtos.events.EventShortDto;
import ru.practicum.ewm.services.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;
import java.util.Collection;

@RestController
@RequestMapping(path = "/events")
public class NonAuthorizedUserEventController {
    private final EventService eventService;

    @Autowired
    public NonAuthorizedUserEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public Collection<EventShortDto> getAllEvents(@RequestParam
                                                  @NotNull(message = "Запрос для поиска равен null.")
                                                  @NotBlank(message = "Пустой запрос для поиска.") String text,
                                                  @RequestParam
                                                  @NotNull(message = "список категорий для поиска равен null")
                                                  @NotEmpty(message = "список категорий для поиска пустой")
                                                  Integer[] categories,
                                                  @RequestParam Boolean paid,
                                                  @RequestParam(required = false)
                                                  @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
                                                  String rangeStart,
                                                  @RequestParam(required = false)
                                                  @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
                                                  String rangeEnd,
                                                  @RequestParam Boolean onlyAvailable,
                                                  @RequestParam String sort,
                                                  @RequestParam(defaultValue = "0")
                                                  @PositiveOrZero(message = "может быть равно или больше 0") int from,
                                                  @RequestParam(defaultValue = "10")
                                                  @Positive(message = "может быть только больше 0") int size,
                                                  HttpServletRequest request) {
        return eventService.getAllEvents(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from,
                size, request);
    }

    @GetMapping("/{id}")
    public EventFullDto getEventById(@PathVariable @NotNull @Positive int id, HttpServletRequest request) {
        return eventService.getFullEventById(id, request);
    }
}