package ru.practicum.stat.services;

import ru.practicum.stat.models.dto.EndPointHitDto;
import ru.practicum.stat.models.ViewStats;

import java.util.Collection;

public interface StatService {
    EndPointHitDto addEndPointHit(EndPointHitDto dto);

    Collection<ViewStats> getStats(String start, String end, String[] uris, Boolean unique);
}
