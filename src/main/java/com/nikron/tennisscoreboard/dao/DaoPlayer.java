package com.nikron.tennisscoreboard.dao;

import com.nikron.tennisscoreboard.model.Player;
import com.nikron.tennisscoreboard.util.EntityManageFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class DaoPlayer implements DaoInterface<Player> {
    @Override
    public Optional<Player> findById(Integer id) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = EntityManageFactoryUtil.getEmf().createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Optional<Player> player = Optional.of(em.find(Player.class, id));
            tx.commit();
            return player;
        } catch (Exception e){
            e.printStackTrace();
            try {
                throw new RuntimeException(e);
            } finally {
                if (tx != null) tx.rollback();
            }
        } finally {
            if (em != null) em.close();
        }
    }

    public Optional<Player> findByUsername(String username) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = EntityManageFactoryUtil.getEmf().createEntityManager();
            tx = em.getTransaction();
            Optional<Player> player = Optional.empty();
            tx.begin();
            Query hql = em.createQuery("select p from Player p where p.username = :username", Player.class);
            hql.setParameter("username", username);
            List<Player> players = hql.getResultList();
            if (players.size() == 0) return player;
            player = Optional.of(players.get(0));
            tx.commit();
            return player;
        } catch (Exception e){
            e.printStackTrace();
            try {
                throw new RuntimeException(e);
            } finally {
                if (tx != null) tx.rollback();
            }
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public void save(Player entity) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = EntityManageFactoryUtil.getEmf().createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(entity);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            try {
                throw new RuntimeException(e);
            } finally {
                if (tx != null) tx.rollback();
            }
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public void update(Player entity) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = EntityManageFactoryUtil.getEmf().createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Player player = em.find(Player.class, entity.getId());
            player.setUsername(entity.getUsername());
            tx.commit();
        } catch (Exception e){
            try {
                throw new RuntimeException(e);
            } finally {
                if (tx != null) tx.rollback();
            }
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public void delete(Player entity) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = EntityManageFactoryUtil.getEmf().createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Optional<Player> player = findById(entity.getId());
            if (player.isPresent()){
                em.remove(player);
            }
            tx.commit();
        } catch (Exception e){
            try {
                throw new RuntimeException(e);
            } finally {
                if (tx != null) tx.rollback();
            }
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public List<Player> findAll() {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = EntityManageFactoryUtil.getEmf().createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            List<Player> players = em.createQuery("SELECT p from Player p", Player.class).getResultList();
            tx.commit();
            return players;
        } catch (Exception e){
            e.printStackTrace();
            try {
                throw new RuntimeException(e);
            } finally {
                if (tx != null) tx.rollback();
            }
        } finally {
            if (em != null) em.close();
        }
    }
}
