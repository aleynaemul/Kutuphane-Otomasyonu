package Patterns;

import Models.Akademisyen;
import Models.IKullanici;
import Models.Ogrenci;
import Models.Personel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gamze
 */
public class KullaniciFactory {
    public IKullanici kullaniciOlustur(String kullaniciTuru){
        
        if (kullaniciTuru.equalsIgnoreCase("Personel")) {
            return new Personel();
        } else if (kullaniciTuru.equalsIgnoreCase("Öğrenci")) {
            return new Ogrenci();
        }
        else {
            return new Akademisyen();
        }
    }
}
