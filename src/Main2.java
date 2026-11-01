import model.Adestrador;
import model.Pokedex;
import services.AdestradorServices;
import services.PokedexServices;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {

        PokedexServices pokedexServices = new PokedexServices();
        AdestradorServices adestradorServices = new AdestradorServices();

        // Crear Pokémons
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

        System.out.println("Insertando entrenadores");
        adestradorServices.crearAdestrador("Ash Ketchum", new Date());
        adestradorServices.crearAdestrador("Misty", new Date());

        System.out.println("Listar");
        List<Pokedex> pokedexList = pokedexServices.listarPokemons();
        for (Pokedex pokemon : pokedexList) {
            System.out.println(pokemon);
        }

        System.out.println("Listar entrenadores");
        List<Adestrador> adestradorList = adestradorServices.listarAdestradores();
        for (Adestrador adestrador : adestradorList) {
            System.out.println(adestrador);
        }

        System.out.println("Modificar entrenadores");
        Adestrador ash = adestradorServices.lerAdestradorPorNome("Ash Ketchum");
        if (ash != null) {
            ash.setNome("Ash Ketchum");
            adestradorServices.actualizarAdestrador(ash);
        }

        Adestrador misty = adestradorServices.lerAdestradorPorNome("Misty");
        if (misty != null) {
            misty.setNome("Misty");
            adestradorServices.actualizarAdestrador(misty);
        }

        System.out.println("Modificar");
        Pokedex pikachu = pokedexServices.lerPokemonPorNome("Pikachu");
        if (pikachu != null) {
            pikachu.setPeso(new BigDecimal("7.0"));
            pikachu.setMisc("Eléctrico - Modificado");
            pokedexServices.actualizarPokemon(pikachu);
        }

        Pokedex snorlax = pokedexServices.lerPokemonPorNome("Snorlax");
        if (snorlax != null) {
            snorlax.setMisc("Normal - Mega");
            pokedexServices.actualizarPokemon(snorlax);
        }

        System.out.println("Listar modificado");
        pokedexList = pokedexServices.listarPokemons();
        for (Pokedex pokemon : pokedexList) {
            System.out.println(pokemon);
        }

        System.out.println("Listar entrenadores modificados");
        adestradorList = adestradorServices.listarAdestradores();
        for (Adestrador adestrador : adestradorList) {
            System.out.println(adestrador);
        }

        System.out.println("Eliminar");
        pokedexServices.eliminarTodosOsPokemonsQuery();
        adestradorServices.eliminarTodosOsAdestradoresQuery();

        System.out.println("Listado final");
        pokedexList = pokedexServices.listarPokemons();
        for (Pokedex pokemon : pokedexList) {
            System.out.println(pokemon);
        }

        adestradorList = adestradorServices.listarAdestradores();
        for (Adestrador adestrador : adestradorList) {
            System.out.println(adestrador);
        }
    }
}