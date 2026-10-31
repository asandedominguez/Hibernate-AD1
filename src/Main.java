import model.Pokedex;
import services.PokedexServices;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PokedexServices pokedexServices = new PokedexServices();

        System.out.println("=== 1. INSERINDO 10 POKEMONS ===");
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

        System.out.println("\n=== 2. LISTADO INICIAL DA POKEDEX ===");
        listarPokedex(pokedexServices);

        System.out.println("\n=== 3. MODIFICANDO DUAS ENTRADAS ===");

        Pokedex pikachu = pokedexServices.lerPokemonPorNome("Pikachu");
        if (pikachu != null) {
            pikachu.setPeso(new BigDecimal("7.5"));
            pikachu.setMisc("Eléctrico - Raichu form pronto");
            pokedexServices.actualizarPokemon(pikachu);
            System.out.println("Pikachu actualizado correctamente.");
        }

        Pokedex snorlax = pokedexServices.lerPokemonPorId(8L); // ID 8 asumindo secuencia SERIAL
        if (snorlax != null) {
            snorlax.setMisc("Normal - Post-dieta, perdeu peso");
            snorlax.setPeso(new BigDecimal("400.0"));
            pokedexServices.actualizarPokemon(snorlax);
            System.out.println("Snorlax actualizado correctamente.");
        }

        System.out.println("\n=== 4. LISTADO DA POKEDEX DESPOIS DAS MODIFICACIÓNS ===");
        listarPokedex(pokedexServices);

        // 5. Eliminar tódo-los pokemons na pokedex
        System.out.println("\n=== 5. ELIMINANDO TÓDO-LOS POKEMONS ===");
        pokedexServices.eliminarTodosOsPokemonsQuery();

        // Comprobación final
        System.out.println("\n=== LISTADO FINAL (DEBE ESTAR VAZO) ===");
        listarPokedex(pokedexServices);
    }

    private static void listarPokedex(PokedexServices pokedexServices) {
        List<Pokedex> pokemons = pokedexServices.listarPokemons();
        if (pokemons == null || pokemons.isEmpty()) {
            System.out.println("A Pokedex está baleira.");
        } else {
            for (Pokedex p : pokemons) {
                System.out.println(p);
            }
        }
    }
}
