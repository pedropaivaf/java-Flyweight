package floresta;

import java.util.ArrayList;
import java.util.List;

public class Floresta {
    private List<Arvore> arvores = new ArrayList<>();

    public void plantarArvore(int x, int y, String nome, String cor, String textura) {
        TipoArvore tipo = TipoArvoreFactory.obter(nome, cor, textura);
        arvores.add(new Arvore(x, y, tipo));
    }

    public List<Arvore> getArvores() { return arvores; }

    public int totalArvores() { return arvores.size(); }
}
