package com.omega.hrch.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="ugovor")
public class Ugovor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String kupac;
    private String broj_ugovora;
    private String datum_akontacije;
    private String rok_isporuke;
    private String status;

    @OneToMany(mappedBy = "ugovor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Artikl> artikls;

    public Ugovor(){}
    public Ugovor(Long id, String kupac, String broj_ugovora, String datum_akontacije, String rok_isporuke, String status){
        this.id=id;
        this.kupac=kupac;
        this.broj_ugovora=broj_ugovora;
        this.datum_akontacije=datum_akontacije;
        this.rok_isporuke=rok_isporuke;
        this.status=status;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}