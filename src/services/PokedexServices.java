package services;

import config.HibernateConfig;
import model.Pokedex;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;

public class PokedexServices {

    public void crearPokemon(String nome, BigDecimal peso, String misc) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Pokedex pokemon = new Pokedex(nome, peso, misc);
            session.save(pokemon);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao crear o pokemon: " + e.getMessage());
        }
    }

    public Pokedex lerPokemonPorId(Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Pokedex.class, id);
        } catch (Exception e) {
            System.out.println("Erro ao ler o pokemon por ID: " + e.getMessage());
            return null;
        }
    }

    public void actualizarPokemon(Pokedex pokemon) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(pokemon);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao actualizar o pokemon: " + e.getMessage());
        }
    }

    // Eliminar pasando o obxecto
    public void eliminarPokemon(Pokedex pokemon) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(pokemon);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao eliminar o pokemon: " + e.getMessage());
        }
    }

    public Pokedex lerPokemonPorNome(String nome) {
        Pokedex pokemon = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Pokedex> lista = session.createQuery("from Pokedex where nome = :nome", Pokedex.class)
                    .setParameter("nome", nome)
                    .getResultList();

            if (!lista.isEmpty()) {
                pokemon = lista.get(0);
            } else {
                System.out.println("Non se atopou o pokemon co nome: " + nome);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro ao buscar o pokemon por query: " + e.getMessage());
        }
        return pokemon;
    }

    public List<Pokedex> listarPokemons() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Pokedex", Pokedex.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao listar os pokemons: " + e.getMessage());
            return null;
        }
    }

    public void eliminarTodosOsPokemonsQuery() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            int deletedCount = session.createQuery("delete from Pokedex").executeUpdate();
            transaction.commit();
            System.out.println("Elimináronse " + deletedCount + " pokemons da pokedex mediante Query.");
        } catch (Exception e) {
            System.out.println("Erro ao eliminar tódolos pokemons con query: " + e.getMessage());
        }
    }
}