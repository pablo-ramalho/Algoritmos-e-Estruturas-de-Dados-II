import java.util.ArrayList;
import java.util.List;

public class Anunciante extends Usuario{

    private String cnpj;
    private List<Produto> listaDeProdutos;

    public Anunciante(String cpf, String nome, String senha, Contato contato, Endereco endereco, String cnpj,
            List<Produto> listaDeProdutos){
        super(cpf, nome, senha, contato, endereco);
        this.cnpj = cnpj;
        this.listaDeProdutos = new ArrayList<>();

    }

    public String getCnpj(){
        return cnpj;

    }

    @Override
    public String toString() {
        return "Anunciante [cnpj=" + cnpj + ", listaDeProdutos=" + listaDeProdutos + super.toString() + "]";
    
    }
    
}
