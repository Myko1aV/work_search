package com.architecture.ahfi.entities;

import javax.persistence.*;

import com.architecture.ahfi.Patterns.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
@Entity
@Table(name = "vacancy")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "status")
    private Boolean status;
    @Column(name = "salary", nullable = false)
    private Integer salary;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "companyID", nullable = false)
    private Company companyID;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "categoryID", nullable = false)
    private Category categoryID;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;

    @Column(name = "city")
    private String city;
    @Column(name = "experience")
    private Integer experience;

    public Vacancy(Integer id,Integer salary, LocalDate date){
        this.id = id;
        this.salary=salary;
        this.createdAt = date;
    }


}