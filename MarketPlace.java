import java.util.List;
import java.util.ArrayList;

public class MarketPlace{

    private String nome;
    private List<Usuario> usuarios;
    private List<Produto> produtos;

    public MarketPlace(String nome){
        this.nome = nome;
        this.usuarios = new ArrayList<>();
        this.produtos = new ArrayList<>();
        
    }

    public Boolean incluirUsuario(Usuario usuario){
        
        if(usuario != null){
            this.usuarios.add(usuario);
            return true;

        }

        return false;

    }

    public Usuario excluirUsuario(Usuario usuario){
        Usuario excluido = null;
        Integer index = 0;

        for(Usuario user: usuarios){
            
            //Caso o usuário especificado como parâmetro esteja na coleção retorna o Usuario excluido
            if(user.equals(usuario)){
                excluido = this.usuarios.get(index);
                usuarios.remove(user);
                return excluido;

            }

            index++;

        }

        //Caso contrário retorna null
        return excluido;

    }

    public Boolean incluirProduto(Produto produto){

        if(produto != null){
            this.produtos.add(produto);
            return true;

        }

        return false;

    }

    public Produto excluirProduto(Produto produto){
        Produto excluido = null;
        Integer index = 0;

        for(Produto product: produtos){
            
            //Caso o usuário especificado como parâmetro esteja na coleção retorna o Usuario excluido
            if(product.equals(produto)){
                excluido = this.produtos.get(index);
                produtos.remove(product);
                return excluido;

            }

            index++;

        }

        //Caso contrário retorna null
        return excluido;

    }

    public Boolean login(String senha, Usuario usuario){
        Usuario tempUser = null;

        //Itera sobre a coleção que armazena usuários
        for(Usuario user : usuarios){

            if(user.equals(usuario))
                tempUser = user;
            
        }

        //Caso o usuário não seja encontrado na coleção
        if(tempUser == null)
            return false;

        //Caso o usuário seja encontrado na coleção e a senha digitada seja válida
        if(tempUser.getSenha().equals(senha)){
            System.out.println("Bem-vindo " + usuario.getNome() + "! :D");
            return true;

        //Caso contrário, se o usuário for encontrado na coleção e a senha digitada seja inválida
        }else{
            System.out.println("Acesso negado! ;(");
            return false;

        }

    }

    public String getNome(){
        return nome;

    }

    @Override
    public String toString() {
        return "MarketPlace [nome=" + nome + ", usuarios=" + usuarios + ", produtos=" + produtos + "]";
    
    }

}