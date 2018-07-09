package com.example.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String email;
    @Column
    private String password;
    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType type;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_product",
            joinColumns={@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns={@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products;
}
