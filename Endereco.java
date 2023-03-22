public class Endereco{
    
    private String logradouro, numero, complemento, bairro, cep, cidade, uf;

    public Endereco(String logradouro, String numero, String complemento, String bairro, String cep, String cidade,
            String uf){
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;

    }

    public String getLogradouro(){
        return logradouro;

    }

    public String getNumero(){
        return numero;

    }

    public String getComplemento(){
        return complemento;

    }

    public String getBairro(){
        return bairro;

    }

    public String getCep(){
        return cep;

    }

    public String getCidade(){
        return cidade;

    }

    public String getUf(){
        return uf;

    }

    @Override
    public String toString(){
        return "Endereco [logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento
                + ", bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade + ", uf=" + uf + "]";
    
    }

}
