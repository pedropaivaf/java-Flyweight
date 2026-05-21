package floresta;

public class TipoArvore {
    private String nome;
    private String cor;
    private String textura;

    public TipoArvore(String nome, String cor, String textura) {
        this.nome = nome;
        this.cor = cor;
        this.textura = textura;
    }

    public String getNome() { return nome; }
    public String getCor() { return cor; }
    public String getTextura() { return textura; }

    @Override
    public String toString() {
        return "TipoArvore{nome='" + nome + "', cor='" + cor + "', textura='" + textura + "'}";
    }
}
