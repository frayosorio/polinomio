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
        if (!linea2.equals("")) {
            linea2 += " = 0";
        }
        lbl.setFont(new Font("Courier New", Font.PLAIN, 12));
        lbl.setText("<html>" + linea1 + "<br>" + linea2 + "</html>");

    }

    //****** metodos estaticos
    public static Polinomio sumar(Polinomio p1, Polinomio p2) {
        Polinomio pR = new Polinomio();

        Nodo apuntador1 = p1.obtenerCabeza();
        Nodo apuntador2 = p2.obtenerCabeza();

        while (!(apuntador1 == null && apuntador2 == null)) {
            Nodo n = new Nodo();

            if (apuntador1 != null && apuntador2 != null
                    && apuntador1.exponente == apuntador2.exponente) {
                n.exponente = apuntador1.exponente;
                n.coeficiente = apuntador1.coeficiente + apuntador2.coeficiente;
                apuntador1 = apuntador1.siguiente;
                apuntador2 = apuntador2.siguiente;
            } else if ((apuntador2 == null)
                    || (apuntador1 != null && apuntador1.exponente < apuntador2.exponente)) {
                n.exponente = apuntador1.exponente;
                n.coeficiente = apuntador1.coeficiente;
                apuntador1 = apuntador1.siguiente;
            } else {
                n.exponente = apuntador2.exponente;
                n.coeficiente = apuntador2.coeficiente;
                apuntador2 = apuntador2.siguiente;
            }
            if (n.coeficiente != 0) {
                pR.agregar(n);
            }
        }

        return pR;
    }

    public static Polinomio restar(Polinomio p1, Polinomio p2) {
        Polinomio pR = new Polinomio();

        Nodo apuntador1 = p1.obtenerCabeza();
        Nodo apuntador2 = p2.obtenerCabeza();

        while (!(apuntador1 == null && apuntador2 == null)) {
            Nodo n = new Nodo();

            if (apuntador1 != null && apuntador2 != null
                    && apuntador1.exponente == apuntador2.exponente) {
                n.exponente = apuntador1.exponente;
                n.coeficiente = apuntador1.coeficiente - apuntador2.coeficiente;
                apuntador1 = apuntador1.siguiente;
                apuntador2 = apuntador2.siguiente;
            } else if ((apuntador2 == null)
                    || (apuntador1 != null && apuntador1.exponente < apuntador2.exponente)) {
                n.exponente = apuntador1.exponente;
                n.coeficiente = apuntador1.coeficiente;
                apuntador1 = apuntador1.siguiente;
            } else {
                n.exponente = apuntador2.exponente;
                n.coeficiente = -apuntador2.coeficiente;
                apuntador2 = apuntador2.siguiente;
            }
            if (n.coeficiente != 0) {
                pR.agregar(n);
            }
        }

        return pR;
    }
    
}
