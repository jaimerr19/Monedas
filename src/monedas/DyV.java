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
public class DyV {

    double[][] resultado = new double[2][15];
    int[][] disPrimeros;
    int[][] disUltimos;
    int posArray;
    boolean posible;
    int[][] arrayLimpio = null;

    public DyV() {
        posArray = 0;
        posible = false;
    }

    public double[][] getResultado() {

        return this.resultado;
    }

    public void escribir(double[][] dispensador) {

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < dispensador[0].length; j++) {
                System.out.print(dispensador[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }

    public void inicializarResultado() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 15; j++) {
                resultado[i][j] = 0.0;
            }
        }
    }

    public boolean devolverCambio(int total, int[][] dispensador, int devuelto, int tamaño, int[][] dispensadorAux, int tamañoAux) {
        int posMedia = dispensador[0].length / 2, indiceAuxDisUltimos = 0;
        int k = tamañoAux, valorMedio = 0;
        boolean insertado = false;
        System.out.println("Devuelto es " + devuelto);
        System.out.println("El dispensador es \n");
        //escribir(dispensador);
        System.out.println("dispensador length " + posMedia + " tamaño es " + tamaño);
        if (dispensador[0].length > 0) {
            valorMedio = dispensador[0][posMedia];
        }

        arrayLimpio = new int[2][k];
        k = 0;
        for (int i = 0; i < dispensadorAux[0].length; i++) {
            if (dispensadorAux[1][i] != 0.0) {
                arrayLimpio[0][k] = dispensadorAux[0][i];
                arrayLimpio[1][k] = dispensadorAux[1][i];
                k++;
            }

        }
        disPrimeros = new int[2][tamaño / 2];
        disUltimos = new int[2][(tamaño / 2)];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < posMedia; j++) {
                disPrimeros[i][j] = dispensador[i][j];
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = posMedia + 1; j < tamaño; j++) {
                disUltimos[i][indiceAuxDisUltimos] = dispensador[i][j];
                indiceAuxDisUltimos++;
            }
            indiceAuxDisUltimos = 0;
        }

        // escribir(disPrimeros);
        //escribir(disUltimos);
        if (total == devuelto) {
            posible = true;
        } else if (arrayLimpio[0].length == 0) {
            posible = false;
        } else {
            if ((disPrimeros[0].length > 0) && (total - devuelto >= disPrimeros[0][(tamaño / 2) - 1])) {
                if (disPrimeros[0].length > 1) {
                    devolverCambio(total, disPrimeros, devuelto, disPrimeros[0].length, arrayLimpio, k);
                } else if (disPrimeros[1][0] != 0.0) {
                    //if ((resultado[0][posArray] != (double)(disPrimeros[0][0]/100.0)) && (resultado[0][posArray] != 0.0)) {
                    if (resultado[0][posArray] == (disPrimeros[0][0] / 100.0) || resultado[0][posArray] == 0.0) {
                        resultado[0][posArray] = (double) (disPrimeros[0][0] / 100.0);
                        resultado[1][posArray]++;
                        disPrimeros[1][0]--;
                        for (int i = 0; i < arrayLimpio[0].length; i++) {
                            if (arrayLimpio[0][i] == disPrimeros[0][0]) {
                                arrayLimpio[1][i]--;
                            }
                        }
                    } else {
                        for (int j = 0; j < resultado[0].length; j++) {
                            if (resultado[0][j] == (disPrimeros[0][0] / 100.0)) {
                                insertado = true;
                                resultado[0][j]++;
                                resultado[1][j]++;
                                disPrimeros[1][0]--;
                                for (int i = 0; i < arrayLimpio[0].length; i++) {
                                    if (arrayLimpio[0][i] == disPrimeros[0][0]) {
                                        arrayLimpio[1][i]--;
                                    }
                                }
                            }
                        }
                        if (!insertado) {
                            posArray++;
                            resultado[0][posArray] = (double) (disPrimeros[0][0] / 100.0);
                            resultado[1][posArray]++;
                            disPrimeros[1][0]--;
                            for (int i = 0; i < arrayLimpio[0].length; i++) {
                                if (arrayLimpio[0][i] == disPrimeros[0][0]) {
                                    arrayLimpio[1][i]--;
                                }
                            }
                        }
                    }
                    devolverCambio(total, disPrimeros, devuelto + disPrimeros[0][0], disPrimeros[0].length, arrayLimpio, k);
                } else {
                    devolverCambio(total, disUltimos, devuelto, disUltimos[0].length, arrayLimpio, k);
                }
            } else if (valorMedio + devuelto == total) {
                if (dispensador[1][posMedia] != 0.0) {
                    if (resultado[0][posArray] == (valorMedio / 100.0) || resultado[0][posArray] == 0.0) {
                        resultado[0][posArray] = (double) (valorMedio / 100.0);
                        resultado[1][posArray]++;
                        dispensador[1][posMedia]--;
                        for (int i = 0; i < arrayLimpio[0].length; i++) {
                            if (arrayLimpio[0][i] == valorMedio) {
                                arrayLimpio[1][i]--;
                            }
                        }
                    } else {
                        for (int j = 0; j < resultado[0].length; j++) {
                            if (resultado[0][j] == (valorMedio / 100.0)) {
                                insertado = true;
                                resultado[0][j]++;
                                resultado[1][j]++;
                                dispensador[1][posMedia]--;
                                for (int i = 0; i < arrayLimpio[0].length; i++) {
                                    if (arrayLimpio[0][i] == valorMedio) {
                                        arrayLimpio[1][i]--;
                                    }
                                }
                            }
                        }
                        if (!insertado) {
                            posArray++;
                            resultado[0][posArray] = (double) (valorMedio / 100.0);
                            resultado[1][posArray]++;
                            dispensador[1][posMedia]--;
                            for (int i = 0; i < arrayLimpio[0].length; i++) {
                                if (arrayLimpio[0][i] == valorMedio) {
                                    arrayLimpio[1][i]--;
                                }
                            }
                        }
                    }
                    devolverCambio(total, disUltimos, devuelto + valorMedio, disUltimos[0].length, arrayLimpio, k);
                } else {
                    devolverCambio(total, disUltimos, devuelto, disUltimos[0].length, arrayLimpio, k);
                }
            } else if (disUltimos[0].length > 0) {
                if (((total - devuelto) > disUltimos[0][0]) && (valorMedio < (total - devuelto))) {
                    System.out.println("Caso que coge valorMedio");
                    if (dispensador[1][posMedia] != 0.0) {
                        if (resultado[0][posArray] == (valorMedio / 100.0) || resultado[0][posArray] == 0.0) {
                            resultado[0][posArray] = (double) (valorMedio / 100.0);
                            resultado[1][posArray]++;
                            dispensador[1][posMedia]--;
                            for (int i = 0; i < arrayLimpio[0].length; i++) {
                                if (arrayLimpio[0][i] == valorMedio) {
                                    arrayLimpio[1][i]--;
                                }
                            }
                        } else {
                            for (int j = 0; j < resultado[0].length; j++) {
                                if (resultado[0][j] == (valorMedio / 100.0)) {
                                    insertado = true;
                                    resultado[0][j]++;
                                    resultado[1][j]++;
                                    dispensador[1][posMedia]--;
                                    for (int i = 0; i < arrayLimpio[0].length; i++) {
                                        if (arrayLimpio[0][i] == valorMedio) {
                                            arrayLimpio[1][i]--;
                                        }
                                    }
                                }
                            }
                            if (!insertado) {
                                posArray++;
                                resultado[0][posArray] = (double) (valorMedio / 100.0);
                                resultado[1][posArray]++;
                                dispensador[1][posMedia]--;
                                for (int i = 0; i < arrayLimpio[0].length; i++) {
                                    if (arrayLimpio[0][i] == valorMedio) {
                                        arrayLimpio[1][i]--;
                                    }
                                }
                            }
                        }
                        devolverCambio(total, disUltimos, devuelto + valorMedio, disUltimos[0].length, arrayLimpio, k);
                    } else {
                        devolverCambio(total, disUltimos, devuelto, disUltimos[0].length, arrayLimpio, k);
                    }
                } else if (disUltimos[0].length > 1) {
                    devolverCambio(total, disUltimos, devuelto, disUltimos[0].length, arrayLimpio, k);
                } else if (disUltimos[0].length > 0 && disUltimos[1][0] != 0.0) {
                    if (resultado[0][posArray] == ((double) (disUltimos[0][0] / 100.0)) || resultado[0][posArray] == 0.0) {
                        resultado[0][posArray] = (double) (disUltimos[0][0] / 100.0);
                        resultado[1][posArray]++;
                        disUltimos[1][0]--;
                        for (int i = 0; i < arrayLimpio[0].length; i++) {
                            if (arrayLimpio[0][i] == disUltimos[0][0]) {
                                arrayLimpio[1][i]--;
                            }
                        }
                    } else {
                        for (int j = 0; j < resultado[0].length; j++) {
                            if (resultado[0][j] == (double) (disUltimos[0][0] / 100.0)) {
                                insertado = true;
                                resultado[0][j]++;
                                resultado[1][j]++;
                                disUltimos[1][0]--;
                                for (int i = 0; i < arrayLimpio[0].length; i++) {
                                    if (arrayLimpio[0][i] == disUltimos[0][0]) {
                                        arrayLimpio[1][i]--;
                                    }
                                }
                            }
                        }
                        if (!insertado) {
                            posArray++;
                            resultado[0][posArray] = (double) (disUltimos[0][0] / 100.0);
                            resultado[1][posArray]++;
                            disUltimos[1][0]--;
                            for (int i = 0; i < arrayLimpio[0].length; i++) {
                                if (arrayLimpio[0][i] == valorMedio) {
                                    arrayLimpio[1][i]--;
                                }
                            }
                        }
                    }
                    devolverCambio(total, disPrimeros, devuelto + disUltimos[0][0], disPrimeros[0].length, arrayLimpio, k);
                } else {
                    devolverCambio(total, arrayLimpio, devuelto, arrayLimpio[0].length, arrayLimpio, k);
                }
            } else if (disUltimos[0].length == 0) {
                System.out.println("Caso que coge valorMedio");
                if (dispensador[1][posMedia] != 0.0 && devuelto + valorMedio <= total) {
                    if (dispensador[1][posMedia] != 0.0) {
                        if (resultado[0][posArray] == (valorMedio / 100.0) || resultado[0][posArray] == 0.0) {
                            resultado[0][posArray] = (double) (valorMedio / 100.0);
                            resultado[1][posArray]++;
                            dispensador[1][posMedia]--;
                            for (int i = 0; i < arrayLimpio[0].length; i++) {
                                if (arrayLimpio[0][i] == valorMedio) {
                                    arrayLimpio[1][i]--;
                                }
                            }
                        } else {
                            for (int j = 0; j < resultado[0].length; j++) {
                                if (resultado[0][j] == (valorMedio / 100.0)) {
                                    insertado = true;
                                    resultado[0][j]++;
                                    resultado[1][j]++;
                                    dispensador[1][posMedia]--;
                                    for (int i = 0; i < arrayLimpio[0].length; i++) {
                                        if (arrayLimpio[0][i] == valorMedio) {
                                            arrayLimpio[1][i]--;
                                        }
                                    }
                                }
                            }
                            if (!insertado) {
                                posArray++;
                                resultado[0][posArray] = (double) (valorMedio / 100.0);
                                resultado[1][posArray]++;
                                dispensador[1][posMedia]--;
                                for (int i = 0; i < arrayLimpio[0].length; i++) {
                                    if (arrayLimpio[0][i] == valorMedio) {
                                        arrayLimpio[1][i]--;
                                    }
                                }
                            }
                        }
                        devolverCambio(total, arrayLimpio, devuelto + valorMedio, arrayLimpio[0].length, arrayLimpio, k);
                    } else {
                        devolverCambio(total, arrayLimpio, devuelto, arrayLimpio[0].length, arrayLimpio, k);
                    }
                }
            }

        }
        return posible;
    }

}