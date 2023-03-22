import java.util.ArrayList;
import java.util.List;

public class Comprador extends Usuario{
    
    private List<Produto> carrinho;
    private List<Produto> favoritos;

    public Comprador(String cpf, String nome, String senha, Contato contato, Endereco endereco, List<Produto> carrinho,
            List<Produto> favoritos){
        super(cpf, nome, senha, contato, endereco);
        this.carrinho = carrinho;
        this.favoritos = new ArrayList<>();

    }

    @Override
    public String toString(){
        return "Comprador [carrinho=" + carrinho + ", favoritos=" + favoritos + super.toString() + " ]";

    }

}
