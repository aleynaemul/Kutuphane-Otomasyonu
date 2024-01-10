package service;
import Patterns.BridgeIliskiselVeritabaniBaglayicisi;
import Patterns.BridgeMySQLBaglantisi;
import Patterns.BridgeVeritabaniBaglayici;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gamze
 */
public abstract class Kitapİslemleri {
    private Connection con = null;
    private Statement statement = null ;
    private PreparedStatement preparedStatement = null;

    public Kitapİslemleri() {
        BridgeVeritabaniBaglayici baglayici = new BridgeIliskiselVeritabaniBaglayicisi(new BridgeMySQLBaglantisi());
        con = baglayici.baglan();
    }
    
    public abstract int iadeEtme(int ogrenci_id,int akademisyen_id,int kitapId,int odunc_id,java.util.Date odunc_alinma_tarihi);
    public abstract void updateKitapDurumu(int kitapId);
    public abstract void deleteOduncAlmaByKitapId(int kitapId);
    public abstract boolean iadeTarihiGecmisMi(int uye_id, Date bugun) throws SQLException;
    public abstract ArrayList<Object[]> oduncAlinanKitaplariGetir();
    public abstract void kayıtOl(String ad, String soyad, String eposta, String adres, String telefon, java.util.Date dogumTarihi, String kullanıcı_adi, String parola, int cinsiyet);
    
   

     
  
}