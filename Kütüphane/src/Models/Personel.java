package Models;

import service.PersonelIslemleri;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gamze
 */
public class Personel implements IKullanici{
    private int personel_id;
    private String personel_adi;
    private String personel_soyad;
    private String kullanici_adi;
    private String sifre;
    private String telefon;
    private String email;
    private String adres;
    private String dogum_tarihi;
    private boolean cinsiyet;

    public Personel() {
    }

    public Personel(int personel_id, String personel_adi, String personel_soyad, String kullanici_adi, String sifre, String telefon, String email, String adres, String dogum_tarihi, boolean cinsiyet) {
        this.personel_id = personel_id;
        this.personel_adi = personel_adi;
        this.personel_soyad = personel_soyad;
        this.kullanici_adi = kullanici_adi;
        this.sifre = sifre;
        this.telefon = telefon;
        this.email = email;
        this.adres = adres;
        this.dogum_tarihi = dogum_tarihi;
        this.cinsiyet = cinsiyet;
    }

    public Personel(String personel_adi, String personel_soyad, String kullanici_adi, String sifre, String telefon, String email, String adres, String dogum_tarihi, boolean cinsiyet) {
        this.personel_adi = personel_adi;
        this.personel_soyad = personel_soyad;
        this.kullanici_adi = kullanici_adi;
        this.sifre = sifre;
        this.telefon = telefon;
        this.email = email;
        this.adres = adres;
        this.dogum_tarihi = dogum_tarihi;
        this.cinsiyet = cinsiyet;
    }

    public int getPersonel_id() {
        return personel_id;
    }

    public void setPersonel_id(int personel_id) {
        this.personel_id = personel_id;
    }

    public String getPersonel_adi() {
        return personel_adi;
    }

    public void setPersonel_adi(String personel_adi) {
        this.personel_adi = personel_adi;
    }

    public String getPersonel_soyad() {
        return personel_soyad;
    }

    public void setPersonel_soyad(String personel_soyad) {
        this.personel_soyad = personel_soyad;
    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getDogum_tarihi() {
        return dogum_tarihi;
    }

    public void setDogum_tarihi(String dogum_tarihi) {
        this.dogum_tarihi = dogum_tarihi;
    }

    public boolean isCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(boolean cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    PersonelIslemleri personel = new PersonelIslemleri();
    
    @Override
    public int giris(String kullaniciAdi,String parola){
        return personel.giris(kullaniciAdi,parola,0);
    }
    
    
    
}
