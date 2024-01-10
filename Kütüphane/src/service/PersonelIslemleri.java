package service;
import Models.Kitap;
import Models.personelBilgi;
import Patterns.BridgeIliskiselVeritabaniBaglayicisi;
import Patterns.BridgeMySQLBaglantisi;
import Patterns.BridgeVeritabaniBaglayici;
import Patterns.KitapSiralamaStrategy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonelIslemleri {
    private Connection con;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private KitapSiralamaStrategy siralamaStrategy;
    public PersonelIslemleri(){
        BridgeVeritabaniBaglayici baglayici = new BridgeIliskiselVeritabaniBaglayicisi(new BridgeMySQLBaglantisi());
        con = baglayici.baglan();
        
}
    
    public int giris(String kullanici_adi,String parola, int unvan){
        String sorgu = "";
        int kullaniciId = -1;

        if(unvan == 0){
            sorgu = "select*from personel where kullaniciAdi=? and sifre=?  ";
        }
        else if (unvan == 1){
            sorgu = "select*from ogrenci where kullaniciAdi=? and sifre=?  ";
        }else if (unvan == 2){
            sorgu = "select*from akademisyen where kullaniciAdi=? and sifre=?  ";
        }
        
        try{
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1, kullanici_adi);
            preparedStatement.setString(2,parola);
            
            ResultSet rs=preparedStatement.executeQuery();
            
            while(rs.next()){
                if(unvan == 0){
                kullaniciId = rs.getInt("personel_id");
            
                }
                else if (unvan == 1){
                    kullaniciId = rs.getInt("ogrenci_id");
                }else if (unvan == 2){
                    kullaniciId = rs.getInt("akademisyen_id");
                }
            }
            
            
            return kullaniciId;
            
        }catch(SQLException ex){
            Logger.getLogger(PersonelIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
            
        }
        
    }
    
    public void setSiralamaStrategy(KitapSiralamaStrategy siralamaStrategy) {
        this.siralamaStrategy = siralamaStrategy;
    }
    public ArrayList<Kitap> kitaplariAdinaGoreListele(ArrayList<Kitap> kitaplar) {
        kitaplar = kitaplariListele(); // Veritabanından tüm kitapları çek
        siralamaStrategy.sort(kitaplar); 
        return kitaplar;
    }
    public ArrayList<Kitap> kitaplariListele(){//array list dönücek metod
        ArrayList<Kitap> cıktı=new ArrayList<>();
        try {
            //veritabanından her bilgi alındığında çıktı değişkenine değer eklenir ve bu değer en sonunda fonksiyon çıktısı olarak dönücek
            statement=con.createStatement();
            String sorgu="select kitap_id,kitap_adi,yazar,sayfa_sayisi,yayinevi,kitap_turu,kategori,basin_yili,kitaplik_adi,raf_adi,barkod_sayisi,ekleyen_id,eklenme_tarihi,durum from kitap";
            ResultSet rs=statement.executeQuery(sorgu);
            while(rs.next()){
                int id=rs.getInt("kitap_id");
                String kitap_ad=rs.getString("kitap_adi");
                String yazar=rs.getString("yazar");
                int sayfa=rs.getInt("sayfa_sayisi");
                String yayinevi=rs.getString("yayinevi");
                String tür=rs.getString("kitap_turu");
                String kategori=rs.getString("kategori");
                String basin_yili=rs.getString("basin_yili");
                String kitaplik_adi=rs.getString("kitaplik_adi");
                String raf=rs.getString("raf_adi");
                String barkod_sayisi=rs.getString("barkod_sayisi");
                int personel_id=rs.getInt("ekleyen_id");
                Date eklenme_tarihi=rs.getDate("eklenme_tarihi");
                String durum=rs.getString("durum");
                cıktı.add(new Kitap(id,kitap_ad,yazar,sayfa,yayinevi,tür,kategori,basin_yili,kitaplik_adi,raf,barkod_sayisi,personel_id,eklenme_tarihi,durum));
                
            }
            return  cıktı;
        } catch (SQLException ex) {
            Logger.getLogger(PersonelIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public void kitapEkle(String kitap_adi, String yazar, int sayfa_sayisi, String yayinevi, String kitap_türü, String kategori, java.util.Date basin_yili, String kitaplik_adi, String raf, String barkod_sayisi, int ekleyen_id, Date eklenme_tarihi, String durum){
        try{
            String sorgu="insert into kitap(kitap_adi,yazar,sayfa_sayisi,yayinevi,kitap_turu,kategori,basin_yili,kitaplik_adi,raf_adi,barkod_sayisi,ekleyen_id,eklenme_tarihi,durum) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1, kitap_adi);
            preparedStatement.setString(2, yazar);
            preparedStatement.setInt(3, sayfa_sayisi);
            preparedStatement.setString(4, yayinevi);
            preparedStatement.setString(5, kitap_türü);
            preparedStatement.setString(6, kategori);
            preparedStatement.setDate(7, new java.sql.Date(basin_yili.getTime()));
            preparedStatement.setString(8, kitaplik_adi);
            preparedStatement.setString(9, raf);
            preparedStatement.setString(10, barkod_sayisi);
            preparedStatement.setInt(11, ekleyen_id);
            preparedStatement.setDate(12, new java.sql.Date(eklenme_tarihi.getTime()));
            preparedStatement.setString(13, durum);
            preparedStatement.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(PersonelIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    public void kitapSil(String kitap_adi,String kitap_id){
        try{
            String sorgu="delete from kitap where kitap_adi=?  and kitap_id=?";
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1, kitap_adi);
            preparedStatement.setString(2, kitap_id);
            preparedStatement.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(PersonelIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void kitapGuncelle(int id, String kitap_adi, String yazar, int sayfa_sayisi, String yayinevi, String kitap_türü, String kategori, String basin_yili, String kitaplik_adi, String raf, String barkod_sayisi, int ekleyen_id, Date eklenme_tarihi, String durum){
        try{
            String sorgu="update kitap set kitap_adi=?,yazar=? , sayfa_sayisi=? ,yayinevi=?, kitap_turu=?,kategori=?,basin_yili=?,kitaplik_adi=?,raf_adi=?,barkod_sayisi=?,ekleyen_id=?,eklenme_tarihi=?,durum=? where kitap_id=?";
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1,kitap_adi);
            preparedStatement.setString(2, yazar);
            preparedStatement.setInt(3, sayfa_sayisi);
            preparedStatement.setString(4, yayinevi);
            preparedStatement.setString(5, kitap_türü);
            preparedStatement.setString(6, kategori);
            preparedStatement.setString(7, basin_yili);
            preparedStatement.setString(8, kitaplik_adi);
            preparedStatement.setString(9, raf);
            preparedStatement.setString(10, barkod_sayisi);
            preparedStatement.setInt(11, ekleyen_id);
            preparedStatement.setDate(12, new java.sql.Date(eklenme_tarihi.getTime()));
            preparedStatement.setString(13, durum);
            preparedStatement.setInt(14, id);
            preparedStatement.executeUpdate();
            
        }catch(SQLException ex){
            Logger.getLogger(PersonelIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
 
    public personelBilgi personelBilgileriniGetir(int personelId) {
        personelBilgi bilgi = new personelBilgi();

        try {
            String query = "SELECT * FROM personel WHERE personel_id = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1, personelId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Veritabanından gelen değerleri personelBilgi nesnesine set et
                        bilgi.setPersonelId(resultSet.getInt("personel_id"));
                        bilgi.setPersonelAd(resultSet.getString("personel_adi"));
                        bilgi.setPersonelSoyad(resultSet.getString("soyadi"));
                        bilgi.setKullaniciAdi(resultSet.getString("kullaniciAdi"));
                        bilgi.setParola(resultSet.getString("sifre"));
                        bilgi.setTelefon(resultSet.getString("telefon"));
                        bilgi.setEposta(resultSet.getString("email"));
                        bilgi.setAdres(resultSet.getString("adres"));
                        bilgi.setDogumTarihi(resultSet.getDate("dogum_tarihi"));
                        bilgi.setCinsiyet(resultSet.getInt("cinsiyet"));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonelIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bilgi;
    }

    
    
}
