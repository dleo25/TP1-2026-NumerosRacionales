public class Calculadora {

    private static NumeroRacional convertirARacional(String texto) {
        
        int posicionDeR = -1;
        // Buscamos la posición de la R
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == 'R') {
                posicionDeR = i;
                break;
            }
        }
        String numStr = texto.substring(0, posicionDeR);
        String denStr = texto.substring(posicionDeR + 1);

        int num = Integer.parseInt(numStr);
        int den = Integer.parseInt(denStr);

        return new NumeroRacional(num, den);
    }

    public static Racional evaluarExpresion(String expresion) {
        //almaceno en una pila los datos
        PilaDoblementeEnlazada<NumeroRacional> pila = new PilaDoblementeEnlazada<>();

        //numeroActual es una bolsa temporal para ir guardando datos hasta
        //formar la estructura 1R2, sino se rompe todo
        String numeroActual = "";

        //recorremos la cadena de derecha a izquierda uno x uno (leemos al reves para encontrar primero 2 numeros y luego un operador)
        for (int i = expresion.length() - 1; i >= 0; i--) {
            char c = expresion.charAt(i);

            //si encontramos un espacio sabemos q ya terminamos de leer un numero completo, asi que lo convertimos 
            //en un racional
            if (c == ' ') {
                if (numeroActual.length() > 0) {
                    NumeroRacional r = convertirARacional(numeroActual);
                    //como ya esta en el formato que queremos, lo apilamos
                    pila.apilar(r);
                    numeroActual = ""; //reiniciamos para el proximo numero
                }
            } 
            //si es un operador
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                NumeroRacional op1 = pila.desapilar();
                NumeroRacional op2 = pila.desapilar();

                if (c == '+') {
                    op1.suma(op2);
                } else if (c == '-') {
                    op2.neg();
                    op1.suma(op2);
                } else if (c == '*') {
                    op1.mult(op2);
                } else if (c == '/') {
                    op1.div(op2);
                }

                pila.apilar(op1);
            } 
            //si es un dígito o la letra R, lo agregamos al principio del token
            //al leer hacia atras, pegamos el caracter nuevo adelante para que no nos quede al reves
            else {
                numeroActual = c + numeroActual;
            }

        }

        //si al llegar al principio nos quedó un número guardado sin apilar
        if (numeroActual.length() > 0) {
            NumeroRacional r = convertirARacional(numeroActual);
            pila.apilar(r);
        }

        return pila.desapilar();
    }



    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usar java Calculadora <expresion>");
            System.out.println("Ej: java Calculadora '+ 1R2 2R3'");
        } else {
            System.out.println(evaluarExpresion(args[0]));
        }
    }
}
