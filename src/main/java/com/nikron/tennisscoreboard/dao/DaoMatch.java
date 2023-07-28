package com.nikron.tennisscoreboard.dao;

import com.nikron.tennisscoreboard.model.Match;
import com.nikron.tennisscoreboard.model.Player;
import com.nikron.tennisscoreboard.util.EntityManageFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class DaoMatch implements DaoInterface<Match>{
    @Override
    public Optional<Match> findById(Integer id) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = EntityManageFactoryUtil.getEmf().createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Optional<Match> match = Optional.ofNullable(em.createQuery("select m from Match m where m.id = :id", Match.class)
                    .setParameter("id", id).getSingleResult());
            tx.commit();
            return match;
        } catch (Exception e){
            e.printStackTrace();
            try {
                throw new RuntimeException("Kek");
            } finally {
                if (tx != null) tx.rollback();
            }
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public void save(Match entity) {
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
                throw new RuntimeException("Kek");
            } finally {
                if (tx != null) tx.rollback();
            }
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public void update(Match entity) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = EntityManageFactoryUtil.getEmf().createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Match match = em.find(Match.class, entity.getId());
            match.setPlayerOne(entity.getPlayerOne());
            match.setPlayerTwo(entity.getPlayerTwo());
            match.setPlayerWinner(entity.getPlayerWinner());
            tx.commit();
        } catch (Exception e){
            try {
                throw new RuntimeException("Kek");
            } finally {
                if (tx != null) tx.rollback();
            }
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public void delete(Match entity) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = EntityManageFactoryUtil.getEmf().createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.remove(entity);
            tx.commit();
        } catch (Exception e){
            try {
                throw new RuntimeException("Kek");
            } finally {
                if (tx != null) tx.rollback();
            }
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public List<Match> findAll() {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = EntityManageFactoryUtil.getEmf().createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            List<Match> matches = em.createQuery("SELECT m from Match m", Match.class).getResultList();
            tx.commit();
            return matches;
        } catch (Exception e){
            e.printStackTrace();
            try {
                throw new RuntimeException("Kek");
            } finally {
                if (tx != null) tx.rollback();
            }
        } finally {
            if (em != null) em.close();
        }
    }

    public List<Match> findMatchesByPlayer(Player player){
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = EntityManageFactoryUtil.getEmf().createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            List<Match> matches = em.createQuery("SELECT m from Match m WHERE m.playerOne = :player or m.playerTwo = :player", Match.class)
                    .setParameter("player", player)
                    .getResultList();
            tx.commit();
            return matches;
        } catch (Exception e){
            e.printStackTrace();
            try {
                throw new RuntimeException("Kek");
            } finally {
                if (tx != null) tx.rollback();
            }
        } finally {
            if (em != null) em.close();
        }
    }

    public List<Match> getMatchStartEndDB(int start, int total){
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = EntityManageFactoryUtil.getEmf().createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            List matches = em.createNativeQuery("SELECT match_id, player_one_id, player_two_id, player_winner_id" +
                            " from public.matches LIMIT :total OFFSET :start", Match.class)
                    .setParameter("start", start-1)
                    .setParameter("total", total)
                    .getResultList();
            tx.commit();
            return matches;
        } catch (Exception e){
            e.printStackTrace();
            try {
                throw new RuntimeException("Kek");
            } finally {
                if (tx != null) tx.rollback();
            }
        } finally {
            if (em != null) em.close();
        }
    }

    public Integer getCountPageMatches(int pageSize){
        return (int)Math.ceil(findAll().size()*1.0/pageSize);
    }

    public Integer getCountPageMatches(int pageSize, Player player){
        return (int)Math.ceil(findMatchesByPlayer(player).size()*1.0/pageSize);
    }

    public List<Match> getMatchStartEndDB(int start, int total, Player player){
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = EntityManageFactoryUtil.getEmf().createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            List matches = em.createNativeQuery("SELECT match_id, player_one_id, player_two_id, player_winner_id" +
                            " from public.matches WHERE player_one_id = :id or player_two_id = :id LIMIT :total OFFSET :start", Match.class)
                    .setParameter("id", player.getId())
                    .setParameter("start", start-1)
                    .setParameter("total", total)
                    .getResultList();
            tx.commit();
            return matches;
        } catch (Exception e){
            e.printStackTrace();
            try {
                throw new RuntimeException("Kek");
            } finally {
                if (tx != null) tx.rollback();
            }
        } finally {
            if (em != null) em.close();
        }
    }
}
