package com.ra.model.entity.customer;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    @Column(name = "email",length = 100,unique = true,nullable = false)
    private String email;
    @Column(name = "full_name",length = 100,nullable = false)
    private String fullName;
    @Column(name = "password",length = 100,nullable = false)
    private String password;
    @Column(name = "birthday")
    private LocalDate birthday;


}
