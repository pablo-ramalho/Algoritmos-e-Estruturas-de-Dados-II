package Kruskal;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Esta classe representa um grafo tendo
 * como implementação uma lista de adjacências.<br></br>
 * Atributo:<br></br>
 * vertice[] - um vetor de listas simplesmente encadeadas
 */
public class Grafo{

    private Lista vertice[];

    /**
     * Constrói um objeto que representa um grafo.
     * @param numeroDeVertices a quantidade de vértices que o grafo terá (máximo 20)
     */
    public Grafo(Integer numeroDeVertices){
        this.vertice = new Lista[numeroDeVertices];
        this.atribuirGraus();

    }

    //======================KRUSKAL========================
    /**
     * Constrói um objeto que representa um grafo<br></br>
     * <b>OBS: </b>Este segundo construtor foi criado pois o construtor com o parâmetro <b>numeroDeVertices</b>
     * é usado para criar grafos usando a entrada padrão pelo usuário, somente sendo usado para criar o grafo original.
     * @param grafo o grafo original que será usado para criar a floresta.
     */
    public Grafo(Grafo grafo){
        Integer index;
        
        this.vertice = new Lista[grafo.vertice.length];

        for(index = 0; index < this.vertice.length; index++)
            this.vertice[index] = new Lista(index, 0);

    }

    public Grafo kruskal(Grafo grafoOriginal){
        Grafo mst;                   //Árvore geradora mínima criada a partir do grafo original
        Queue<Lista> arestas = null; //Armazena as arestas do grafo original (inicialmente vazia)
        Integer index;               //Percorre a lista de adjacências (de 0 a n - 1, onde n corresponde ao número de vértices do grafo) e serve como índice iterador para adicionar as arestas bidirecionais
        Node atual;                  //Itera sobre as adjacências de cada vértice
        Lista aresta;                //Armazena temporariamente informações de cada aresta para incluí-las na fiia de prioridade

        //Cria a floresta (inicialmente todos os vértices têm grau 0)
        mst = new Grafo(grafoOriginal);

        //Cria uma fila de prioridade para armazenar as arestas do grafo original
        arestas = new PriorityQueue<>();

        /**
         * Adiciona as arestas do grafo à fila de prioridade (faz os cortes).
         * A implementação interna de uma PriorityQueue<> já faz
         * a ordenação dos elementos por padrão em ordem crescente
         */
        for(index = 0; index < grafoOriginal.vertice.length; index++){
            
            for(atual = grafoOriginal.vertice[index].getHead(); atual != null; atual = atual.getNext()){

                //Restringe a adição das arestas simétricas na fila de prioridade
                if(atual.getIdentificador() == grafoOriginal.vertice[atual.getIdentificador()].getRotulo()){
                    Node auxiliar = grafoOriginal.vertice[atual.getIdentificador()].getHead();

                    do{
                        
                        if(auxiliar.getIdentificador() != grafoOriginal.vertice[index].getRotulo()){
                            aresta = new Lista(index, 1);
                            this.incluirCorte(aresta, arestas, atual);
                        
                        }

                        auxiliar = auxiliar.getNext();

                    }while(auxiliar.getIdentificador() != grafoOriginal.vertice[index].getRotulo() && auxiliar != null);

                }else{
                    
                    aresta = new Lista(index, 1);
                    this.incluirCorte(aresta, arestas, atual);

                }

            }

        }

        //TESTE
        for(Lista aux : arestas){
            System.out.print("[ " + aux.getRotulo() + " ] - " + aux.getHead().getPeso() + " -> ");
            aux.imprimir();

        }

        /** 
         * Seleciona e adiciona as arestas seguras 
         * à árvore geradora mínima a partir da fila de prioridade
        */
        while(arestas.isEmpty() != false){

            aresta = arestas.poll();
            
            //Se a próxima aresta obtida na fila de prioridade não forma um ciclo entre os dois vértices ela é adicionada à árvore geradora mínima
            if(this.mesmoConjunto(aresta.getRotulo(), mst.vertice[aresta.getRotulo()].getHead().getIdentificador(), mst) == false){
                
                mst.vertice[aresta.getRotulo()].adicionarNoFinal(aresta.getHead().getIdentificador(), aresta.getHead().getPeso(), new Node(aresta.getHead().getIdentificador(), aresta.getHead().getPeso()));
                mst.vertice[aresta.getRotulo()].setNumeroDeElementos(aresta.getNumeroDeElementos() + 1);
                
                aresta = arestas.poll();
                mst.vertice[aresta.getRotulo()].adicionarNoFinal(aresta.getHead().getIdentificador(), aresta.getHead().getPeso(), new Node(aresta.getHead().getIdentificador(), aresta.getHead().getPeso()));
                mst.vertice[aresta.getRotulo()].setNumeroDeElementos(aresta.getNumeroDeElementos() + 1);

            //Caso contrário a próxima aresta obtida na fila de prioridade é descartada
            }else
                 arestas.poll();

        }

        return mst;

    }

