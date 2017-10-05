package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Knjiga;
import model.Oblast;

import java.io.IOException;
import java.util.List;

public class Zadatak4BrisanjeVrednosti {

    static Dao<Knjiga,Integer> knjigaDao;
    static Dao<Oblast,Integer> oblastDao;

    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {
            /*Uspostavimo konekciju sa bazom preko koje mozemo da posaljemo
               naredbe bazi
            */
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:knjigaOblast.db");

            knjigaDao = DaoManager.createDao(connectionSource, Knjiga.class);
            oblastDao = DaoManager.createDao(connectionSource, Oblast.class);

            List<Oblast> oblasti = oblastDao.queryForAll();
            System.out.println("Prikaz svih oblasti da proverimo da li je uspisana oblast koju brisemo.");
            for(Oblast o : oblasti)
                System.out.println(o);

            Oblast brisanje = oblastDao.queryForEq(Oblast.POLJE_NAZIV, "Aritmeticki operatori").get(0);
            oblastDao.delete(brisanje);

            oblasti = oblastDao.queryForAll();
            System.out.println("\nPrikaz svih oblasti da proverimo da li je obrisana data oblast.");
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
