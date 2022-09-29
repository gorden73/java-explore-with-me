package ru.practicum.ewm.models.dtos.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ViewStatsDto {
    private String app;
    private String uri;
    private Integer hits;
}