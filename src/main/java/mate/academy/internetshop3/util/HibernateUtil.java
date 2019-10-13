package mate.academy.internetshop3.util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static Logger logger = Logger.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory = initSessionFactory();

    private static SessionFactory initSessionFactory() {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            return sessionFactory;
        } catch (Exception e) {
            logger.error("Cant create session factory");
            throw new RuntimeException(e);
        }
    }

    private HibernateUtil() {}

    public static SessionFactory sessionFactory() {
        return sessionFactory;
    }
}