    /**
     * Adiciona a aresta à fila de prioridade
     */
    private void incluirCorte(Lista aresta, Queue<Lista> arestas, Node atual){
        aresta.adicionarNoInicio(atual.getIdentificador(), atual.getPeso(), new Node(atual.getIdentificador(), atual.getPeso()));
        arestas.offer(aresta);

    }

    /**
     * Método auxiliar para verificar se dois vértices especificados
     * como parâmetro pertencem no mesmo conjunto ou não
     * @param vertice1 o vértice de origem
     * @param vertice2 o vértice de destino
     * @param mst a árvore geradora mínima obtida até o momento
     * @return <b>true</b> se os dois vértices estão no mesmo conjunto e <b>false</b> caso contrário
     */
    private boolean mesmoConjunto(Integer vertice1, Integer vertice2, Grafo mst){
        Node atual;
        Boolean same = false;

        if(mst.vertice[vertice1].getHead() != null){

              for(atual = mst.vertice[vertice1].getHead(); atual != null; atual = atual.getNext()){

                   if(atual.getIdentificador() == vertice2){
                      same = true;
                      break;

                   }

              }

        }

        return same;

    }

    //=====================================================

    /**
     * Determina a quantidade de vértices adjacentes a cada vértice.
     * @param numeroDeVertices a quantidade de vértices que o grafo tem
     */
    private void atribuirGraus(){
        Integer grau;
        
        for(Integer index = 0; index < this.vertice.length; index++){
            System.out.print("Digite o grau do vértice " + index + ": ");
            grau = lerGrau();

            this.vertice[index] = new Lista(index, grau);

        }

    }

    /**
     * Determina a quantidade de vértices adjacentes a um vértice individualmente.
     * @param numeroDeVertices a quantidade de vértices que o grafo tem
     * @return o grau do vértice fornecido pelo usuário
     */
    private Integer lerGrau(){
        Scanner leitor = new Scanner(System.in);
        Integer grau;

        do{
            grau = leitor.nextInt();
            System.out.println((grau < 0 || grau > this.vertice.length) ? "Graus negativos ou maiores que a quantidade de vértices não são válidos. Por favor digite um grau maior ou igual a zero: " : "Grau do vértice: " + grau + "\n");

        }while(grau < 0 || grau > this.vertice.length);

        return grau;

    }

    /**
     * Conecta dois vértices por uma aresta.
     * @param vertice1 o primeiro vértice (0 até o número de vértices do grafo)
     * @param vertice2 o segundo vértice (0 até o número de vértices do grafo)
     * @param peso o peso/custo para ir do vertice1 até o vértice2
     */
    public void adicionarAresta(Integer vertice1, Integer vertice2, Integer peso){
        Node novo;

        if((vertice1 >= 0 && vertice1 < this.vertice.length) && (vertice2 >= 0 && vertice2 < this.vertice.length)){
            novo = new Node(vertice2, peso);
            this.vertice[vertice1].adicionarNoFinal(vertice2, peso, novo);

        }else
            System.out.println("Parâmetro(s) inválido(s)!");

    }

    /**
     * Imprime os vértices e seus respectivos 
     * vértices adjacentes de forma linear.
     * Exemplo:
     * <br></br>
     * [ 0 ] --> ( 2 ) --> ( 1 ) --> 
     * <br></br>
     */
    public void imprimirGrafo(){
        Node atual;

        //Imprime o grafo em formato de lista
        for(Integer index = 0; index < this.vertice.length; index++){
            System.out.print("[ "+ this.vertice[index].getRotulo() + " ] --> ");
            this.vertice[index].imprimir();

        }

        //Imprime as informações sobre os graus de cada vértice
        for(Integer index = 0; index < this.vertice.length; index++)
            System.out.println("Grau do vértice " + this.vertice[index].getRotulo() + ": " + this.vertice[index].getNumeroDeElementos());

        System.out.println();
        
        //Imprime as informações sobre os pesos entre os vértices
        for(Integer index = 0; index < this.vertice.length; index++){
            
            for(atual = this.vertice[index].getHead(); atual != null; atual = atual.getNext())
                System.out.println("Peso entre " + this.vertice[index].getRotulo() + " e " + atual.getIdentificador() + ": " + atual.getPeso());

        }

    }

    /**
     * Obtém o vértice na sua respectiva posição no vetor.
     * @param vertice o número que identifica o vértice (0 até número de vértices - 1)
     * @return o vértice dado o número que o identifica
     */
    public Lista getVertice(Integer vertice){
        return this.vertice[vertice];

    }

}
