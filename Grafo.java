package Kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

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

    //=========================KRUSKAL===============================
    /**
     * Constrói um objeto que representa um grafo a partir de um grafo já criado (cópia) <br></br>
     * <b>OBS: </b>este construtor é necessário para que seja criada a floresta a partir
     * do grafo passado como parâmetro, pois o construtor acima é usado
     * para criar o grafo usando a entrada padrão pelo usuário
     * @param grafoOriginal o grafo a partir do qual se criará a árvore geradora mínima
     */
    public Grafo(Grafo grafoOriginal){
        Integer index;

        this.vertice = new Lista[grafoOriginal.vertice.length];

        for(index = 0; index < vertice.length; index++)
            this.vertice[index] = new Lista(index, 0);

    }

    public Grafo kruskal(Grafo grafoOriginal){
        Grafo mst;
        List<Lista> cortes;

        //Cria a floresta (todos os vértices com grau 0)
        mst = new Grafo(grafoOriginal);

        //Faz os cortes no grafo original
        cortes = this.extrairArestas(grafoOriginal);

        //Imprime as arestas extraídas do grafo
        System.out.println("ARESTAS OBTIDAS DO GRAFO:");
        this.imprimirCortes(cortes);

        //Ordena as arestas com base no critério de peso
        Collections.sort(cortes);

        //Imprime as arestas em ordem crescente de peso
        System.out.println("ARESTAS ORDENADAS PELO PESO:");
        this.imprimirCortes(cortes);

	    //Conecta os vértices de forma a não formar ciclos
	    this.unionFind(mst, cortes);

	    return mst;

    }

    /**
     * Faz os cortes no grafo original, armazenando cada aresta em um vetor dinâmico
     * de forma que todos os vértices tenham grau 1<br></br>
     * <b>Exemplos de cortes:</b><br></br>
     * [ 1 ] - 3 - ( 4 )<br></br>
     * [ 7 ] - 1 - ( 2 )
     * @param grafoOriginal o grafo a partir do qual serão extraídas as arestas
     * @return um vetor dinâmico contendo as arestas do grafo original
     */
    private List<Lista> extrairArestas(Grafo grafoOriginal){
        Node atual;
        Integer index, secondIndex;
        List<Lista> cortes;
        Lista aresta;

        cortes = new ArrayList<>();

        //Obtém as arestas do grafo original e as armazena no vetor dinâmico
        for(index = 0; index < grafoOriginal.vertice.length; index++){
            for(atual = grafoOriginal.vertice[index].getHead(); atual != null; atual = atual.getNext()){
                aresta = new Lista(grafoOriginal.vertice[index].getRotulo(), 1);
                aresta.adicionarNoInicio(atual.getIdentificador(), atual.getPeso(), new Node(atual.getIdentificador(), atual.getPeso()));
                cortes.add(aresta);
		    
            }

        }

        //Retira as arestas simétricas
        for(index = 0; index < cortes.size(); index++){
            for(secondIndex = index; secondIndex < cortes.size(); secondIndex++){
                
                if(cortes.get(secondIndex).getHead().getIdentificador() == cortes.get(index).getRotulo() && cortes.get(secondIndex).getRotulo() == cortes.get(index).getHead().getIdentificador()){
                    cortes.remove((int)secondIndex);
                    break;

                }

            }

        }

        return cortes;

    }

	private void unionFind(Grafo mst, List<Lista> cortes){
		List<Set<Integer>> grupos;
		Set<Integer> grupo;
		Integer index, conjunto1, conjunto2;

		grupos = new ArrayList<>(mst.vertice.length);

		//Adiciona cada vértice a um grupo separado
		for(index = 0; index < grupos.size(); index++){
			grupo = new TreeSet<>();
			grupo.add(mst.vertice[index].getRotulo());
			grupos.add(grupo);

		}

        //Faz a união dos conjuntos de forma a não conter ciclos
        for(index = 0; grupos.size() > 1 && index < cortes.size(); index++){
            conjunto1 = cortes.get(index).getRotulo();
            conjunto2 = cortes.get(index).getHead().getIdentificador();

            for(; grupos.get(conjunto1).contains(cortes.get(index).getRotulo()) == false; conjunto1--);
            
            for(; conjunto2 >= grupos.size(); conjunto2--);
            for(; grupos.get(conjunto2).contains(cortes.get(index).getHead().getIdentificador()) == false; conjunto2--);

            if(grupos.get(conjunto1).contains(cortes.get(index).getHead().getIdentificador()) == false){
                mst.vertice[cortes.get(index).getRotulo()].adicionarNoInicio(cortes.get(index).getHead().getIdentificador(), cortes.get(index).getHead().getPeso(), new Node(cortes.get(index).getHead().getIdentificador(), cortes.get(index).getHead().getPeso()));
                mst.vertice[cortes.get(index).getRotulo()].setNumeroDeElementos(cortes.get(index).getNumeroDeElementos() + 1);

                mst.vertice[cortes.get(index).getHead().getIdentificador()].adicionarNoInicio(cortes.get(index).getRotulo(), cortes.get(index).getHead().getPeso(), new Node(cortes.get(index).getRotulo(), cortes.get(index).getHead().getPeso()));
		mst.vertice[cortes.get(index).getHead().getIdentificador()].setNumeroDeElementos(cortes.get(index).getNumeroDeElementos() + 1);

                grupos.get(conjunto1).addAll(grupos.get(conjunto2));
                grupos.remove((int)conjunto2);

            }

        }

	}

    private void imprimirCortes(List<Lista> cortes){
        Integer index;

        System.out.println();

        for(index = 0; index < cortes.size(); index++)
            System.out.println("[ " + cortes.get(index).getRotulo() + " ] - " + cortes.get(index).getHead().getPeso() + " -> ( " + cortes.get(index).getHead().getIdentificador() + " ) -->");

        System.out.println();

    }

    //===============================================================

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
