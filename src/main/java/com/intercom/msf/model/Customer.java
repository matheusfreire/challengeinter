package com.intercom.msf.model;

import java.util.Objects;

/**
 * Created by matheus on 3/5/20.
 */
public class Customer implements Comparable<Customer>{

    private int id;

    private String name;

    private Coordinates coordinates;

    public Customer(int id, String name, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public int compareTo(Customer o) {
        return this.id - o.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(name, customer.name) &&
                Objects.equals(coordinates, customer.coordinates);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
