package com.example.KaryawanThymeleaf1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class Karyawan {
    private final String id;
    @NotBlank
    private final String nama;
    private final Date tglMasuk;
    private final String noHP;
    private final String reimbursement;
    private final Date tglBuat;
    private final Date tglUpdate;



    //Add @JsonProperty Annotation so Spring recognizes the JSON API Payload
    public Karyawan(@JsonProperty("id") String id,
                    @JsonProperty("nama") String nama,
                    @JsonProperty("tglMasuk") Date tglMasuk,
                    @JsonProperty("noHP") String noHP,
                    @JsonProperty("reimbursement") String reimbursement,
                    @JsonProperty("tglBuat") Date tglBuat,
                    @JsonProperty("tglUpdate") Date tglUpdate) {

        this.id = id;
        this.nama = nama;
        this.tglMasuk = tglMasuk;
        this.noHP = noHP;
        this.reimbursement = reimbursement;
        this.tglBuat = tglBuat;
        this.tglUpdate = tglUpdate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return nama;
    }

    public Date getTglMasuk() {
        return tglMasuk;
    }

    public String getNoHP() {
        return noHP;
    }

    public String getReimbursement() {
        return reimbursement;
    }

    public Date getTglBuat() {
        return tglBuat;
    }

    public Date getTglUpdate() {
        return tglUpdate;
    }
}
