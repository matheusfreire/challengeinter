package com.intercom.msf.service;

import com.intercom.msf.model.Coordinates;
import com.intercom.msf.model.Customer;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by matheus on 3/5/20.
 */
public final class CustomersService {
    private static final double HUNDRED_EIGHTY_DEGREE = 180.0;
    private static final double LAT_OFFICE_DUBLIN = 53.339428;
    private static final double LONG_OFFICE_DUBLIN = -6.257664;

    public static Set<Customer> findSortedCustomerWithin(int distance, List<Customer> customers) {
        Stream<Customer> customerStream = customers.stream().filter(customer -> isWithin(distance, customer));
        return customerStream.collect(Collectors.toCollection(TreeSet::new));
    }

    private static boolean isWithin(int distance, Customer customer){
        Coordinates coordOfficeDublin = new Coordinates(LAT_OFFICE_DUBLIN, LONG_OFFICE_DUBLIN);
        return distanceTwoCoordinatesInKm(coordOfficeDublin,customer.getCoordinates()) < distance;
    }

    private static double distanceTwoCoordinatesInKm(Coordinates co1, Coordinates co2) {
        double distance = calculateSin(co1.getLatitude()) * calculateSin(co2.getLatitude()) +
                calculateCos(co1.getLatitude()) * calculateCos(co2.getLatitude()) * calculateCos(co1.getLongitude() - co2.getLongitude());
        double arcCosDistanceInRad = Math.acos(distance);
        double distanceInDegree = convertRadToDegree(arcCosDistanceInRad);
        return distanceInDegree * 60 * 1.1515 * 1.609344;
    }

    private static double calculateSin(double degree){
        return Math.sin(convertDegreeToRad(degree));
    }

    private static double calculateCos(double degree){
        return Math.cos(convertDegreeToRad(degree));
    }

    private static double convertDegreeToRad(double degree){
        return degree * Math.PI / HUNDRED_EIGHTY_DEGREE;
    }

    private static double convertRadToDegree(double rad){
        return rad * HUNDRED_EIGHTY_DEGREE / Math.PI;
    }
}
