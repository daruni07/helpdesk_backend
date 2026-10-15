package com.example.helpdesk.model;

import com.example.helpdesk.enums.JobTitle;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Executive {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String contact;
    @Enumerated(EnumType.STRING)
    private JobTitle jobTitle;
    @OneToOne
    private User user;
    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;
}
