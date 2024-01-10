/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Patterns;

import Models.Kitap;
import java.util.List;

/**
 *
 * @author Gamze
 */
public interface KitapState {
    List<Kitap> kitapDurumuState(List<Kitap> kitaplar);
}
