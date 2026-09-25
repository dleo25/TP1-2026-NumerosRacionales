public class PilaDoblementeEnlazada<T> implements Pila<T> {

    private NodoPila<T> inicio;
    private NodoPila<T> fin;

    //el constructor arranca con la pila completamente vacia, sin inicio ni fin
    PilaDoblementeEnlazada() {
        this.inicio = null;
        this.fin = null;
    }

    //si la lista no tiene ni inicio ni final significa que esta vacia
    @Override
    public boolean esVacia() {
        return this.inicio == null && this.fin == null;
    }

    //si es vacia no hay tope para devolver. sino devuelve el Item que esta en fin
    @Override
    public T tope(){
        if(esVacia()) throw new IllegalStateException("Pila vacia");
        else return fin.getItem();

    }

    // si el elemento es null tira error
    // si la pila esta vacia crea el primer nodo y tanto inicio como fin apuntan ahi
    // si ya tiene elementos, engancha el nuevo nodo al final y actualiza el puntero fin 
    @Override
    public void apilar(T elem){
        if(elem == null) throw new IllegalStateException("Elemento vacio");
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

    // si la pila esta vacia no se puede sacar nada
    // guarda el dato del ultimo nodo (fin) para devolverlo al terminar
    // si era el unico elemento que quedaba, resetea inicio y fin a null
    // si habia mas de uno, mueve el puntero fin uno hacia atras y desconecta el ultimo
    @Override
    public T desapilar(){
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

    // se para en el tope (fin) y va recorriendo hacia atras hasta el inicio (null)
    // imprime cada elemento en pantalla respetando el orden LIFO
    @Override
    public void imprimir() {
        NodoPila<T> actual = this.fin;
        while (actual != null){
            System.out.println(actual.getItem());
            actual = actual.getPrev();
        }
    }

}