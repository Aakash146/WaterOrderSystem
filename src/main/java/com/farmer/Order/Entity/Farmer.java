package com.farmer.Order.Entity;

import com.farmer.Order.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "farmer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Farmer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "farmer_generator")
    @GenericGenerator(name = "farmer_generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "farmer_id")
    private UUID farmId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Transient
    private String fullName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farmer", orphanRemoval = true)
    private List<Order> orderDetails = new ArrayList<>();

    public Farmer(final UUID farmId, final String firstName, final String lastName, final String email, final List<Order> orderDetails) {
        this.farmId = farmId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.orderDetails = orderDetails;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

