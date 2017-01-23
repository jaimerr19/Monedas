/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package monedas;



/**
 *
 * @author TOSHIBA
 */
public class Voraz {

    static final int MONEDAS = 15;
    
    int posArrayAux;
    double[][] resultado = new double[2][15];
    public Voraz() {
        
    }
        
    public double[][] getResultado(){
        
        return this.resultado;
    }
    
  /*  public void escribir (double[][] dispensador){
        resultado=dispensador;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(dispensador[i][j] + " ");
            }
            System.out.println("");
        }
    }*/
    
    public void inicializarResultado (){
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 15; j++) {
                resultado[i][j]=0.00;
            }            
        }
    }
        
    public boolean devolverCambio (int total, int[][] dispensador, int tamaño){
        int valorActual, resto = 0;
        int posArray = 0;
        inicializarResultado();
        
        posArrayAux = 0;

        while (resto != total && posArray < tamaño) {           
            valorActual = dispensador[0][posArray];
            if (valorActual + resto > total) {
                posArray++;
            } else {
                resto = resto + valorActual;
                if (resultado[0][posArrayAux] == 0.0) {
                    resultado[0][posArrayAux] = (double) (valorActual/100.0);
                } else if (resultado[0][posArrayAux] != (double) (valorActual/100.0)) {
                    posArrayAux++;
                    resultado[0][posArrayAux] = (double) (valorActual/100.0);
                }
                resultado[1][posArrayAux]++;
                dispensador[1][posArray]--;
            }
            if (dispensador[1][posArray] == 0){
                posArray++;
            }
        }
        return resto == total;
    }
}
