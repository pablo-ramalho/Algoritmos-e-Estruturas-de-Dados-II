public abstract class Usuario implements Comparable<Usuario>{

    private String cpf, nome, senha;
    private Contato contato;
    private Endereco endereco;

    public Usuario(String cpf, String nome, String senha, Contato contato, Endereco endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.contato = contato;
        this.endereco = endereco;

    }

    public String getCpf(){
        return cpf;

    }

    public String getNome(){
        return nome;

    }

    public String getSenha(){
        return senha;

    }

    public Contato getContato(){
        return contato;

    }

    public Endereco getEndereco(){
        return endereco;

    }

    //Critério de ordem: ordem alfabética dos nomes dos usuários
    @Override
    public int compareTo(Usuario user){
        if(this.nome.charAt(0) < user.nome.charAt(0))
            return -1;

        else
            if(this.nome.charAt(0) > user.nome.charAt(0))
                return 1;

        return 0;

    }

    @Override
    public String toString() {
        return "Usuario [cpf=" + cpf + ", nome=" + nome + ", senha=" + senha + ", contato=" + contato + ", endereco="
                + endereco + "]";
                
    }

}
