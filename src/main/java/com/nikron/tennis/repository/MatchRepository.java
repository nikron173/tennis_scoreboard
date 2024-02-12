package com.nikron.tennis.repository;

import com.nikron.tennis.entity.Match;
import com.nikron.tennis.exception.DatabaseException;
import com.nikron.tennis.util.BuildSessionFactoryUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MatchRepository implements Repository<Long, Match> {

    private static final MatchRepository INSTANCE = new MatchRepository();

    private MatchRepository() {
    }

    public static MatchRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Match> findById(Long id) {
        try (Session session = BuildSessionFactoryUtil.getSession()) {
            session.beginTransaction();
            Optional<Match> match = Optional.of(session.get(Match.class, id));
            session.getTransaction().commit();
            return match;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public List<Match> findAll() {
        try (Session session = BuildSessionFactoryUtil.getSession()) {
            session.beginTransaction();
            List<Match> matches = session
                    .createQuery("FROM Match m", Match.class)
                    .getResultList();
            session.getTransaction().commit();
            return matches;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public List<Match> findMatchByPlayerNamePageSize(int pageSize, String playerName, int page) {
        try (Session session = BuildSessionFactoryUtil.getSession()) {
            session.beginTransaction();
            Query<Match> selectQuery = session
                    .createQuery("FROM Match m" +
                            " WHERE m.firstPlayer.name = :playerName" +
                            " OR m.secondPlayer.name = :playerName", Match.class)
                    .setParameter("playerName", playerName);
            selectQuery.setFirstResult((page - 1) * pageSize);
            selectQuery.setMaxResults(pageSize);
            List<Match> matches = selectQuery.getResultList();
            session.getTransaction().commit();
            return matches;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public Integer lastPageNumberPlayerName(int pageSize, String playerName) {
        try (Session session = BuildSessionFactoryUtil.getSession()) {
            session.beginTransaction();
            Long countRecords = session
                    .createQuery("SELECT count(m.id) FROM Match m" +
                            " WHERE m.firstPlayer.name = :playerName" +
                            " OR m.secondPlayer.name = :playerName", Long.class)
                    .setParameter("playerName", playerName)
                    .uniqueResult();
            Integer lastPageNumber = (int) (Math.ceil(countRecords * 1.0 / pageSize));
            session.getTransaction().commit();
            return lastPageNumber;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public List<Match> findMatchPageSize(int pageSize, int page) {
        try (Session session = BuildSessionFactoryUtil.getSession()) {
            session.beginTransaction();
            Query<Match> selectQuery = session.createQuery("FROM Match", Match.class);
            selectQuery.setFirstResult((page - 1) * pageSize);
            selectQuery.setMaxResults(pageSize);
            List<Match> matches = selectQuery.getResultList();
            session.getTransaction().commit();
            return matches;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public Integer lastPageNumber(int pageSize) {
        try (Session session = BuildSessionFactoryUtil.getSession()) {
            session.beginTransaction();
            Long countRecords = session
                    .createQuery("SELECT count(m.id) FROM Match m", Long.class)
                    .uniqueResult();
            Integer lastPageNumber = (int) (Math.ceil(countRecords * 1.0 / pageSize));
            session.getTransaction().commit();
            return lastPageNumber;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = BuildSessionFactoryUtil.getSession();
            transaction = session.beginTransaction();
            session.createQuery("DELETE Match m WHERE m.id = :id", Match.class)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
            throw new DatabaseException(e.getMessage(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            if (Objects.nonNull(session) && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Match update(Long id, Match match) {
        return null;
    }

    @Override
    public Match save(Match match) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = BuildSessionFactoryUtil.getSession();
            transaction = session.beginTransaction();
            session.persist(match);
            transaction.commit();
            return match;
        } catch (Exception e) {
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
            throw new DatabaseException(e.getMessage(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            if (Objects.nonNull(session) && session.isOpen()) {
                session.close();
            }
        }
    }
}
