package Models;

import service.PersonelIslemleri;

public class Akademisyen implements IKullanici{
    private int akademisyen_id;
    private String akademisyen_adi;
    private String akademisyen_soyad;
    private String kullanici_adi;
    private String sifre;
    private String telefon;
    private String email;
    private String adres;
    private String dogum_tarihi;
    private int cinsiyet;
     public Akademisyen() {
    }

    public Akademisyen(int akademisyen_id, String akademisyen_adi, String akademisyen_soyad, String kullanici_adi, String sifre, String telefon, String email, String adres, String dogum_tarihi, int cinsiyet) {
        this.akademisyen_id = akademisyen_id;
        this.akademisyen_adi = akademisyen_adi;
        this.akademisyen_soyad = akademisyen_soyad;
        this.kullanici_adi = kullanici_adi;
        this.sifre = sifre;
        this.telefon = telefon;
        this.email = email;
        this.adres = adres;
        this.dogum_tarihi = dogum_tarihi;
        this.cinsiyet = cinsiyet;
    }

   

    public int getAkademisyen_id() {
        return akademisyen_id;
    }

    public void setAkademisyen_id(int akademisyen_id) {
        this.akademisyen_id = akademisyen_id;
    }

    public String getAkademisyen_adi() {
        return akademisyen_adi;
    }

    public void setAkademisyen_adi(String akademisyen_adi) {
        this.akademisyen_adi = akademisyen_adi;
    }

    public String getAkademisyen_soyad() {
        return akademisyen_soyad;
    }

    public void setAkademisyen_soyad(String akademisyen_soyad) {
        this.akademisyen_soyad = akademisyen_soyad;
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

    public int getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(int cinsiyet) {
        this.cinsiyet = cinsiyet;
    }
     PersonelIslemleri personel = new PersonelIslemleri();

     @Override
    public int giris(String kullaniciAdi,String parola){
        return personel.giris(kullaniciAdi,parola,2);
    }
    
    
}
