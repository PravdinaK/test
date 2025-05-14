package com.gridnine.testing;

import com.gridnine.testing.filter.FilterFactory;
import com.gridnine.testing.util.FlightBuilder;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Исходный список перелётов:");
        printFlights(flights);

        applyFilter("1. Вылет до текущего момента:", flights, FilterFactory.departureAfterNow());
        applyFilter("2. Прилёт раньше вылета:", flights, FilterFactory.arrivalAfterDeparture());
        applyFilter("3. Время на земле больше 2 часов:", flights, FilterFactory.groundTimeNotExceeding(120));
    }

    private static void applyFilter(String title, List<Flight> flights, FlightFilter filter) {
        System.out.println("\n " + title + " ");
        List<Flight> result = filter.filter(flights);
        if (result.isEmpty()) {
            System.out.println("Нет рейсов, удовлетворяющих условию фильтра.");
        } else {
            printFlights(result);
        }
    }

    private static void printFlights(List<Flight> flights) {
        flights.forEach(flight -> System.out.println(" - " + flight));
    }
}