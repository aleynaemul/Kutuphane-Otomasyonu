package Patterns;

import java.sql.Connection;

public class BridgeIliskiselVeritabaniBaglayicisi implements BridgeVeritabaniBaglayici{

    protected BridgeVeritabaniPlatformu platform;

    public BridgeIliskiselVeritabaniBaglayicisi(BridgeVeritabaniPlatformu platform) {
        this.platform = platform;
    }
    
    
    @Override
    public Connection baglan() {
        platform.baglantiyiYapilandir();
        return platform.baglantiyiGetir();
    }
    
    
}


