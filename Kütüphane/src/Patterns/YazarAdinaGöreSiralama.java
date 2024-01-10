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
public class YazarAdinaGöreSiralama implements KitapSiralamaStrategy{

    @Override
    public void sort(ArrayList<Kitap> kitaplar) {
        kitaplar.sort((b1,b2)->b1.getYazar().compareTo(b2.getYazar()));
    }
    
}
