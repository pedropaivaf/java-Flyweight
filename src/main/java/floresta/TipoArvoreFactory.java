package floresta;

import java.util.HashMap;
import java.util.Map;

public class TipoArvoreFactory {
    private static final Map<String, TipoArvore> cache = new HashMap<>();

    public static TipoArvore obter(String nome, String cor, String textura) {
        if (!cache.containsKey(nome)) {
            cache.put(nome, new TipoArvore(nome, cor, textura));
        }
        return cache.get(nome);
    }

    public static int totalTiposCriados() {
        return cache.size();
    }

    public static void limparCache() {
        cache.clear();
    }
}
