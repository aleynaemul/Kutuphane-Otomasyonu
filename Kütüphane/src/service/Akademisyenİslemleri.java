package service;


import Models.Akademisyen;
import Models.Kitap;
import Patterns.BridgeIliskiselVeritabaniBaglayicisi;
import Patterns.BridgeMySQLBaglantisi;
import Patterns.BridgeVeritabaniBaglayici;
import Patterns.KitapState;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Akademisyenİslemleri extends Kitapİslemleri{
    private Connection con;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private KitapState guncelDurum;

    public void setGuncelDurum(KitapState guncelDurum) {
        this.guncelDurum = guncelDurum;
    }

    public List<Kitap> kitaplarGuncelDurumuListele(List<Kitap> kitaplar){
        return guncelDurum.kitapDurumuState(kitaplar);
    }
    
    
    
    public Akademisyenİslemleri() {
        BridgeVeritabaniBaglayici baglayici = new BridgeIliskiselVeritabaniBaglayicisi(new BridgeMySQLBaglantisi());
        con = baglayici.baglan();
    }
    
    public  void OduncVer(int kitap_id, int ogrenci_id,int akademisyen_id, java.util.Date verilis_tarihi)
    {
        try{
            String sorgu="insert into odunc_alma(kitap_id,ogrenci_id,akademisyen_id,odunc_alinma_tarihi) values (?,?,?,?)";
            String sorgu2="update kitap set durum='Kitap Ödünç Alındı' where kitap_id=?";
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setInt(1, kitap_id);
            preparedStatement.setInt(2, ogrenci_id);
            preparedStatement.setInt(3, akademisyen_id);
            preparedStatement.setDate(4, new java.sql.Date(verilis_tarihi.getTime()));
            preparedStatement.executeUpdate();
          
            preparedStatement=con.prepareStatement(sorgu2);
            preparedStatement.setInt(1, kitap_id);
            preparedStatement.executeUpdate();
            
        }catch(SQLException ex){
            Logger.getLogger(PersonelIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public int iadeEtme(int ogrenci_id,int akademisyen_id,int kitapId,int odunc_id,java.util.Date odunc_alinma_tarihi){

            String query = "insert into cezalar (ogrenci_id,akademisyen_id,gun,cezaMiktari) values(?,?,?,?)";
            String query2 = "Update odunc_alma set iade_tarihi = ? where odunc_id = ?";
            String query3 = "Update kitap set durum = ? where kitap_id = ?";
            try 
            {
                
                LocalDate today =  LocalDate.now();
                LocalDate teslimEtmesiGerekTarih =  new java.sql.Date(odunc_alinma_tarihi.getTime()).toLocalDate().plus(2, ChronoUnit.WEEKS);
                long haftaFarki = ChronoUnit.WEEKS.between(today, teslimEtmesiGerekTarih);
                
                java.sql.Date sqlBugununTarihi = java.sql.Date.valueOf(today);
                
                if(Math.abs(haftaFarki) > 2){
                    int gunSayisi = (int) Math.abs((haftaFarki - 2)) * 7;
                    int cezaMiktari = Math.abs(gunSayisi * 10);
                    
                    preparedStatement = con.prepareStatement(query3);
                    preparedStatement.setString(1, "Kitap Bulunuyor");
                    preparedStatement.setInt(2, kitapId);
                    preparedStatement.executeUpdate();
                    
                    preparedStatement = con.prepareStatement(query2);
                    preparedStatement.setDate(1, sqlBugununTarihi);
                    preparedStatement.setInt(2, odunc_id);
                    preparedStatement.executeUpdate();
                        
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setInt(1, ogrenci_id);
                    preparedStatement.setInt(2, akademisyen_id);
                    preparedStatement.setInt(3, gunSayisi);
                    preparedStatement.setInt(4, cezaMiktari);
                    return preparedStatement.executeUpdate();
                }
                else{
                    
                    preparedStatement = con.prepareStatement(query3);
                    preparedStatement.setString(1, "Kitap Bulunuyor");
                    preparedStatement.setInt(2, kitapId);
                    preparedStatement.executeUpdate();
                    
                    preparedStatement = con.prepareStatement(query2);
                    preparedStatement.setDate(1, sqlBugununTarihi);
                    preparedStatement.setInt(2, odunc_id);
                    return preparedStatement.executeUpdate();
                }
                
                
                             
            }
        
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return -1;
        }
   
    }
    
    @Override
     public void updateKitapDurumu(int kitapId) {
        String query = "UPDATE kitap SET durum = 1 WHERE kitap_id = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, kitapId);
            preparedStatement.executeUpdate();

            // Kitap durumu güncellendikten sonra odunc_alma tablosundan kitap_id'yi sil
            deleteOduncAlmaByKitapId(kitapId);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Kitap durumu güncellenirken bir hata oluştu.");
        }
    }
     @Override
     public void deleteOduncAlmaByKitapId(int kitapId) {
        String query = "DELETE FROM odunc_alma WHERE kitap_id = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, kitapId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Kitap durumu 1 ise odunc_alma tablosundan kitap_id silinirken bir hata oluştu.");
        }
    }
    @Override
    public  boolean iadeTarihiGecmisMi(int uye_id, Date bugun) throws SQLException {
        String sorgu = "SELECT COUNT(*) FROM odunc_alma WHERE akademisyen_id = ? AND iade_tarihi < ?";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setInt(1, uye_id);
            preparedStatement.setDate(2, bugun);

            try {
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    int oduncSayisi = rs.getInt(1);
                    return oduncSayisi > 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    

    
    
    public ArrayList<Akademisyen> akademisyenListele(){//array list dönücek metod
        ArrayList<Akademisyen> cıktı=new ArrayList<>();
        try {
            //veritabanından her bilgi alındığında çıktı değişkenine değer eklenir ve bu değer en sonunda fonksiyon çıktısı olarak dönücek
            statement=con.createStatement();
            String sorgu="select*from akademisyen";
            ResultSet rs=statement.executeQuery(sorgu);
            while(rs.next()){
                int akademisyen_id=rs.getInt("akademisyen_id");
                String adi=rs.getString("adi");
                String soyadi=rs.getString("soyadi");
                String kullanici_adi=rs.getString("kullaniciAdi");
                String sifre=rs.getString("sifre");
                String email=rs.getString("email");
                String telefon=rs.getString("telefon");
                String adres=rs.getString("adres");
                String dogum_tarihi=rs.getString("dogum_tarihi");
                int cinsiyet=rs.getInt("cinsiyet");
                cıktı.add(new Akademisyen(akademisyen_id, kullanici_adi, adres, kullanici_adi, sifre, telefon, email, adres, dogum_tarihi, cinsiyet));
            }
            return  cıktı;
        } catch (SQLException ex) {
            Logger.getLogger(PersonelIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public ArrayList<Kitap> kitaplariListele(){ //array list dönücek metod
        ArrayList<Kitap> cıktı=new ArrayList<>();
        try {
            //veritabanından her bilgi alındığında çıktı değişkenine değer eklenir ve bu değer en sonunda fonksiyon çıktısı olarak dönücek
            statement=con.createStatement();
            String sorgu="select*from kitap";
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
                int personel_id=rs.getInt("personel_id");
                Date eklenme_tarihi=rs.getDate("eklenme_tarihi");
                String durum=rs.getString("durum");
                cıktı.add(new Kitap(id,kitap_ad,yazar,sayfa,yayinevi,tür,kategori,basin_yili,kitaplik_adi,raf,barkod_sayisi,personel_id,eklenme_tarihi,durum));
                
            }
            return  cıktı;
        } catch (SQLException ex) {
            Logger.getLogger(PersonelIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }/*
      public ArrayList<Object[]> cezaGetir() {
        ArrayList<Object[]> cezalar = new ArrayList<>();

        try {
            String sorgu = "SELECT ceza_id,ogrenci-id,o.ogrenci_adi,gun,cezaMiktari FROM cezalar c inner join  ogrenci o on o.ogrenci_id= c.ceza_id";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sorgu);

            while (resultSet.next()) {
                Object[] row = {
                    resultSet.getInt("kitap_id"),
                    resultSet.getString("kitap_adi"),
                    resultSet.getString("kitap_turu"),
                    resultSet.getInt("ogrenci_id"),
                    resultSet.getInt("odunc_id"),
                    resultSet.getDate("odunc_alinma_tarihi")
                };
                cezalar.add(row);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return oduncAlinanKitaplar;
    }
 */
    @Override
    public ArrayList<Object[]> oduncAlinanKitaplariGetir() {
        ArrayList<Object[]> oduncAlinanKitaplar = new ArrayList<>();

        try {
            String sorgu = "SELECT oa.kitap_id, k.kitap_adi, k.kitap_turu, oa.akademisyen_id, oa.odunc_id, oa.odunc_alinma_tarihi FROM odunc_alma oa inner join  kitap k on k.kitap_id = oa.kitap_id";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sorgu);

            while (resultSet.next()) {
                Object[] row = {
                    resultSet.getInt("kitap_id"),
                    resultSet.getString("kitap_adi"),
                    resultSet.getString("kitap_turu"),
                    resultSet.getInt("akademisyen_id"),
                    resultSet.getInt("odunc_id"),
                    resultSet.getDate("odunc_alinma_tarihi")
                };
                oduncAlinanKitaplar.add(row);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return oduncAlinanKitaplar;
    }
    @Override
       public void kayıtOl(String ad, String soyad, String eposta, String adres, String telefon, java.util.Date dogumTarihi, String kullanıcı_adi, String parola, int cinsiyet){
        try{
            String sorgu="insert into akademisyen(adi,soyadi,kullaniciAdi,sifre,email,telefon,,adres,dogum_tarihi,cinsiyet) values(?,?,?,?,?,?,?,?,?)";
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1,ad);
            preparedStatement.setString(2, soyad);
            preparedStatement.setString(3, kullanıcı_adi);
            preparedStatement.setString(4,parola);
            preparedStatement.setString(5, eposta);
            preparedStatement.setString(6, telefon);
            preparedStatement.setString(7,adres);
            preparedStatement.setDate(8, new java.sql.Date(dogumTarihi.getTime()));
            preparedStatement.setInt(9,cinsiyet);
            preparedStatement.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(PersonelIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public void satinAl(int akademisyen_id,int kitap_id, java.util.Date verilis_tarihi){
             try{
            String sorgu="insert into satin_alinan_kitaplar(akademisyen_id,kitap_id,satin_alinma_tarihi) values (?,?,?)";
            String sorgu2="update kitap set durum = 'Kitap Satın Alındı.' where kitap_id=?";
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setInt(1, akademisyen_id);
            preparedStatement.setInt(2, kitap_id);
            preparedStatement.setDate(3, new java.sql.Date(verilis_tarihi.getTime()));
            preparedStatement.executeUpdate();
          
            preparedStatement=con.prepareStatement(sorgu2);
            preparedStatement.setInt(1, kitap_id);
            preparedStatement.executeUpdate();
            
        }catch(SQLException ex){
            Logger.getLogger(PersonelIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
       }

    public void kapat() {
        try {
            if (con != null) {
                con.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
  
 
}
