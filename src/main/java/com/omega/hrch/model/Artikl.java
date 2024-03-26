package com.omega.hrch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Data
@Entity
@Table(name="artikl")
public class Artikl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String naziv;
    private String dobavljac;
    private String status;

    @ManyToOne
    @Cascade({CascadeType.PERSIST})
    @JoinColumn(name="ugovor_id")
    @JsonIgnore
    public Ugovor ugovor;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }



}
