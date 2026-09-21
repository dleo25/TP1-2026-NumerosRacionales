public class NumeroRacional implements Racional{


    private int numerador;
    private int denominador;
    
    public NumeroRacional(int numerador, int denominador){
        this.numerador = numerador;
        this.denominador = denominador;
        if (!repOk()) throw new IllegalArgumentException("El denominador no puede ser 0");
    }

    @Override
    public boolean repOk(){
        if(denominador == 0) return false;
        else return true;
    }

    @Override
    public String toString(){
        return this.numerador + "/" + denominador;
    }

    @Override
    public void neg(){
        this.numerador = this.numerador * -1;
    }

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

    @Override
    public void mult(Racional r){
        NumeroRacional otro = (NumeroRacional) r;
        this.numerador = this.numerador * otro.getNumerador();
        this.denominador = this.denominador * otro.getDenominador();
    }

    @Override
    public void div(Racional r){
        NumeroRacional otro = (NumeroRacional) r;
        
        if(otro.getNumerador() == 0) throw new IllegalArgumentException("No se puede dividir por cero");

        int nuevoDenominador = this.denominador * otro.getNumerador();
        int nuevoNumerador = this.numerador * otro.getDenominador();

        this.denominador = nuevoDenominador;
        this.numerador = nuevoNumerador;
    }

    public int getNumerador(){
        return numerador;
    }

    public int getDenominador(){
        return denominador;
    }


}
