package com.kha.ecommerce.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "[customer]")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment avatarUrl;

    @Column(name = "nickname", length = 100,columnDefinition = "NVARCHAR(100)")
    private String nickname;

    @Column(name = "prefferred_department", length = 50,columnDefinition = "NVARCHAR(50)")
    private String preferredDepartment;

    @Column(name = "height")
    private int height;

    @Column(name = "weight")
    private int weight;


    @Column(name = "age_group", columnDefinition = "NVARCHAR(100)")
    private String ageGroup;

    @Column(columnDefinition = "NVARCHAR(100)")
    private String interests;

}
