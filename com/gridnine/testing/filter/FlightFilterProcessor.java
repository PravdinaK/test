package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.util.List;

public class FlightFilterProcessor {
    private final List<FlightFilter> filters;

    public FlightFilterProcessor(List<FlightFilter> filters) {
        this.filters = filters;
    }

    public List<Flight> process(List<Flight> flights) {
        List<Flight> result = flights;
        for (FlightFilter filter : filters) {
            result = filter.filter(result);
        }
        return result;
    }
}