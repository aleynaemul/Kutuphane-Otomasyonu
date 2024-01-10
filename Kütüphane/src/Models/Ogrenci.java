package Models;

import service.PersonelIslemleri;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pc
 */
public class Ogrenci implements IKullanici{
    private int ogrenci_id;
    private  String adi;
    private String soyadi;
    private String kullaniciAdi;
    private String sifre;
    private String email;
    private String telefon;
    private String adres;
    private String dogum_tarihi;
    private int cinsiyet;
    


    public Ogrenci(int ogrenci_id, String adi, String soyadi, String kullaniciAdi, String sifre, String telefon, String email, String dogum_tarihi,String adres,int cinsiyet) {
        this.ogrenci_id = ogrenci_id;
        this.adi = adi;
        this.soyadi = soyadi;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.email = email;
        this.telefon = telefon;
        this.adres=adres;
        this.dogum_tarihi = dogum_tarihi;
        this.cinsiyet = cinsiyet;
    }

    public Ogrenci() {
    }
    
    

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getOgrenci_id() {
        return ogrenci_id;
    }

    public void setOgrenci_id(int ogrenci_id) {
        this.ogrenci_id = ogrenci_id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

        public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getDogum_tarihi() {
        return dogum_tarihi;
    }

    public void setDogum_tarihi(String dogum_tarihi) {
        this.dogum_tarihi = dogum_tarihi;
    }

    public int getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(int cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    PersonelIslemleri personel = new PersonelIslemleri();

     @Override
    public int giris(String kullaniciAdi,String parola){
        return personel.giris(kullaniciAdi,parola,1);
    }
    
    
}
