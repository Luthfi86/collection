import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBTTServlet extends HttpServlet {

    private SessionFactory sessionFactory;
    private Cache cache;

    public void init() throws ServletException {
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
                .buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        cache = CacheManager.getInstance().getCache("bttCache");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String NS = request.getParameter("NS");
        String Pemilik = request.getParameter("Pemilik");
        String Alamat = request.getParameter("Alamat");
        String Jp = request.getParameter("Jp");
        String Jp2 = request.getParameter("Jp2");
        String pabrik = request.getParameter("pabrik");
        String tahun_buat = request.getParameter("tahun_buat");
        String merk = request.getParameter("merk");
        String pabrik2 = request.getParameter("pabrik2");
        String tipe = request.getParameter("tipe");
        
        BTTEntity btt = new BTTEntity();
        btt.setNS(NS);
        btt.setPemilik(Pemilik);
        btt.setAlamat(Alamat);
        btt.setJp(Jp);
        btt.setJp2(Jp2);
        btt.setPabrik(pabrik);
        btt.setTahun_buat(tahun_buat);
        btt.setMerk(merk);
        btt.setPabrik2(pabrik2);
        btt.setTipe(tipe);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(btt);
            transaction.commit();
            cache.put(new Element(NS, btt));
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            response.sendRedirect("error.jsp
            ");
          } finally {
          session.close();
          }
          }

    public void destroy() {
        sessionFactory.close();
        CacheManager.getInstance().shutdown();
    }
}