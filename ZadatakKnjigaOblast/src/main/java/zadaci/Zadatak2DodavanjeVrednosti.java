package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Knjiga;
import model.Oblast;

import java.util.*;

import java.io.IOException;

public class Zadatak2DodavanjeVrednosti {

    static Dao<Knjiga,Integer> knjigaDao;
    static Dao<Oblast,Integer> oblastDao;


    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {

            connectionSource = new JdbcConnectionSource("jdbc:sqlite:knjigaOblast.db");

            knjigaDao= DaoManager.createDao(connectionSource, Knjiga.class);
            oblastDao = DaoManager.createDao(connectionSource, Oblast.class);

            TableUtils.clearTable(connectionSource,Oblast.class);
            TableUtils.clearTable(connectionSource,Knjiga.class);

            Knjiga k1=new Knjiga("Java programiranje", 650, new Date());
            knjigaDao.create(k1);

            Knjiga k2=new Knjiga("Android programiranje", 500, new Date());
            knjigaDao.create(k2);

            Oblast o1=new Oblast("Uvod", 2);
            o1.setKnjiga(k1);
            oblastDao.create(o1);

            Oblast o2=new Oblast("Naredbe",  10);
            o2.setKnjiga(k1);
            oblastDao.create(o2);

            Oblast o3=new Oblast("Aritmeticki operatori", 20);
            o3.setKnjiga(k1);
            oblastDao.create(o3);

            Oblast o4=new Oblast("Android operativni sistem", 2);
            o4.setKnjiga(k2);
            oblastDao.create(o4);

            Oblast o5=new Oblast("Activity klasa", 30);
            o5.setKnjiga(k2);
            oblastDao.create(o5);

            List<Knjiga> knjige = knjigaDao.queryForAll();
            System.out.println("Prikaz svih knjiga da proverimo da li su upisane sve knjige.");
            for(Knjiga k : knjige)
                System.out.println(k);

            List<Oblast> oblasti = oblastDao.queryForAll();
            System.out.println("\nPrikaz svih oblasti da proverimo da li su sve oblasti upisane.");
            for(Oblast o : oblasti)
                System.out.println(o);

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
