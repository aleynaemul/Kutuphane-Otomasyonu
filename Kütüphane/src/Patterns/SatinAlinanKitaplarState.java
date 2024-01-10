/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Patterns;

import Models.Kitap;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gamze
 */
public class SatinAlinanKitaplarState implements KitapState{

    @Override
    public List<Kitap> kitapDurumuState(List<Kitap> kitaplar) {
        List<Kitap> list = new ArrayList<Kitap>();
        
        for(Kitap kitap : kitaplar){
            if(kitap.getDurum().equals("Kitap Satın Alındı.")){
                list.add(kitap);
            }
        }
        return list;
    }
    
}
