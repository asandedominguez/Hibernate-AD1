package services;

import config.HibernateConfig;
import model.Adestrador;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class AdestradorServices {

    // --- OPERACIÓNS CON OBXECTOS ---

    public void crearAdestrador(String nome, Date nacemento) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Adestrador adestrador = new Adestrador(nome, nacemento);
            session.save(adestrador);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao crear o adestrador: " + e.getMessage());
        }
    }

    public Adestrador lerAdestradorPorId(Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Adestrador.class, id);
        } catch (Exception e) {
            System.out.println("Erro ao ler o adestrador por ID: " + e.getMessage());
            return null;
        }
    }

    public void actualizarAdestrador(Adestrador adestrador) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(adestrador);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao actualizar o adestrador: " + e.getMessage());
        }
    }

    public void eliminarAdestrador(Adestrador adestrador) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(adestrador);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao eliminar o adestrador: " + e.getMessage());
        }
    }

    // --- OPERACIÓNS MEDIANTE QUERIES (HQL) ---

    public Adestrador lerAdestradorPorNome(String nome) {
        Adestrador adestrador = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Adestrador> lista = session.createQuery("from Adestrador where nome = :nome", Adestrador.class)
                    .setParameter("nome", nome)
                    .getResultList();

            if (!lista.isEmpty()) {
                adestrador = lista.get(0);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro ao buscar o adestrador por query: " + e.getMessage());
        }
        return adestrador;
    }

    public List<Adestrador> listarAdestradores() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Adestrador", Adestrador.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao listar os adestradores: " + e.getMessage());
            return null;
        }
    }

    public void eliminarTodosOsAdestradoresQuery() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            int count = session.createQuery("delete from Adestrador").executeUpdate();
            transaction.commit();
            System.out.println("Elimináronse " + count + " adestradores mediante Query.");
        } catch (Exception e) {
            System.out.println("Erro ao eliminar adestradores con query: " + e.getMessage());
        }
    }
}