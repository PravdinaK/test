package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public class DepartureBeforeNowFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(f -> f.getSegments().stream()
                        .allMatch(s -> s.getDepartureDate().isAfter(LocalDateTime.now())))
                .toList();
    }
}