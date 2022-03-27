package com.far.recrutement.entities;

import javax.persistence.Tuple;

public class Data {
    private Long id;
    private String concours;
    private String sexe;
    private Long number;

    public Data(Tuple tuple) {
        this.id = Long.valueOf(tuple.get("id").toString());
        this.concours = tuple.get("concours").toString();
        this.sexe = tuple.get("sexe").toString();
        this.number = Long.valueOf(tuple.get("number").toString());
    }

    public String getConcours() {
        return concours;
    }

    public void setConcours(String concours) {
        this.concours = concours;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
