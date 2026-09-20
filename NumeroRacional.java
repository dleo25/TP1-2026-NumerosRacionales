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
        return;   
    }

    @Override
    public void mult(Racional r){
        NumeroRacional otro = (NumeroRacional) r;
        this.numerador = this.numerador * otro.getNumerador();
        this.denominador = this.denominador * otro.getDenominador();
    }

    @Override
    public void div(Racional r){
        return;
    }

    public int getNumerador(){
        return numerador;
    }

    public int getDenominador(){
        return denominador;
    }


}
