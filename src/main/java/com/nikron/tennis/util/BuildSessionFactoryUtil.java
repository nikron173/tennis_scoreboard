package com.nikron.tennis.util;


import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

@UtilityClass
public class BuildSessionFactoryUtil {

    private static SessionFactory sf;

    public static SessionFactory createSessionFactory() {
        try {
            if (Objects.isNull(sf)) {
                Configuration configuration = new Configuration();
                configuration.configure();
                sf = configuration.buildSessionFactory();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sf;
    }

    public static Session getSession(){
        return sf.openSession();
    }

    public static void closeSessionFactory() {
        if (Objects.nonNull(sf) && sf.isOpen()) {
            sf.close();
        }
    }
}
