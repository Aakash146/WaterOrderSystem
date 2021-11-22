package com.farmer.Order.Entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "farmer")
public class Farmer {

    @Id
    @SequenceGenerator(name = "farmer_sequence", sequenceName = "farmer_sequence", allocationSize =  1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "farmer_sequence")
    @Column(name = "farm_id")
    private Long farmId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Transient
    private String fullName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farmer", orphanRemoval = true)
    private List<Order> orderDetails = new ArrayList<>();

    public Farmer() {
        // Do Nothing
    }

    public Farmer(final Long farmId, final String firstName, final String lastName, final String email, final List<Order> orderDetails) {
        this.farmId = farmId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.orderDetails = orderDetails;
    }

    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public List<Order> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<Order> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Farmer that = (Farmer) o;
        return Objects.equals(farmId, that.farmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(farmId);
    }

    @Override
    public String toString() {
        return "FarmerDetails{" +
                "farmId=" + farmId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }
}

