package Kruskal;

public class Node{

    private Integer identificador;
    private Integer peso;
    private Node next;

    public Node(Integer identificador, Integer peso){
        this.setIdentificador(identificador);
        this.setPeso(peso);

    }

    public Integer getIdentificador(){
        return this.identificador;

    }

    public void setIdentificador(Integer identificador){
        this.identificador = identificador;
    
    }

    public Integer getPeso(){
        return this.peso;
    
    }

    public void setPeso(Integer peso){
        this.peso = peso;

    }

    public Node getNext(){
        return this.next;

    }

    public void setNext(Node next){
        this.next = next;

    }

}
