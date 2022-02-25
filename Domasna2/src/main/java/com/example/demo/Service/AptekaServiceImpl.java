package com.example.demo.Service;


import com.example.demo.Repository.JPA.SiteApteki;
import com.example.demo.model.Apteka;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AptekaServiceImpl  implements AptekaService{
    private final SiteApteki siteApteki;

    public AptekaServiceImpl( SiteApteki siteApteki) {
        this.siteApteki = siteApteki;

    }
    //funkcijata gi lista site apteki
    @Override
    public List<Apteka> findallPharmacy() {
     return  siteApteki.findAll();
    }

    //gi prebaruva aptekite spored prebaraniot tekst
    @Override
    public List<Apteka> findbyC(String text) {
       return siteApteki.find(text);
    }


     //funkcijata convertCyrilic sluzi za konvertiranje od latinica na kirlica
     public  String convertCyrilic(String message){
        String [] niza=message.split("");
        String izlez="";

        char[] abcCyr =   {'а','б','в','г','д','ѓ','е', 'ж','з','ѕ','и','ј','к','л','љ','м','н','њ','о','п','р','с','т', 'ќ','у', 'ф','х','ц','ч','џ','ш', 'А','Б','В','Г','Д','Ѓ','Е', 'Ж','З','Ѕ','И','Ј','К','Л','Љ','М','Н','Њ','О','П','Р','С','Т', 'Ќ', 'У','Ф', 'Х','Ц','Ч','Џ','Ш','1','2','3','4','5','6','7','8','9'};
        String[] abcLat = {"a","b","v","g","d","gj","e","zh","z","y","i","j","k","l","lj","m","n","nj","o","p","r","s","t","kj","u","f","h", "c","ch", "dz","sh","A","B","V","G","D","Gj","E","Zh","Z","Dz","I","J","K","L","LJ","M","N","NJ","O","P","R","S","T","KJ","U","F","H", "C","CH", "DZ","SH","1","2","3","4","5","6","7","8","9","/","-"};
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            for (int x = 0; x < abcCyr.length; x++ ) {
                if (niza[i].equals(abcLat[x])) {
                    izlez+=abcCyr[x];
                }
            }
        }
        if(izlez.length()==0){
            return message;
        }else{
        return izlez;}
    }

    //funkcijata sluzi za zolemuvanje na prvata bukva od prebaraniot tekst
    @Override
    public String ToUpperCase(String s) {
        String [] niza=s.split("");
        niza[0]= niza[0].toUpperCase();
        String cc="";
        for(int i=0;i<niza.length;i++)
        {
            cc+=niza[i];
        }
        return cc;
    }

//gi pronaogja aptekite spored id
    @Override
    public Apteka findbyId(Long id) {
        Apteka a= new Apteka();
       return siteApteki.findById(id).orElse(a);

    }
}
