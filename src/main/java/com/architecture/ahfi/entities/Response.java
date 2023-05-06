package com.architecture.ahfi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "response")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    private User userID;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "vacancyID", nullable = false)
    private Vacancy vacancyID;

    @Column(name = "file", nullable = false)
    private byte[] file;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Vacancy getVacancyID() {
        return vacancyID;
    }

    public void setVacancyID(Vacancy vacancyID) {
        this.vacancyID = vacancyID;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Response( User user,Vacancy vacancy,byte[] file){
        this.file= file;
        this.userID= user;
        this.vacancyID=vacancy;
    }
}