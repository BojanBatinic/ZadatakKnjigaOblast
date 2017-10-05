package zadaci;

import com.j256.ormlite.dao.Dao;
import model.Knjiga;

import java.util.Random;

import static zadaci.Biblioteka.prisutna;

public class KnjigaNit extends Thread{

    static Dao<Knjiga,Integer> knjigaDao;

    private String imeClana;
    public Knjiga knjiga;


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
        try {
            this.sleep(2000);
            } catch (InterruptedException e) {
            e.printStackTrace();
        }

        do {
            synchronized (prisutna) {
                if (prisutna) {
                    try {

                        sleep(random.nextInt(5000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(" Clan " + imeClana + " je iznajmio knjigu " + knjiga.getNaslov() + ".");
                    prisutna = false;

                }

                try {
                    sleep(random.nextInt(2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(" Clan " + imeClana + " ceka da knjiga " + knjiga.getNaslov() + " bude vraćena.");


                synchronized (prisutna) {
                    prisutna = true;
                    System.out.println(" Clan " + imeClana + " je vratio knjigu " + knjiga.getNaslov() + ".");
                }
            }

        } while (!prisutna);
     }
}
