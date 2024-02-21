package com.nikron.tennis.repository;

import com.nikron.tennis.entity.Player;
import com.nikron.tennis.exception.DatabaseException;
import com.nikron.tennis.util.BuildSessionFactoryUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PlayerRepository implements Repository<Long, Player> {

    private final static PlayerRepository INSTANCE = new PlayerRepository();

    private PlayerRepository() {
    }

    public static PlayerRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Player> findById(Long id) {
        try (Session session = BuildSessionFactoryUtil.getSession()) {
            session.beginTransaction();
            Optional<Player> player = Optional.of(session.get(Player.class, id));
            session.getTransaction().commit();
            return player;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<Player> findByName(String name) {
        try (Session session = BuildSessionFactoryUtil.getSession()) {
            session.beginTransaction();
            Optional<Player> player = session
                    .createQuery("FROM Player p WHERE p.name = :name", Player.class)
                    .setParameter("name", name)
                    .uniqueResultOptional();
            session.getTransaction().commit();
            return player;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Player> findAll() {
        try (Session session = BuildSessionFactoryUtil.getSession()) {
            session.beginTransaction();
            List<Player> players = session
                    .createQuery("FROM Player p", Player.class)
                    .getResultList();
            session.getTransaction().commit();
            return players;
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
            session.createQuery("DELETE Player p WHERE p.id = :id", Player.class)
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
    public Player update(Long id, Player player) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = BuildSessionFactoryUtil.getSession();
            transaction = session.beginTransaction();
            Player playerDb = session.find(Player.class, id);
//            if (findByName(player.getName()).isPresent()) {
//                throw new DuplicateResourceException("Имя \"%s\" уже занято".formatted(player.getName()),
//                        HttpServletResponse.SC_CONFLICT);
//            }
            playerDb.setName(player.getName());
            transaction.commit();
            return playerDb;
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
    public Player save(Player player) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = BuildSessionFactoryUtil.getSession();
            transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
            return player;
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
