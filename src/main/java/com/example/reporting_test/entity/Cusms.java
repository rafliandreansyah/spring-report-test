package com.example.reporting_test.entity;

import java.sql.Timestamp;

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

@Entity
@Table(name="cusms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cusms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="norecord", columnDefinition= "serial")
    private Long norecord;

    @Column(name="agama")
    private String agama;

    @Column(name="alamat1")
    private String alamat1;

    @Column(name="alamat2")
    private String alamat2;

    @Column(name="ambil")
    private Double ambil;

    @Column(name="area")
    private String area;

    @Column(name="bacc")
    private String bacc;

    @Column(name="b_cair")
    private Double b_cair;

    @Column(name="b_cairx")
    private Double b_cairx;

    @Column(name="b_omzet")
    private Double b_omzet;

    @Column(name="cr_limit")
    private Double cr_limit;

    @Column(name="depo01")
    private Double depo01;

    @Column(name="depo03")
    private Double depo03;

    @Column(name="depo06")
    private Double depo06;

    @Column(name="depo12")
    private Double depo12;

    @Column(name="depo24")
    private Double depo24;

    @Column(name="disc")
    private Double disc;

    @Column(name="disc1")
    private Double disc1;

    @Column(name="email")
    private String email;
    
    @Column(name="expdate")
    private Timestamp expdate;
    
    @Column(name="flag")
    private String flag;
    
    @Column(name="fover")
    private String fover;

    @Column(name="fpoint")
    private String fpoint;

    @Column(name="hutang")
    private Double hutang;
    
    @Column(name="hutangx")
    private Double hutangx;
    
    @Column(name="jns")
    private String jns;

    @Column(name="kelamin")
    private String kelamin;

    @Column(name="ket")
    private String ket;

    @Column(name="ket1")
    private String ket1;

    @Column(name="ket2")
    private String ket2;

    @Column(name="kode")
    private String kode;

    @Column(name="kodepos")
    private String kodepos;
    
    @Column(name="kota")
    private String kota;

    @Column(name="ld_bayar")
    private Timestamp ld_bayar;

    @Column(name="ld_beli")
    private Timestamp ld_beli;

    @Column(name="member")
    private String member;

    @Column(name="merk")
    private String merk;

    @Column(name="merkt")
    private String merkt;

    @Column(name="nama")
    private String nama;

    @Column(name="nip")
    private String nip;

    @Column(name="nofax")
    private String nofax;

    @Column(name="noktp")
    private String noktp;

    @Column(name="npwp")
    private String npwp;

    @Column(name="pekerjaan")
    private String pekerjaan;

    @Column(name="person")
    private String person;

    @Column(name="pinjb01")
    private Double pinjb01;

    @Column(name="pinjb02")
    private Double pinjb02;

    @Column(name="pinjint")
    private Double pinjint;

    @Column(name="pinjsem")
    private Double pinjsem;

    @Column(name="pinju01")
    private Double pinju01;

    @Column(name="pinju02")
    private Double pinju02;

    @Column(name="pinju03")
    private Double pinju03;

    @Column(name="pkp")
    private String pkp;

    @Column(name="plg")
    private String plg;
    
    @Column(name="point")
    private Double point;

    @Column(name="pointint")
    private Double pointint;

    @Column(name="ptd_bayar")
    private Double ptd_bayar;

    @Column(name="ptd_beli")
    private Double ptd_beli;

    @Column(name="reff")
    private String reff;

    @Column(name="region")
    private String region;

    @Column(name="retur")
    private Double retur;

    @Column(name="returx")
    private Double returx;

    @Column(name="saham")
    private Double saham;

    @Column(name="ship")
    private String ship;

    @Column(name="simjib")
    private Double simjib;

    @Column(name="simkus")
    private Double simkus;

    @Column(name="simpok")
    private Double simpok;

    @Column(name="siup")
    private String siup;

    @Column(name="tabsuka")
    private Double tabsuka;

    @Column(name="telp")
    private String telp;

    @Column(name="terms")
    private String terms;

    @Column(name="tgllahir")
    private Timestamp tgllahir;

    @Column(name="tgl_ambil")
    private Timestamp tgl_ambil;

    @Column(name="tgl_beli")
    private Timestamp tgl_beli;

    @Column(name="tgl_point")
    private Timestamp tgl_point;

    @Column(name="trtory")
    private String trtory;

    @Column(name="t_omzet")
    private String t_omzet;

    @Column(name="umuka")
    private String umuka;

    @Column(name="umukax")
    private String umukax;

    @Column(name="ytd_bayar")
    private String ytd_bayar;

    @Column(name="ytd_beli")
    private String ytd_beli;


}
