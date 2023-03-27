package Kruskal;

/**
 * Esta classe representa uma lista simplesmente encadeada.<br></br>
 * Atributos:<br></br>
 * head - o primeiro nó da lista<br></br>
 * tail - o último nó da lista<br></br>
 * rotulo - o número (maior ou igual a 0) que identifica a lista<br></br>
 * numeroDeElementos - quantidade de nós que a lista tem<br></br>
 */
public class Lista implements Comparable<Lista>{

    private Node head;
    private Node tail;
    private Integer rotulo;
    private Integer numeroDeElementos;

    /**
     * Constrói um objeto que representa uma lista simplesmente encadeada.
     * @param rotulo o número (maior ou igual a 0) que identifica a lista
     * @param numeroDeElementos quantidade de nós que a lista tem
     */
    public Lista(Integer rotulo, Integer numeroDeElementos){
        this.setHead(null);
        this.setTail(null);
        this.setRotulo(rotulo);
        this.setNumeroDeElementos(numeroDeElementos);

    }

    /**
     * Adiciona um nó a head (se a lista não contiver elementos
     * o head (assim como tail) será o próprio elemento inserido).
     * @param vertice o número que identifica o nó a ser inserido (maior ou igual a 0)
     * @param peso o peso/custo para ir até este nó
     * @param novo o objeto nó a ser inserido
     */
    public void adicionarNoInicio(Integer vertice, Integer peso, Node novo){

        //Caso não haja elementos na lista
        if(this.getHead() == null && this.getTail() == null){
            this.setHead(novo);
            this.setTail(novo);
            this.getHead().setIdentificador(vertice);
            this.getHead().setPeso(peso);

        }else{
            novo.setNext(this.getHead());
            this.setHead(novo);
            this.getHead().setIdentificador(vertice);
            this.getHead().setPeso(peso);

        }

    }

    /**
     * Adiciona um nó a tail (se a lista não contiver elementos
     * o tail (assim como head) será o próprio elemento inserido).
     * @param vertice o número que identifica o nó a ser inserido (maior ou igual a 0)
     * @param peso o peso/custo para ir até este nó
     * @param novo o objeto nó a ser inserido
     */
    public void adicionarNoFinal(Integer vertice, Integer peso, Node novo){

        //Caso não haja elementos na lista
        if(this.getHead() == null && this.getTail() == null){
            this.adicionarNoInicio(vertice, peso, novo);

        }else{
            this.getTail().setNext(novo);
            this.setTail(novo);
            this.getTail().setIdentificador(vertice);
            this.getTail().setPeso(peso);

        }

    }

    /**
     * Remove o primeiro nó (head) da lista.
     * Se a lista não contiver elementos será
     * retornado null.
     * @return o objeto nó que foi removido da lista
     */
    public Node removerDoInicio(){
        Node removido;

        //Caso não haja elementos na lista
        if(this.getHead() == null && this.getTail() == null){
            removido = null;

        }else   //Caso haja um único elemento na lista
            if(this.getHead() == this.getTail()){
                removido = this.getHead();
                this.setHead(null);
                this.setTail(null);
                this.setNumeroDeElementos(this.getNumeroDeElementos() - 1);

        }else{
            removido = this.getHead();
            this.setHead(this.getHead().getNext());
            this.setNumeroDeElementos(this.getNumeroDeElementos() - 1);

        }

        return removido;

    }


    public Node remover(Integer posicao){
        Node removido = this.getHead();
        Node atual, anterior;

        //Caso a posição digitada seja inválida ou ultrapasse os limites do tamanho da lista
        if(posicao < 0 || posicao >= this.getNumeroDeElementos()){
            System.out.println("Posição inválida!");
            return null;

        }

        //Caso não haja elementos na lista
        if(this.getHead() == null && this.getTail() == null){
            System.out.println("Sem elementos a remover!");
            return null;

        }

        //Caso o elemento a ser removido seja o primeiro da lista
        if(posicao == 0){
            this.setHead(this.getHead().getNext());
            this.setNumeroDeElementos(this.getNumeroDeElementos() - 1);
            return removido;

        }else{  //Caso o elemento a ser removido esteja entre o meio e o fim da lista
            atual = this.getHead();
            Integer index = 0;
            anterior = atual;

            while(atual != this.getTail() && index < posicao){
                anterior = atual;
                atual = atual.getNext();
                index++;

            }

            //Caso o elemento a ser removido seja encontrado no meio da lista
            if(atual != this.getTail()){
                removido = atual;
                anterior.setNext(atual.getNext());
                this.setNumeroDeElementos(this.getNumeroDeElementos() - 1);
                return removido;

            }else{
                System.out.println("Elemento não encontrado na posição " + posicao + "!");
                return null;

            }

        }

    }

    /**
     * Remove o último nó (tail) da lista.
     * Se a lista não contiver elementos será
     * retornado null.
     * @return o objeto nó que foi removido da lista
     */
    public Node removerDoFinal(){
        Node removido;

        //Caso não haja elementos na lista
        if(this.getHead() == null && this.getTail() == null){
            removido = null;

        }else   //Caso haja somente um elemento na lista
            if(this.getHead() == this.getTail()){
                removido = this.removerDoInicio();

        }else{
            Node atual;

            for(atual = this.getHead(); atual.getNext().getNext() != null; atual = atual.getNext());

            removido = atual.getNext();
            atual.setNext(atual.getNext().getNext());
            this.setTail(atual);
            this.setNumeroDeElementos(this.getNumeroDeElementos() - 1);

        }

        return removido;

    }

    /**
     * Apresenta a lista de forma linear<br></br>
     * Exemplo:<br></br>
     * ( 1 ) --> ( 0 ) --> ( 7 ) -->
     */
    public void imprimir(){
        Node atual;

        for(atual = this.getHead(); atual != null; atual = atual.getNext())
            System.out.print("( " + atual.getIdentificador() + " ) --> ");

        System.out.println();

    }

    public Node getHead(){
        return this.head;

    }

    public void setHead(Node head){
        this.head = head;

    }

    public Node getTail(){
        return this.tail;

    }

    public void setTail(Node tail){
        this.tail = tail;

    }

    public Integer getRotulo(){
        return this.rotulo;

    }

    public void setRotulo(Integer rotulo){
        this.rotulo = rotulo;

    }

    public Integer getNumeroDeElementos(){
        return this.numeroDeElementos;
    
    }

    public void setNumeroDeElementos(Integer numeroDeElementos){
        this.numeroDeElementos = numeroDeElementos;

    }

    //==========================KRUSKAL===========================
    @Override
    public int compareTo(Lista aresta){
    
        if(this.getHead().getPeso() < aresta.getHead().getPeso())
            return -1;

        else
            if(this.getHead().getPeso() > aresta.getHead().getPeso())
                return 1;

        return 0;
    
    }
    //=============================================================

}
