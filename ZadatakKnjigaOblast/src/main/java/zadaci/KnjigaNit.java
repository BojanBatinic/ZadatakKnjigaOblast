package zadaci;

import com.j256.ormlite.dao.Dao;
import model.Knjiga;

import java.util.Random;

import static model.Knjiga.prisutna;

//import static zadaci.Biblioteka.prisutna;

public class KnjigaNit extends Thread{

    static Dao<Knjiga,Integer> knjigaDao;

    private String imeClana;
    public Knjiga knjiga;
    //public static Boolean prisutna = true;


    public KnjigaNit() {
    }

    public KnjigaNit(String imeClana, Knjiga knjiga) {
        this.imeClana = imeClana;
        this.knjiga = knjiga;
    }

    public String getImeClana() {
        return imeClana;
    }

    public void setImeClana(String imeClana) {
        this.imeClana = imeClana;
    }

    public void run () {
        Random random = new Random();

        System.out.println(" Clan " + imeClana + " trazi knjigu " + knjiga.getNaslov() + ".");

        do {
            synchronized (prisutna) {
                if (prisutna) {
                    prisutna = false;
                System.out.println(" Clan " + imeClana + " ceka da knjiga " + knjiga.getNaslov() + " bude vraćena.");
                 }
                System.out.println(" Clan " + imeClana + " je iznajmio knjigu " + knjiga.getNaslov() + ".");

                try {
                    sleep(random.nextInt(5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (prisutna) {
                    prisutna = true;

                    System.out.println(" Clan " + imeClana + " je vratio knjigu " + knjiga.getNaslov() + ".");
                }
            }

        } while (!prisutna);
     }
}
