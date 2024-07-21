package com.example.reporting_test.entity;

import java.sql.Date;
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
@Table(name="invbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invbl {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="norecord", columnDefinition= "serial")
    private Long norecord;

    @Column(name="bdim")
    private String bdim;

    @Column(name="b_adj")
    private Double b_adj;
    
    @Column(name="b_jual")
    private Double b_jual;
    
    @Column(name="b_pakai")
    private Double b_pakai;
    
    @Column(name="b_prci")
    private Double b_prci;
    
    @Column(name="b_prco")
    private Double b_prco;
    
    @Column(name="b_prcr")
    private Double b_prcr;
    
    @Column(name="b_returp")
    private Double b_returp;
    
    @Column(name="b_returs")
    private Double b_returs;
    
    @Column(name="b_terima")
    private Double b_terima;
    
    @Column(name="b_trani")
    private Double b_trani;
    
    @Column(name="b_trano")
    private Double b_trano;
    
    @Column(name="b_waste")
    private Double b_waste;
    
    @Column(name="class")
    private String classImvbl;
    
    @Column(name="dept")
    private String dept;
    
    @Column(name="e_prom")
    private Timestamp e_prom;
    
    @Column(name="fcc")
    private String fcc;
    
    @Column(name="fct")
    private Integer fct;
    
    @Column(name="fdisc")
    private String fdisc;
    
    @Column(name="fixcost")
    private Double fixcost;
    
    @Column(name="fppn")
    private String fppn;
    
    @Column(name="fppnbm")
    private String fppnbm;
    
    @Column(name="h_beli1")
    private Double h_beli1;
    
    @Column(name="h_beli2")
    private Double h_beli2;
    
    @Column(name="h_jual")
    private Double h_jual;
    
    @Column(name="h_jual1")
    private Double h_jual1;
    
    @Column(name="h_jual2")
    private Double h_jual2;
    
    @Column(name="h_jual3")
    private Double h_jual3;
    
    @Column(name="h_jual4")
    private Double h_jual4;
    
    @Column(name="h_j_baru")
    private Double h_j_baru;
    
    @Column(name="h_j_mark")
    private Double h_j_mark;
    
    @Column(name="h_j_tgl")
    private Timestamp h_j_tgl;
    
    @Column(name="h_pokok")
    private Double h_pokok;
    
    @Column(name="h_prom")
    private Double h_prom;
    
    @Column(name="h_promj")
    private Double h_promj;
    
    @Column(name="insentif")
    private Double insentif;
    
    @Column(name="inv")
    private String inv;
    
    @Column(name="jam1")
    private Integer jam1;
    
    @Column(name="jam2")
    private Integer jam2;
    
    @Column(name="kode")
    private String kode;
    
    @Column(name="kons")
    private String kons;
    
    @Column(name="ldim")
    private String ldim;
    
    @Column(name="lead_time")
    private Integer lead_time;
    
    @Column(name="lokasi")
    private String lokasi;
    
    @Column(name="lokasi_tk")
    private String lokasi_tk;
    
    @Column(name="l_pokok")
    private Double l_pokok;
    
    @Column(name="l_pokok1")
    private Double l_pokok1;
    
    @Column(name="markdw")
    private Double markdw;
    
    @Column(name="markdwj")
    private Double markdwj;
    
    @Column(name="markup")
    private Double markup;
    
    @Column(name="max")
    private Double max;
    
    @Column(name="max_toko")
    private Double max_toko;
    
    @Column(name="merk")
    private String merk;
    
    @Column(name="min")
    private Double min;
    
    @Column(name="min_toko")
    private Double min_toko;
    
    @Column(name="m_adj")
    private Double m_adj;
    
    @Column(name="m_jual")
    private Double m_jual;
    
    @Column(name="m_pakai")
    private Double m_pakai;
    
    @Column(name="m_prci")
    private Double m_prci;
    
    @Column(name="m_prco")
    private Double m_prco;
    
    @Column(name="m_prcr")
    private Double m_prcr;
    
    @Column(name="m_returp")
    private Double m_returp;
    
    @Column(name="m_returs")
    private Double m_returs;
    
    @Column(name="m_terima")
    private Double m_terima;
    
    @Column(name="m_trani")
    private Double m_trani;
    
    @Column(name="m_trano")
    private Double m_trano;
    
    @Column(name="m_waste")
    private Double m_waste;
    
    @Column(name="nama")
    private String nama;
    
    @Column(name="namax")
    private String namax;
    
    @Column(name="nobrg")
    private String nobrg;
    
    @Column(name="orderr")
    private Double orderr;
    
    @Column(name="pdim")
    private String pdim;
    
    @Column(name="plu")
    private String plu;

    @Column(name="ppn")
    private Double ppn;
    @Column(name="ppnbm")
    private Double ppnbm;
    @Column(name="qty_rsp")
    private Double qty_rsp;
    @Column(name="q_bo")
    private Double q_bo;
    @Column(name="q_int")
    private Double q_int;
    @Column(name="q_inttk")
    private Double q_inttk;
    @Column(name="q_order")
    private Double q_order;
    @Column(name="q_po")
    private Double q_po;
    @Column(name="q_rs")
    private Double q_rs;
    @Column(name="q_rstk")
    private Double q_rstk;
    @Column(name="q_toko")
    private Double q_toko;
    @Column(name="rank")
    private String rank;
    @Column(name="reff")
    private String reff;
    @Column(name="rsp")
    private String rsp;
    @Column(name="sell")
    private String sell;
    @Column(name="size")
    private String size;
    @Column(name="stok")
    private Double stok;
    @Column(name="supplier")
    private String supplier;
    @Column(name="supplier1")
    private String supplier1;
    @Column(name="supplier2")
    private String supplier2;
    @Column(name="sup_nobrg")
    private String sup_nobrg;
    @Column(name="s_prom")
    private Date s_prom;
    @Column(name="tcr")
    private String tcr;
    @Column(name="tdisc")
    private String tdisc;
    @Column(name="tgl_adj")
    private Date tgl_adj;
    @Column(name="tgl_beli")
    private Date tgl_beli;
    @Column(name="tgl_eff")
    private Date tgl_eff;
    @Column(name="tgl_hjual")
    private Date tgl_hjual;
    @Column(name="tgl_jual")
    private Date tgl_jual;
    @Column(name="tgl_pakai")
    private Date tgl_pakai;
    @Column(name="tgl_prci")
    private Date tgl_prci;
    @Column(name="tgl_prco")
    private Date tgl_prco;
    @Column(name="tgl_prcr")
    private Date tgl_prcr;
    @Column(name="tgl_returp")
    private Date tgl_returp;
    @Column(name="tgl_returs")
    private Date tgl_returs;
    @Column(name="tgl_rev")
    private Date tgl_rev;
    @Column(name="tgl_terima")
    private Date tgl_terima;
    @Column(name="tgl_trani")
    private Date tgl_trani;
    @Column(name="tgl_trano")
    private Date tgl_trano;
    @Column(name="tgl_ws")
    private Date tgl_ws;
    @Column(name="t_adj")
    private Double t_adj;
    @Column(name="t_jual")
    private Double t_jual;
    @Column(name="t_pakai")
    private Double t_pakai;
    @Column(name="t_prci")
    private Double t_prci;
    @Column(name="t_prco")
    private Double t_prco;
    @Column(name="t_prcr")
    private Double t_prcr;
    @Column(name="t_returp")
    private Double t_returp;
    @Column(name="t_returs")
    private Double t_returs;
    @Column(name="t_terima")
    private Double t_terima;
    @Column(name="t_trani")
    private Double t_trani;
    @Column(name="t_trano")
    private Double t_trano;
    @Column(name="t_waste")
    private Double t_waste;
    @Column(name="ukuran")
    private String ukuran;
    @Column(name="uom")
    private String uom;
    @Column(name="arc")
    private String arc;
    @Column(name="blterbit")
    private String blterbit;
    @Column(name="b_bruto")
    private Double b_bruto;
    @Column(name="b_emas")
    private Double b_emas;
    @Column(name="b_emasl")
    private Double b_emasl;
    @Column(name="fberlian")
    private String fberlian;
    @Column(name="gudang")
    private String gudang;
    @Column(name="gudangb")
    private String gudangb;
    @Column(name="gudangb1")
    private String gudangb1;
    @Column(name="gudangb2")
    private String gudangb2;
    @Column(name="gudangj")
    private String gudangj;
    @Column(name="gudangj1")
    private String gudangj1;
    @Column(name="gudangj2")
    private String gudangj2;
    @Column(name="gudangx")
    private String gudangx;
    @Column(name="h_m1_max")
    private Double h_m1_max;
    @Column(name="h_m1_tgl1")
    private Date h_m1_tgl1;
    @Column(name="h_m1_tgl2")
    private Date h_m1_tgl2;
    @Column(name="h_m2_max")
    private Double h_m2_max;
    @Column(name="h_m2_tgl1")
    private Date h_m2_tgl1;
    @Column(name="h_m2_tgl2")
    private Date h_m2_tgl2;
    @Column(name="h_m3_max")
    private Double h_m3_max;
    @Column(name="h_m3_tgl1")
    private Date h_m3_tgl1;
    @Column(name="h_m3_tgl2")
    private Date h_m3_tgl2;
    @Column(name="h_m4_max")
    private Double h_m4_max;
    @Column(name="h_m4_tgl1")
    private Date h_m4_tgl1;
    @Column(name="h_m4_tgl2")
    private Date h_m4_tgl2;
    @Column(name="h_m5_max")
    private Double h_m5_max;
    @Column(name="h_m5_tgl1")
    private Date h_m5_tgl1;
    @Column(name="h_m5_tgl2")
    private Date h_m5_tgl2;
    @Column(name="h_mark1")
    private Double h_mark1;
    @Column(name="h_mark2")
    private Double h_mark2;
    @Column(name="h_mark3")
    private Double h_mark3;
    @Column(name="h_mark4")
    private Double h_mark4;
    @Column(name="h_mark5")
    private Double h_mark5;
    @Column(name="id")
    private String id;
    @Column(name="inbns")
    private Double inbns;
    @Column(name="inf")
    private String inf;
    @Column(name="infbef")
    private String infbef;
    @Column(name="jam")
    private String jam;
    @Column(name="karang")
    private String karang;
    @Column(name="kberlian")
    private String kberlian;
    @Column(name="kel")
    private Double kel;
    @Column(name="kel1")
    private Double kel1;
    @Column(name="kel2")
    private Double kel2;
    @Column(name="lokasi_bs")
    private Double lokasi_bs;
    @Column(name="nofaktur")
    private String nofaktur;
    @Column(name="nopesanan")
    private String nopesanan;
    @Column(name="outbns")
    private Double outbns;
    @Column(name="partno")
    private String partno;
    @Column(name="plg")
    private String plg;
    @Column(name="plubef")
    private String plubef;
    @Column(name="q_intbs")
    private Double q_intbs;
    @Column(name="q_rsbs")
    private Double q_rsbs;
    @Column(name="rtrbns")
    private Double rtrbns;
    @Column(name="sel")
    private Double sel;
    @Column(name="sel1")
    private Double sel1;
    @Column(name="sel2")
    private Double sel2;
    @Column(name="stokbns")
    private Double stokbns;
    @Column(name="stokbs")
    private Double stokbs;
    @Column(name="sts")
    private String sts;
    @Column(name="terbit")
    private String terbit;
    @Column(name="tgl")
    private Date tgl;
    @Column(name="tglb")
    private Date tglb;
    @Column(name="tglb1")
    private Date tglb1;
    @Column(name="tglb2")
    private Date tglb2;
    @Column(name="tglj")
    private Date tglj;
    @Column(name="tglj1")
    private Date tglj1;
    @Column(name="tglj2")
    private Date tglj2;
    @Column(name="tgl_i_bns")
    private Date tgl_i_bns;
    @Column(name="tgl_o_bns")
    private Date tgl_o_bns;
    @Column(name="tgl_r_bns")
    private Date tgl_r_bns;
}
