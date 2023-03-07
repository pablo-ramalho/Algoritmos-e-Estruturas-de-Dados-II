package Semana3.Dijkstra;

import java.util.Arrays;
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
     * Determina o menor caminho de um vértice de origem até um vértice de destino. <br></br>
     * Para calcular as distâncias usa uma "tabela" de rotas que consiste de três vetores de mesmo tamanho:<br></br>
     * distancia[] - indica a estimativa para cada vértice.<br></br>
     * pai[] - indica o nó predecessor de cada vértice.<br></br>
     * fechado[] - indica se cada vértice está fechado ou não.<br></br>
     * @param source um inteiro de 0 a n - 1, onde n corresponde ao número de vértices
     * @param destination um inteiro de 0 a n - 1, onde n corresponde ao número de vértices
     * @throws IllegalArgumentException caso a fonte/destino passado(s) como parâmetro sejam negativos ou ultrapassem
     *                                  o intervalo permitido para acessar os elementos dos vetores.
     * @return uma lista simplesmente encadeada contendo os vértices do menor caminho
     */
    public Lista dijkstra(Integer source, Integer destination) throws IllegalArgumentException{

        if(source < 0 || source >= this.vertice.length || destination < 0 || destination >= this.vertice.length)
            throw new IllegalArgumentException("Fonte e/ou destino solicitado(s) fora do intervalo de 0 a " + (this.vertice.length - 1));
                     
        Integer distancia[] = new Integer[this.vertice.length]; //Cria um vetor de distâncias onde cada posisção indica a distância até outro(s) vértices
        Integer pai[] = new Integer[this.vertice.length];       //Cria um vetor de predecessores
        Boolean fechado[] = new Boolean[this.vertice.length];   //Cria um vetor que indica se cada vértice foi visitado ou não
        Lista caminhoMinimo;                                    //Cria uma lista simplesmente encadeada que contém os nós do caminho mínimo
        Integer menor = source;                                 //Cria uma variável local para armazenar o índice do vértice que contém a menor estimativa                                           //Cria uma variável local auxiliar para percorrer a(s) lista(s) encadeada(s)
        Integer temp = destination;
        Integer contador = 0;

        this.inicializaDijkstra(source, distancia, pai, fechado);

        while(this.existemVerticesAbertos(fechado)){
            
            //Explora as adjacências do vértice com a menor estimativa encontrada até o momento
            for(Node atual = vertice[menor].getHead(); atual != null; atual = atual.getNext()){


                if(distancia[menor] + atual.getPeso() < distancia[atual.getIdentificador()]){
                    distancia[atual.getIdentificador()] = distancia[menor] + atual.getPeso();
                    pai[atual.getIdentificador()] = menor;
                
                }

            }

            //Fecha o vértice com a menor estimativa encontrada até o momento
            fechado[menor] = true;
            
            //Verifica se ainda há vértices abertos
            if(this.existemVerticesAbertos(fechado));
                //Determina o vértice com a menor estimativa dentre os vértices abertos
                menor = this.verticeComMenorEstimativa(distancia, fechado);
            
        }

        fechado[menor] = true;
        
        if(pai[temp] == -1)
            return null;

        else{
            while(temp != source){
                temp = pai[temp];
                contador++;

            }

            caminhoMinimo = new Lista(source, contador);
            temp = destination;

            while(temp != source){
                caminhoMinimo.adicionarNoInicio(temp, distancia[temp], new Node(temp, distancia[temp]));
                temp = pai[temp];

            }

            return caminhoMinimo;

        }

    }
    
    private void inicializaDijkstra(Integer source, Integer[] distancia, Integer[] pai, Boolean[] fechado){
        
        //Inicializa a distância da fonte até todos os outros vértices (com exceção dela mesma) com "infinito"
        Arrays.fill(distancia, Integer.MAX_VALUE / 2);
        distancia[source] = 0;

        //Inicializa o nó pai de cada vértice
        Arrays.fill(pai, -1);

        //Inicializa todos os vértices como abertos
        Arrays.fill(fechado, false);

    }

    private Boolean existemVerticesAbertos(Boolean[] fechado){
        Integer abertos = fechado.length;

        for(Integer index = 0; index < fechado.length; index++){

            if(fechado[index] == true)
                abertos--;

        }

        return (abertos != 0);

    }

    private Integer verticeComMenorEstimativa(Integer[] distancia, Boolean[] fechado){
        Integer menor;          //Cria uma variável local para armazenar o índice que indica o vértice com a menor estimativa
        Integer[] vetor;        //Cria um vetor com o mesmo tamanho do vetor de distâncias

        vetor = new Integer[distancia.length];

        //Atribui as estimativas do vetor de distâncias ao vetor de estimativas
        for(Integer index = 0; index < distancia.length; index++){

            if(fechado[index] == false)
                vetor[index] = distancia[index];

            else
                vetor[index] = Integer.MAX_VALUE;

        }

        //Determina qual o vértice com a menor estimativa
        menor = 0;

        for(Integer index = 0; index < vetor.length; index++){

            if(vetor[index] < vetor[menor])
                menor = index;

        }

        return menor;

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

        //leitor.close();

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