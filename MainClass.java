package Kruskal;

import java.util.Scanner;

public class MainClass{

    public static void main(String[] args){
        Scanner leitor;
        Integer numeroDeVertices, adjacente, peso;
        Grafo grafo;
    
	    //Dados para o algoritmo de Kruskal
	    Grafo mst;

        leitor = new Scanner(System.in);
        numeroDeVertices = lerNumeroDeVertices();
        grafo = new Grafo(numeroDeVertices);

        for(Integer index = 0; index < numeroDeVertices; index++){

            for(Integer contador = 0; contador < grafo.getVertice(index).getNumeroDeElementos(); contador++){
                System.out.print("Escolha um vértice para ser adjacente ao vértice " + grafo.getVertice(index).getRotulo() + ": ");
                adjacente = leitor.nextInt();

                if(adjacente == grafo.getVertice(index).getRotulo())
                    peso = 0;

                else{
                    System.out.print("Digite o peso associado aos vértices " + grafo.getVertice(index).getRotulo() + " e " + adjacente + ": ");
                    peso = leitor.nextInt();
                    System.out.println();

                }

                grafo.adicionarAresta(index, adjacente, peso);

            }

            System.out.println();

        }

        grafo.imprimirGrafo();


	    //====================KRUSKAL========================
        mst = grafo.kruskal(grafo);

	    System.out.println("ÁRVORE GERADORA MÍNIMA OBTIDA:");
	    mst.imprimirGrafo();
	//===================================================
	
        //leitor.close();

    }

    /**
     * É permitido o armazenamento de até 20 vértices
     * @return o número de vértices digitado pelo usuário.
     * Se a quantidade digitada for nula ou negativa ou maior
     * do que 20 a quantidade será solicitada novamente.
     */
    private static Integer lerNumeroDeVertices(){
        Scanner leitor;
        Integer numeroDeVertices;

        leitor = new Scanner(System.in);

        System.out.print("Digite a quantidade de vértices que o grafo terá: ");

        do{
            numeroDeVertices = leitor.nextInt();
            System.out.print((numeroDeVertices <= 0 || numeroDeVertices > 20) ? "Quantidade de vértices inválida. Por favor digite uma quantidade válida: " : "Você solicitou a criação de " + numeroDeVertices + " vértices\n");

        }while(numeroDeVertices <= 0 || numeroDeVertices > 20);

        //leitor.close();

        return numeroDeVertices;

    }

}
    
