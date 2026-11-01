import model.Pokedex;
import services.PokedexServices;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        PokedexServices pokedexServices = new PokedexServices();

        pokedexServices.crearPokemon("Bulbasaur", new BigDecimal("6.9"), "Planta/Veneno");
        pokedexServices.crearPokemon("Charmander", new BigDecimal("8.5"), "Fogo");
        pokedexServices.crearPokemon("Squirtle", new BigDecimal("9.0"), "Aga");
        pokedexServices.crearPokemon("Pikachu", new BigDecimal("6.0"), "Eléctrico");
        pokedexServices.crearPokemon("Jigglypuff", new BigDecimal("5.5"), "Normal/Fada");
        pokedexServices.crearPokemon("Meowth", new BigDecimal("4.2"), "Normal");
        pokedexServices.crearPokemon("Psyduck", new BigDecimal("19.6"), "Aga");
        pokedexServices.crearPokemon("Snorlax", new BigDecimal("460.0"), "Normal");
        pokedexServices.crearPokemon("Gengar", new BigDecimal("40.5"), "Pantasma/Veneno");
        pokedexServices.crearPokemon("Mewtwo", new BigDecimal("122.0"), "Psíquico");

        System.out.println("Lista");
        List<Pokedex> pokemons = pokedexServices.listarPokemons();
        for (Pokedex p : pokemons) {
            System.out.println(p);
        }

        System.out.println("Modificador");

        Pokedex pikachu = pokedexServices.lerPokemonPorNome("Pikachu");
        if (pikachu != null) {
            pikachu.setPeso(new BigDecimal("7.5"));
            pikachu.setMisc("Eléctrico - Raichu ");
            pokedexServices.actualizarPokemon(pikachu);
            System.out.println("Pikachu actualizado");
        }

        Pokedex snorlax = pokedexServices.lerPokemonPorId(8L);
        if (snorlax != null) {
            snorlax.setMisc("Normal - Después del cambio");
            snorlax.setPeso(new BigDecimal("400.0"));
            pokedexServices.actualizarPokemon(snorlax);
            System.out.println("Snorlax actualizado");
        }

        System.out.println("Listado después de actualización");
        pokemons = pokedexServices.listarPokemons();
        for (Pokedex p : pokemons) {
            System.out.println(p);
        }

        System.out.println("Eliminar");
        pokedexServices.eliminarTodosOsPokemonsQuery();

        System.out.println("Listado final");
        pokemons = pokedexServices.listarPokemons();
        for (Pokedex p : pokemons) {
            System.out.println(p);
        }
    }
}