package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data


public class listaSe {
    private Node head;
    /*en esta es para adiccionar un niño al final de la lista
    nos dan de entrada un NIÑO
    y podemos realizar dos acciones (si y no )
    si hay datos de un niño
      si
    es necesario de un ayudante(temp) y le digo que se posicione en la cabeza que y que mienstra en el brazo
    del niño haya algo pase al siguiente hasta encontrar por asi decirlo uno sin nada y al estar en el
    ultimo meto el niño al costal(Node)(nuevo costal o newNode) y le digo al ultimo que tome el nuevo costal
    NO
    metemos al niño al costal(Node) y este sera la cabeza
    */

    public void addKid(kid kid) {
    if (head !=null){
        Node temp = head;
        while (temp.getNext() !=null);
        {
            temp = temp.getNext();
        }
        //parado ahora desde el ultimo
        Node newNode = new Node(kid);
        temp.setNext(newNode);
    }
    else {
    head = new Node(kid);
       }
    }
    /*
    ahora el algoritmo para agregar al inico
    si hay datos

    si
    le digo a nuevo costal que tome con su brazo a la cabeza y la cabeza es iguala a nuevo costal
    lo que hacemos es que el nuevo costal se posicione en la cebaza este al estar en esta posicion pasa a ser la cebaza

    no
    vuelvo a meter al niño al costal y lo asigono al principio como cabeza

     */
    public void addStard(kid kid) {
        if (head !=null){ // si la cabeza esta en o
            Node newNode = new Node(kid); //se hace un nuevo nodo y meto al niño
            newNode.setNext(head);//posiciono el niño en la cabeza
            head = newNode; // a lo ultimo decimos que el nodo queda como la cabeza al principio
        }
   else{
            head = new Node(kid);
        }
    }

    public void delete(listaSe<kid> listaSe, int id){
        for (Kid kid : listaSe) {
            if (kid.getId() == id) {
                listaSe.remove(kid);
                System.out.println("Niño eliminado");
                return;
            }
        }

    }

    public void deleteKid(String id){
        Node temp = head;
        while (!temp.getNext () .getData() . getId() . equals (1d)){
            temp= temp.getNext);
        }
            temp.getNext ().setData(null);
    }

    public void addInPos(Kid kid, int pos) {
        Node temp = head;
        for (int 1 = 0; 1 < pos ;
        i++){
            temp = temp.getNext();
        }
        Node newNode = new Node(kid);
        temp.setNext(newNode);
    }
}
