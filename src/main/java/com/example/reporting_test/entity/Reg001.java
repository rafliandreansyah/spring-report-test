package com.example.reporting_test.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "reg001")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reg001 {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="norecord", columnDefinition= "serial")
    private Long norecord;

    @Column(name="arc")
    private String arc;

    @Column(name="bdim")
    private Double bdim;
    
    @Column(name="bunga")
    private Double bunga;
    
    @Column(name="ccd")
    private Double ccd;
    
    @Column(name="class")
    private String classReg;
    
    @Column(name="dept")
    private Double dept;
    
    @Column(name="disc")
    private Double disc;
    
    @Column(name="discx")
    private Double discx;
    
    @Column(name="dstore")
    private String dstore;
    
    @Column(name="fbudpo")
    private String fbudpo;
    
    @Column(name="fdisc")
    private String fdisc;
    
    @Column(name="fkons")
    private String fkons;
    
    @Column(name="fmember")
    private String fmember;
    
    @Column(name="fnota")
    private String fnota;
    
    @Column(name="fppn")
    private String fppn;
    
    @Column(name="fsize")
    private String fsize;
    
    @Column(name="fsup")
    private String fsup;
    
    @Column(name="harga")
    private Double harga;
    
    @Column(name="hpokok")
    private Double hpokok;
    
    @Column(name="jam")
    private String jam;
    
    @Column(name="kasir")
    private String kasir;
    
    @Column(name="ket1")
    private String ket1;
    
    @Column(name="ket2")
    private String ket2;
    
    @Column(name="ket3")
    private String ket3;
    
    @Column(name="kode")
    private String kode;
    
    @Column(name="ldim")
    private Double ldim;
    
    @Column(name="merk")
    private String merk;
    
    @Column(name="noinv")
    private Integer noinv;
    
    @Column(name="nonota")
    private String nonota;
    
    @Column(name="noor")
    private String noor;
    
    @Column(name="nopram")
    private String nopram;
    
    @Column(name="nota")
    private String nota;
    
    @Column(name="nourut")
    private String nourut;
    
    @Column(name="pajak")
    private String pajak;
    
    @Column(name="pbunga")
    private Double pbunga;
    
    @Column(name="pdim")
    private Double pdim;
    
    @Column(name="pjk")
    private Double pjk;
    
    @Column(name="plg")
    private String plg;
    
    @Column(name="plu")
    private String plu;
    
    @Column(name="post")
    private String post;
    
    @Column(name="post_x")
    private String post_x;
    
    @Column(name="price")
    private Double price;
    
    @Column(name="prom")
    private String prom;
    
    @Column(name="qty")
    private Double qty;
    
    @Column(name="qty_x")
    private Double qty_x;
    
    @Column(name="reff")
    private String reff;
    
    @Column(name="register")
    private String register;
    
    @Column(name="serv")
    private Double serv;
    
    @Column(name="size")
    private String size;
    
    @Column(name="supervr")
    private String supervr;
    
    @Column(name="supplier")
    private String supplier;
    
    @Column(name="tgl")
    private Date tgl;
    
    @Column(name="tharga")
    private Double tharga;
    
    @Column(name="tipe")
    private String tipe;
    
    @Column(name="t_sel")
    private Date t_sel;
    
    @Column(name="warna")
    private String warna;
    
}
