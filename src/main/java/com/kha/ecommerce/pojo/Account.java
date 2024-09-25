package com.kha.ecommerce.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "[account]")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer customer;

    @Column(columnDefinition="bit default 0", nullable = false)
    private boolean require2fa = false;

    @Column(columnDefinition="bit default 1", nullable = false)
    private boolean dummy = true;

    private Role role;
    public enum Role {
        ADMIN,
        MEMBER,
        MANAGER,
        STAFF;
    }

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @NaturalId
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(max = 255, message = "Password must be less than 255 characters")
    @Column(name = "password", length = 255 ,columnDefinition = "NVARCHAR(255)")
    private String password;

    @Size(max = 15, message = "Phone must be less than 15 characters")
    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.DISABLED;

    public enum Status {
        ACTIVE,
        DISABLED
    }
    @Column(name = "balance", precision = 20, scale = 8)
    private BigDecimal balance = new BigDecimal(0);

    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
