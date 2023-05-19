package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.exception.ListDeException;
import lombok.Data;

@Data
public class LIstDECircle {
    private NodeDE head;
    private int size;
    /*
    metodo para agregar al final de la lista, primeor tengo que preguntar si hay datos, si no hay datos le digo a un nuevo nodo que es la nueva cabeza, le digo a los brazos le apunten a si mismo para ya asi poder empezar a crear la listaDEcircular
    si hay datos
    tengo que hacerr la verificacion de que la mascota no existe tomando como una referencia la cabeza ya que esta es el primer y su ultimo dato de la lista
    si la mascota no existe en nuestra lista, entonces meto la mascota en un nuevo nodo
    a esta nuevo nodo le digo que ponga su previo en el anterior de la cabeza
    a cabeza le digo que llame a su anterior, que coja el siguiete y lo ponga en el nuevo nodo
    le digo a nuestro nuevo nodo que coloque su previo en nuevo nodo y por ultimo que este ya me queda añadido al final
     */
    public void addPetToEnd(Pet pet) throws ListDeException {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != head) {
                if (temp.getData().getIdentification().equals(pet.getIdentification())) {
                    throw new ListDeException("400", "Ya existe una mascota con ese codigo");
                }
                temp = temp.getNext();
            }
            if (temp.getData().getIdentification().equals(pet.getIdentification())) {
                throw new ListDeException("400", "Ya existe una mascota con ese codigo");
            }

            NodeDE newNode = new NodeDE(pet);
            newNode.setPrevious(this.head.getPrevious());
            this.head.getPrevious().setNext(newNode);
            newNode.setNext(this.head);
            this.head.setPrevious(newNode);
        } else {
            this.head = new NodeDE(pet);
            this.head.setPrevious(head);
            this.head.setNext(head);
        }
        size++;
    }
        /*
para agregar un pet al inicio, si hay datos llamo al metodo agrgar al final de lista que tengo arriba que es el que ya tengo con validacion ya hecha
si tengo datos en la lista, busco si el dato que se quiere agregar ya esta en la lista, si no es asi tengo que decirle al nezt del nuevo nodo va para el siguiente a la cabeza
el precio de nuevo nodo va para la cabeza, el previo del siguiente a la cabeza va para nuevo nodo
el previo del siguiente a la cabeza va para un nuevo nodo y el next de la cabeza va para nuevo nodo
finalmente le digo que la nueva cabeza en un nuevo nodo
     */

    public void addPetToBeginning(Pet pet) {
        NodeDE newNode = new NodeDE(pet);
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != head) {
                if (temp.getData().getIdentification().equals(pet.getIdentification())) {
                    throw new ListDeException("400", "Ya existe una mascota con ese codigo");
                }
                temp = temp.getNext();
            }
            if (temp.getData().getIdentification().equals(pet.getIdentification())) {
                throw new ListDeException("400", "Ya existe una mascota con ese codigo");
            }
            newNode.setNext(this.head.getNext());
            newNode.setPrevious(this.head);
            this.head.getNext().setPrevious(newNode);
            this.head.setNext(newNode);
            this.head = newNode;
        } else {
            addPetToEnd(pet);
        }
        size++;
    }

}
