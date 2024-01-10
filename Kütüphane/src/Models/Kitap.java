package Models;
import java.util.Date;

public class Kitap {
    private int kitapID;
    private String kitap_adi;
    private String yazar;
    private int sayfa_sayisi;
    private String yayinevi;
    private String kitap_türü;
    private String kategori;
    private String basin_yili;
    private String kitaplik_adi;
    private String raf;
    private String barkod_sayisi;
    private int ekleyen;
    private Date eklenme_tarihi;
    private String durum;

    public Kitap(int kitapID, String kitap_adi, String yazar, int sayfa_sayisi, String yayinevi, String kitap_türü, String kategori, String basin_yili, String kitaplik_adi, String raf, String barkod_sayisi,int ekleyen,java.util.Date eklenme_tarihi, String durum) {
        this.kitapID = kitapID;
        this.kitap_adi = kitap_adi;
        this.yazar = yazar;
        this.sayfa_sayisi = sayfa_sayisi;
        this.yayinevi = yayinevi;
        this.kitap_türü = kitap_türü;
        this.kategori = kategori;
        this.basin_yili = basin_yili;
        this.kitaplik_adi = kitaplik_adi;
        this.raf = raf;
        this.barkod_sayisi = barkod_sayisi;
        this.ekleyen = ekleyen;
        this.eklenme_tarihi = eklenme_tarihi;
        this.durum=durum;
    }

    public int getKitapID() {
        return kitapID;
    }

    public void setKitapID(int kitapID) {
        this.kitapID = kitapID;
    }

    public String getKitap_adi() {
        return kitap_adi;
    }

    public void setKitap_adi(String kitap_adi) {
        this.kitap_adi = kitap_adi;
    }

    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }

    public int getSayfa_sayisi() {
        return sayfa_sayisi;
    }

    public void setSayfa_sayisi(int sayfa_sayisi) {
        this.sayfa_sayisi = sayfa_sayisi;
    }

    public String getYayinevi() {
        return yayinevi;
    }

    public void setYayinevi(String yayinevi) {
        this.yayinevi = yayinevi;
    }

    public String getKitap_türü() {
        return kitap_türü;
    }

    public void setKitap_türü(String kitap_türü) {
        this.kitap_türü = kitap_türü;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getBasin_yili() {
        return basin_yili;
    }

    public void setBasin_yili(String basin_yili) {
        this.basin_yili = basin_yili;
    }

    public String getKitaplik_adi() {
        return kitaplik_adi;
    }

    public void setKitaplik_adi(String kitaplik_adi) {
        this.kitaplik_adi = kitaplik_adi;
    }

    public String getRaf() {
        return raf;
    }

    public void setRaf(String raf) {
        this.raf = raf;
    }

    public String getBarkod_sayisi() {
        return barkod_sayisi;
    }

    public void setBarkod_sayisi(String barkod_sayisi) {
        this.barkod_sayisi = barkod_sayisi;
    }

    public int getEkleyen() {
        return ekleyen;
    }

    public void setEkleyen(int ekleyen) {
        this.ekleyen = ekleyen;
    }

    public Date getEklenme_tarihi() {
        return eklenme_tarihi;
    }

    public void setEklenme_tarihi(Date eklenme_tarihi) {
        this.eklenme_tarihi = eklenme_tarihi;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getKitapAdi() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getSayfaSayisi() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
     
    
    
   

    



 
  

    

    
    
}
