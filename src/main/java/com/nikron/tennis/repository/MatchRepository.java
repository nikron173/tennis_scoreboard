package com.nikron.tennis.repository;

import com.nikron.tennis.entity.Match;
import com.nikron.tennis.exception.DatabaseException;
import com.nikron.tennis.util.BuildSessionFactoryUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class MatchRepository implements Repository<UUID, Match> {
    @Override
    public Optional<Match> findById(UUID id) {
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

    @Override
    public void delete(UUID id) {
        Transaction transaction = null;
        try (Session session = BuildSessionFactoryUtil.getSession()){
            transaction = session.beginTransaction();
            session.createQuery("DELETE Match m WHERE m.id = :id", Match.class)
                            .setParameter("id", id)
                            .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (Objects.nonNull(transaction)){
                transaction.rollback();
            }
            throw new DatabaseException(e.getMessage(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Match update(UUID id, Match match) {
        return null;
    }

    @Override
    public Match save(Match match) {
        Transaction transaction = null;
        try (Session session = BuildSessionFactoryUtil.getSession()) {
            transaction = session.beginTransaction();
            session.persist(match);
            transaction.commit();
            return match;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
