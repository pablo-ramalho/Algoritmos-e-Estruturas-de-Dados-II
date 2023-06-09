package Semana1.Grafos;

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

        leitor.close();

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