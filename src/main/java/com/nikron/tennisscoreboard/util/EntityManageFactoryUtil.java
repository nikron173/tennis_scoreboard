package com.nikron.tennisscoreboard.util;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManageFactoryUtil {
    private static EntityManagerFactory emf;

    private EntityManageFactoryUtil(){}

    public static EntityManagerFactory getEmf(){
        if (emf == null){
            try {
                Class.forName ("org.h2.Driver");
                emf = Persistence.createEntityManagerFactory("h2db");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return emf;
    }
}
