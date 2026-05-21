package floresta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlorestaTest {

    @BeforeEach
    public void setUp() {
        TipoArvoreFactory.limparCache();
    }

    // Compartilhamento de tipo

    @Test
    public void deveCompartilharMesmoTipoParaArvoresIguais() {
        Floresta floresta = new Floresta();
        floresta.plantarArvore(0, 0, "Pinheiro", "Verde", "Rugosa");
        floresta.plantarArvore(5, 3, "Pinheiro", "Verde", "Rugosa");
        System.out.println("[FLYWEIGHT] 2 arvores do mesmo tipo | tipos criados: " + TipoArvoreFactory.totalTiposCriados());
        TipoArvore t1 = floresta.getArvores().get(0).getTipo();
        TipoArvore t2 = floresta.getArvores().get(1).getTipo();
        System.out.println("  -> mesmo objeto TipoArvore: " + (t1 == t2) + " (esperado: true)");
        assertSame(t1, t2);
    }

    @Test
    public void deveCriarTiposDiferentesParaEspeciesDistintas() {
        Floresta floresta = new Floresta();
        floresta.plantarArvore(0, 0, "Pinheiro", "Verde", "Rugosa");
        floresta.plantarArvore(1, 1, "Carvalho", "Marrom", "Lisa");
        floresta.plantarArvore(2, 2, "Eucalipto", "Verde-claro", "Fina");
        int total = TipoArvoreFactory.totalTiposCriados();
        System.out.println("[FLYWEIGHT] 3 especies distintas | tipos no cache: " + total + " (esperado: 3)");
        assertEquals(3, total);
    }

    @Test
    public void deveTerMenosTiposQueTotalDeArvores() {
        Floresta floresta = new Floresta();
        for (int i = 0; i < 10; i++) floresta.plantarArvore(i, i, "Pinheiro", "Verde", "Rugosa");
        for (int i = 0; i < 10; i++) floresta.plantarArvore(i, i + 10, "Carvalho", "Marrom", "Lisa");
        System.out.println("[FLYWEIGHT] 20 arvores, 2 tipos | arvores: " + floresta.totalArvores()
            + " | tipos criados: " + TipoArvoreFactory.totalTiposCriados());
        assertEquals(20, floresta.totalArvores());
        assertEquals(2, TipoArvoreFactory.totalTiposCriados());
    }

    // Posicao unica por arvore

    @Test
    public void devePreservarPosicaoIndividualDeArvore() {
        Floresta floresta = new Floresta();
        floresta.plantarArvore(10, 20, "Pinheiro", "Verde", "Rugosa");
        floresta.plantarArvore(30, 40, "Pinheiro", "Verde", "Rugosa");
        System.out.println("[FLYWEIGHT] posicoes distintas com mesmo tipo");
        Arvore a1 = floresta.getArvores().get(0);
        Arvore a2 = floresta.getArvores().get(1);
        System.out.println("  -> arvore1: " + a1 + " | arvore2: " + a2);
        assertEquals(10, a1.getX());
        assertEquals(40, a2.getY());
        assertSame(a1.getTipo(), a2.getTipo());
    }

    // Atributos do tipo preservados

    @Test
    public void devePreservarAtributosDeTipoArvore() {
        Floresta floresta = new Floresta();
        floresta.plantarArvore(0, 0, "Carvalho", "Marrom", "Lisa");
        TipoArvore tipo = floresta.getArvores().get(0).getTipo();
        System.out.println("[FLYWEIGHT] atributos do TipoArvore | " + tipo);
        assertEquals("Carvalho", tipo.getNome());
        assertEquals("Marrom", tipo.getCor());
        assertEquals("Lisa", tipo.getTextura());
    }

    // Cache nao duplica apos multiplas chamadas

    @Test
    public void naoDeveCriarNovaTipoArvoreSeJaExisteNoCache() {
        TipoArvore primeira = TipoArvoreFactory.obter("Eucalipto", "Verde-claro", "Fina");
        TipoArvore segunda  = TipoArvoreFactory.obter("Eucalipto", "Verde-claro", "Fina");
        System.out.println("[FLYWEIGHT] factory retorna mesmo objeto do cache");
        System.out.println("  -> mesmo objeto: " + (primeira == segunda) + " (esperado: true)");
        System.out.println("  -> tipos no cache: " + TipoArvoreFactory.totalTiposCriados() + " (esperado: 1)");
        assertSame(primeira, segunda);
        assertEquals(1, TipoArvoreFactory.totalTiposCriados());
    }
}
