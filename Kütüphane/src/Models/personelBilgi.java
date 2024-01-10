package Models;
import java.util.Date;

public class personelBilgi extends javax.swing.JFrame {

    private String personelAd;
    private String personelSoyad;
    private String kullaniciAdi;
    private String parola;
    private String telefon;
    private String eposta;
    private String adres;
    private Date dogumTarihi;
    private int cinsiyet;
    private int personelId;
    
    
    

    // Diğer bileşenler

    // Constructor ve diğer bileşen metotları

    public void personelBilgi(int personelId) {
        // Veritabanından bilgileri çek ve alanları güncelle
        // Bu metodu PersonelIslemleri sınıfındaki personelBilgileriniGoster metodunda çağırabilirsin
        // Bu metot içinde PersonelBilgileri sınıfının alanlarına veritabanından çekilen değerleri atayabilirsin
        // Örnek olarak: this.personelAd = ...; gibi
    }

    // Getter ve Setter metotları
    public String getPersonelAd() {
        return personelAd;
    }

    public void setPersonelAd(String personelAd) {
        this.personelAd = personelAd;
    }

    public String getPersonelSoyad() {
        return personelSoyad;
    }

    public void setPersonelSoyad(String personelSoyad) {
        this.personelSoyad = personelSoyad;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Date getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(Date dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public int getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(int cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public int getPersonelId() {
        return personelId;
    }

    public void setPersonelId(int personelId) {
        this.personelId = personelId;
    }
}
