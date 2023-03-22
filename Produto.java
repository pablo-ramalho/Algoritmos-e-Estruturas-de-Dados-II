public class Produto implements Comparable<Produto>{
    
    private Integer id, estoque;
    private String nome, categoria, marca;
    private Double preco;
    private Anunciante vendedor;

    public Produto(Integer id, Integer estoque, String nome, String categoria, String marca, Double preco) {
        this.id = id;
        this.estoque = estoque;
        this.nome = nome;
        this.categoria = categoria;
        this.marca = marca;
        this.preco = preco;

    }
    

    public Integer getId(){
        return id;

    }

    public Integer getEstoque(){
        return estoque;

    }

    public String getNome(){
        return nome;

    }

    public String getCategoria(){
        return categoria;

    }

    public String getMarca(){
        return marca;

    }


    public Double getPreco(){
        return preco;

    }

    public Anunciante getVendedor(){
        return vendedor;

    }

    //Critério de ordem: preço dos produtos
    @Override
    public int compareTo(Produto item){
        if(this.preco < item.preco)
            return -1;

        else
            if(this.preco > item.preco)
                return 1;

        return 0;

    }
    
    @Override
    public String toString() {
        return "Produto [id=" + id + ", estoque=" + estoque + ", nome=" + nome + ", categoria=" + categoria + ", marca="
                + marca + ", preco=" + preco + ", vendedor=" + vendedor + "]";
   
    }

}
