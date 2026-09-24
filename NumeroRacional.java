import java.time.format.SignStyle;

public class NumeroRacional implements Racional{

    
    private int numerador;
    private int denominador;
    
    //el constructor inicializa los atributos y comprueba con repOk() que sean validos
    public NumeroRacional(int numerador, int denominador){
        this.numerador = numerador;
        this.denominador = denominador;
        if (!repOk()) throw new IllegalArgumentException("El denominador no puede ser 0");
    }

    //El repok() verifica que el denominador no sea 0
    @Override
    public boolean repOk(){
        if(denominador == 0) return false;
        else return true;
    }

    //devuelve como String el numerador/denominador
    @Override
    public String toString(){
        return this.numerador + "/" + denominador;
    }

    //multiplica el numerador por -1 para invertir la fraccion
    @Override
    public void neg(){
        this.numerador = this.numerador * -1;

        simplificar();
    }

    //la suma de fracciones requiere mismo denominador, entonces multiplicamos entre si para obtener la formula
    // a/b + c/d = a*d + b*c / a * d
    //el if sirve para simplificar, aunque pienso hacer un metodo privado
    //Al final actualiza el numerador y denominador
    @Override
    public void suma(Racional r){
        NumeroRacional otro = (NumeroRacional) r;
        int nuevoNumerador = (this.numerador * otro.getDenominador()) + (this.denominador * otro.getNumerador());
        int nuevoDenominador = this.denominador * otro.getDenominador();
        
        this.numerador = nuevoNumerador;
        this.denominador = nuevoDenominador;

        simplificar();
    }

    //para multiplicar fracciones lo hacemos "Derecho"
    @Override
    public void mult(Racional r){
        NumeroRacional otro = (NumeroRacional) r;
        this.numerador = this.numerador * otro.getNumerador();
        this.denominador = this.denominador * otro.getDenominador();

        simplificar();
    }

    //para dividir primero chequeamos que r sea valido
    //para dividir lo hacemos "Cruzado", asi creamos los nuevos numeros
    //Al final actualizamos el numerador y denominador
    @Override
    public void div(Racional r){
        NumeroRacional otro = (NumeroRacional) r;
        
        if(otro.getNumerador() == 0) throw new IllegalArgumentException("No se puede dividir por cero");

        int nuevoDenominador = this.denominador * otro.getNumerador();
        int nuevoNumerador = this.numerador * otro.getDenominador();

        this.denominador = nuevoDenominador;
        this.numerador = nuevoNumerador;
        
        simplificar();
    }
    
    private void simplificar(){
    if(this.numerador == 0){
        //el 0 lo dejamos siempre como 0/1
        this.denominador = 1;
        return;
    }

    //revisa los signos del numerador, denominador y los compara, si son distintos retorna true sino retorna false
    boolean esNegativo = (this.numerador < 0) != (this.denominador < 0);

    //math.abs toma el valor absoluto de un numero para que se pueda calcular el mcd
    //esto modifica el signo del numerador y denominador(luego se devuelve ese signo)
    int a = Math.abs(this.numerador);
    int b = Math.abs(this.denominador);

    //prueba dividir por 2, cada vez que un numero
    //divide justo a los dos (sin resto), dividimos y probamos ese mismo numero
    //de nuevo. Si no divide, pasamos al siguiente. Repetimos hasta que no entre mas.
    int divisor = 2;
    while(divisor <= a && divisor <= b){
        if(a % divisor == 0 && b % divisor == 0){
            a = a / divisor;
            b = b / divisor;
        } else {
            divisor = divisor + 1;
        }
    }

    //devuelve el signo que tenia al inicio la fraccion
    if(esNegativo){
        this.numerador = -a;
    } else {
        this.numerador = a;
    }
    this.denominador = b;
 }

    //Setters 

    
    public int getNumerador(){
        return numerador;
    }

    public int getDenominador(){
        return denominador;
    }


}
