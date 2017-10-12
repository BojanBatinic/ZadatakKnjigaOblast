package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Knjiga;

import java.io.IOException;
import java.util.*;


public class Biblioteka {

    static Dao<Knjiga,Integer> knjigaDao;

    //public static Boolean prisutna = true;

    public static void main(String[] args) {

        ConnectionSource connectionSource = null;
        try {

            connectionSource = new JdbcConnectionSource("jdbc:sqlite:knjigaOblast.db");

            knjigaDao = DaoManager.createDao(connectionSource, Knjiga.class);

        List<Knjiga> knjige = knjigaDao.queryForAll();

        KnjigaNit kn1 = new KnjigaNit("Milica", knjige.get(0));
        KnjigaNit kn3 = new KnjigaNit("Dragana", knjige.get(0));
        KnjigaNit kn5 = new KnjigaNit("Ljubica", knjige.get(0));
        KnjigaNit kn2 = new KnjigaNit("Milica", knjige.get(1));
        KnjigaNit kn4 = new KnjigaNit("Dragana", knjige.get(1));
        KnjigaNit kn6 = new KnjigaNit("Ljubica", knjige.get(1));

            kn1.start();
            kn2.start();
            kn3.start();
            kn4.start();
            kn5.start();
            kn6.start();

            kn1.join();
            kn2.join();
            kn3.join();
            kn4.join();
            kn5.join();
            kn6.join();

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        /* public synchronized boolean isPrisutna() {
        return prisutna;
    }

    public synchronized void setPrisutna(boolean prisutna) {
        this.prisutna = prisutna;
    }*/
            }System.out.println("\nBiblioteka se zatvara.");
        }


    }

}
