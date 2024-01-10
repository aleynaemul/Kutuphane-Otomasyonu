package Patterns;
import Models.Kitap;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gamze
 */
public class SayfaSayisinaGoreSiralama implements KitapSiralamaStrategy{

    @Override
    public void sort(ArrayList<Kitap> kitaplar) {
        kitaplar.sort((b1,b2)->Integer.compare(b1.getSayfa_sayisi(), b2.getSayfa_sayisi()));

    }
    
}
