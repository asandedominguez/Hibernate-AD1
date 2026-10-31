import model.Adestrador;
import model.Pokedex;
import services.AdestradorServices;
import services.PokedexServices;
import utils.XmlExportUtil;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        PokedexServices pokedexServices = new PokedexServices();
        AdestradorServices adestradorServices = new AdestradorServices();

        // 1. Inserir 10 pokemons na táboa pokedex
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

        // 2. Inserir 2 adestradores na táboa adestrador
        System.out.println("\n=== 2. INSERINDO 2 ADESTRADORES ===");
        adestradorServices.crearAdestrador("Ash Ketchum", crearData(1987, Calendar.MAY, 22));
        adestradorServices.crearAdestrador("Misty", crearData(1990, Calendar.DECEMBER, 11));

        // 3. Listar pokemons na pokedex
        System.out.println("\n=== 3. LISTAR POKEMONS (INICIAL) ===");
        listarPokemons(pokedexServices);

        // 4. Listar adestradores
        System.out.println("\n=== 4. LISTAR ADESTRADORES (INICIAL) ===");
        listarAdestradores(adestradorServices);

        // 5. Modificar dous adestradores
        System.out.println("\n=== 5. MODIFICAR DOUS ADESTRADORES ===");
        Adestrador ash = adestradorServices.lerAdestradorPorNome("Ash Ketchum");
        if (ash != null) {
            ash.setNome("Ash Ketchum (Campión)");
            adestradorServices.actualizarAdestrador(ash);
        }

        Adestrador misty = adestradorServices.lerAdestradorPorNome("Misty");
        if (misty != null) {
            misty.setNome("Misty (Líder de Ximnasio)");
            adestradorServices.actualizarAdestrador(misty);
        }

        // 6. Modificar dúas entradas na pokedex
        System.out.println("\n=== 6. MODIFICAR DUAS ENTRADAS NA POKEDEX ===");
        Pokedex pikachu = pokedexServices.lerPokemonPorNome("Pikachu");
        if (pikachu != null) {
            pikachu.setPeso(new BigDecimal("7.0"));
            pikachu.setMisc("Eléctrico - Modificado");
            pokedexServices.actualizarPokemon(pikachu);
        }

        Pokedex snorlax = pokedexServices.lerPokemonPorNome("Snorlax");
        if (snorlax != null) {
            snorlax.setMisc("Normal - Mega Snorlax");
            pokedexServices.actualizarPokemon(snorlax);
        }

        // 7. Listar pokemons na pokedex
        System.out.println("\n=== 7. LISTAR POKEMONS (MODIFICADOS) ===");
        List<Pokedex> listaPokemons = pokedexServices.listarPokemons();
        mostrarLista(listaPokemons);

        // 8. Listar adestradores
        System.out.println("\n=== 8. LISTAR ADESTRADORES (MODIFICADOS) ===");
        List<Adestrador> listaAdestradores = adestradorServices.listarAdestradores();
        mostrarLista(listaAdestradores);

        // 9 & 10. EXPORTAR A XML (toXML pokemons e adestradores)
        System.out.println("\n=== EXPORTANDO A XML ===");
        XmlExportUtil.pokemonsToXML(listaPokemons, "pokemons.xml");
        XmlExportUtil.adestradoresToXML(listaAdestradores, "adestradores.xml");

        // 11. Eliminar tódo-los datos das táboas
        System.out.println("\n=== ELIMINANDO TÓDO-LOS DATOS DAS TÁBOAS ===");
        pokedexServices.eliminarTodosOsPokemonsQuery();
        adestradorServices.eliminarTodosOsAdestradoresQuery();

        // Comprobación final
        System.out.println("\n=== COMPROBACIÓN FINAL (TÁBOAS VAZAS) ===");
        listarPokemons(pokedexServices);
        listarAdestradores(adestradorServices);
    }

    private static void listarPokemons(PokedexServices pokedexServices) {
        mostrarLista(pokedexServices.listarPokemons());
    }

    private static void listarAdestradores(AdestradorServices adestradorServices) {
        mostrarLista(adestradorServices.listarAdestradores());
    }

    private static <T> void mostrarLista(List<T> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("A táboa está baleira.");
        } else {
            for (T elem : lista) {
                System.out.println(elem);
            }
        }
    }

    private static Date crearData(int ano, int mes, int dia) {
        Calendar cal = Calendar.getInstance();
        cal.set(ano, mes, dia);
        return cal.getTime();
    }
}