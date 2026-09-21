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
        
        if(nuevoNumerador % nuevoDenominador == 0){
            nuevoNumerador = nuevoNumerador / nuevoDenominador;
            nuevoDenominador = nuevoDenominador / nuevoDenominador;
        }

        this.numerador = nuevoNumerador;
        this.denominador = nuevoDenominador;
    }

    //para multiplicar fracciones lo hacemos "Derecho"
    @Override
    public void mult(Racional r){
        NumeroRacional otro = (NumeroRacional) r;
        this.numerador = this.numerador * otro.getNumerador();
        this.denominador = this.denominador * otro.getDenominador();
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
    }


    //Setters 

    
    public int getNumerador(){
        return numerador;
    }

    public int getDenominador(){
        return denominador;
    }


}
