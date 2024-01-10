package Patterns;

public class KullaniciIDGetirme {
    private static KullaniciIDGetirme kullanici;
    
    private int id;
    private String unvan;
    
    private KullaniciIDGetirme(){
    }
    
    public static KullaniciIDGetirme getKullanici(){
        if(kullanici == null){
            kullanici = new KullaniciIDGetirme();
        }
        return kullanici;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }
    
    
    
}