package polinomios;

import java.awt.Font;
import javax.swing.JLabel;


public class Polinomio {
    Nodo cabeza;

    //Metodo constructor que crea un Polinomio vacio
    public Polinomio() {
        cabeza = null;
    }

    //Agrega un nodo en la ubicación que le corresponde en el polinomio
    public void agregar(Nodo n) {
        if (n != null) {
            if (cabeza == null) {
                cabeza = n;
            } else {
                Nodo apuntador = cabeza;
                Nodo predecedor = null;
                int encontrado = 0;
                while (apuntador != null && encontrado == 0) {
                    if (n.exponente == apuntador.exponente) {
                        encontrado = 1;
                    } else if (n.exponente < apuntador.exponente) {
                        encontrado = 2;
                    } else {
                        predecedor = apuntador;
                        apuntador = apuntador.siguiente;
                    }
                }
                if (encontrado == 1) {
                    apuntador.coeficiente = n.coeficiente;
                } else {
                    insertar(n, predecedor);
                }
            }
        }
    }

    //Inserta un nodo en medio de la lista
    public void insertar(Nodo n, Nodo predecesor) {
        if (n != null) {
            if (predecesor != null) {
                n.siguiente = predecesor.siguiente;
                predecesor.siguiente = n;
            } else {
                n.siguiente = cabeza;
                cabeza = n;
            }
        }
    }

    public int grado() {
        if (cabeza != null) {
            Nodo apuntador = cabeza;
            while (apuntador.siguiente != null) {
                apuntador = apuntador.siguiente;
            }
            return apuntador.exponente;
        }
        return -1;
    }

    public Nodo obtenerCabeza() {
        return cabeza;
    }

    public void mostrar(JLabel lbl) {
        String espacio = "&nbsp;";
        String linea1 = "";
        String linea2 = "";
        Nodo apuntador = cabeza;
        while (apuntador != null) {
            String texto = String.valueOf(apuntador.coeficiente) + " X";
            if (apuntador.coeficiente >= 0) {
                texto = "+" + texto;
            }
            linea1 += String.format("%0" + texto.length() + "d", 0).replace("0", espacio);
            linea2 += texto;

            texto = String.valueOf(apuntador.exponente);
            linea2 += String.format("%0" + texto.length() + "d", 0).replace("0", espacio);
            linea1 += texto;

            apuntador = apuntador.siguiente;
        }
        linea2 += " = 0";
        lbl.setFont(new Font("Courier New", Font.PLAIN, 12));
        lbl.setText("<html>" + linea1 + "<br>" + linea2 + "</html>");

    }
}
