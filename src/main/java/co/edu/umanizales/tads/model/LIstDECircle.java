package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.exception.ListDeException;
import lombok.Data;

import java.util.ArrayList;

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
    /*
    Metodo para añadir una mascota en posicion
    se crea un ayudante y un nuevo nodo donde en este va ir una mascota
    entonces
    si no hay datos entonces se llama a nuestrto metodo agregar al final par que este haga la vlaidacion
    si la posicion es igual a 0 entonces necesito el metodo agrgar al inicio que tiene la validacion
    si fuera otro caso puedo iniciar la posicion en 1 que es la cabeza

    para girar por la lista hacia la izquierda se hace un ciclo que recorra la lista
    hacia la posicion en la que se quiere agregar la mascota y (se debe
    tener en cuenta que estamos contando a cabeza como un primer dato
     y esta en la posicion 0).
     ahora el previus del siguiente va a apuntar al nuevo nodo y el siguiente ea
     del temporal va para el nuevo nodo
     si el valor es negativo, se debe girara por el lado izquierda y se trasforma en un valor
     positivo para hacer el mismo ciclo pero diciendole al ayudante que se vaya para
     el dato anteriror y cuando todo el ciclo termine

     el previus del nuevo nodo se va para el anterior de temporal
     el siguiente del nuevo nodo se va para el temporla
     el previus de el temporal se va para el nuwvo nodo
     y el siguiente del dato anterior del temporal se debe ir para el nuevo nodo.



     */
    public void addInPos(Pet pet, int pos2) {
        NodeDE temp = head;
        NodeDE newNode = new NodeDE(pet);
        if (this.head == null) {
            addPetToEnd(pet);
            return;
        }
        if (pos2 == 0) {
            addPetToBeginning(pet);
        }
        if (pos2 > 0) {
            for (int i = 1; i < pos2; i++) {
                temp = temp.getNext();
            }
            newNode.setPrevious(temp);
            newNode.setNext(temp.getNext());
            temp.getNext().setPrevious(newNode);
            temp.setNext(newNode);
        } else if (pos2 < 0) {
            int pos = pos2 * (-1);
            for (int i = 1; i < pos2; i++) {
                temp = temp.getPrevious();
            }
            newNode.setPrevious(temp.getPrevious());
            newNode.setNext(temp);
            temp.setPrevious(newNode);
            temp.getPrevious().setNext(newNode);
        }
        size++;

    }

    /*
    metodo para bañar perros
    si hya datos debo retornar que no hay datos
    si hya datos creo una variable de tipo entero que tenga numeros 0 a 1000
    cree un ayudante en cabeza
    si la direccion que el usuario es un i entonces itere por la izquierda de la lista
    hasta que llegue al valor aleatorio cuando llegue a esa posicion se debe preguntar si
    esta mascota ya esta bañada, si lo esta debe retornar que esta mascota ya fue bañada
    , y si no lo esta entonces se debe bañar y retorne.


    ahora si la direccion que el usuario es de d intere por la derecha hsata llegar al valor
    de el aleatorio,se pregunta si esta mascota ya esta bañada si no es asi entonces retorne una
    exepcion que diga que ets aya fue bañada y si no es asi entonces digale que se
    bañe y retorne este dato  


     */
    public void cleanPet(char direction) {
        if (this.head != null) {
            int val = (int) Math.floor(Math.random() * 1000);
            NodeDE temp = this.head;
            if (direction == 'i' || direction == 'I') {
                for (int i = 0; i < val; i++) {
                    temp = temp.getPrevious();
                }
                if (temp.getData().isShower() == true) {
                    throw new ListDeException("400", "La mascota ya está bañada");
                } else {
                    temp.getData().setShower(true);
                    return;
                }
            } else if (direction == 'd' || direction == 'D') {
                for (int i = 0; i < val; i++) {
                    temp = temp.getNext();
                }
                if (temp.getData().isShower() == true) {
                    throw new ListDeException("400", "La mascota ya está bañada");
                } else {
                    temp.getData().setShower(true);
                    return;
                }
            }else{
                throw new ListDeException("400","Envió mal la variable de dirección");
            }
        }
        throw new ListDeException("404","la lista está vacía");
    }
    public ArrayList<Pet> showList() {
        ArrayList<Pet> pets = new ArrayList<>();
        if (this.head != null) {
            NodeDE temp = this.head;

            do {
                pets.add(temp.getData());
                temp = temp.getNext();
            } while (temp != this.head);
        }
        return pets;

    }

}
