public class PilaDoblementeEnlazada<T> implements Pila<T> {

    private NodoPila<T> inicio;
    private NodoPila<T> fin;

    PilaDoblementeEnlazada() {
        this.inicio = null;
        this.fin = null;
    }

    @Override
    public boolean esVacia() {
        return this.inicio == null && this.fin == null;
    }

    @Override
    public T tope() throws IllegalStateException {
        if(esVacia()) throw new IllegalStateException("Pila vacia");
        else return fin.getItem();

    }

    @Override
    public void apilar(T elem) throws IllegalStateException {
        if(esVacia()){
            NodoPila<T> nuevo = new NodoPila<>(null, elem, null);
            this.inicio = nuevo;
            this.fin = nuevo;
        } else {
            NodoPila<T> nuevo = new NodoPila<>(this.fin, elem, null);
            this.fin.setNext(nuevo);
            this.fin = nuevo;
        }
    }

    @Override
    public T desapilar() throws IllegalStateException {
        if(esVacia()) throw new IllegalStateException("Pila vacia");
        else{
            T dato = this.fin.getItem();

                if(this.inicio == this.fin){
                    this.inicio = null;
                    this.fin = null;
                }
                else {
                    this.fin = this.fin.getPrev();
                    this.fin.setNext(null);
                }
            return dato;
        }
    }

    @Override
    public void imprimir() {
        // TODO Implementar este método y eliminar la línea siguiente
        throw new UnsupportedOperationException("Unimplemented method 'imprimir'");
    }

}