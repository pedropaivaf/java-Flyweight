package floresta;

public class Arvore {
    private int x;
    private int y;
    private TipoArvore tipo;

    public Arvore(int x, int y, TipoArvore tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public TipoArvore getTipo() { return tipo; }

    @Override
    public String toString() {
        return "Arvore{x=" + x + ", y=" + y + ", tipo=" + tipo.getNome() + "}";
    }
}
