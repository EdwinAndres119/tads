package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.exception.ListDeException;
import lombok.Data;

import java.util.ArrayList;


@Data
public class ListDE {
    private NodeDE head;
    private int size;

    /*
METODO AÑADIR UNA MASCOTA:
en este metodo es necesario los datos necesarios para completar por
Este metodo utiliza una función llamada añadir mascota que agrega una nueva mascota a una lista de mascotas
Si la lista ya tiene una mascota con el mismo número de dueño que la nueva mascota que se está agregando, entonces la función lanzará una excepción.
En caso contrario, la nueva mascota se agregará a la lista y se incrementará el tamaño de la listaDE.
Lo que significa que cada nodo en la lista tiene referencias tanto al nodo anterior como al siguiente en la lista.
 */
    public void addPet(Pet pet) throws ListDeException {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                if(temp.getData().getIdentification().equals(pet.getIdentification())){
                    throw new ListDeException("400","Ya existe una mascota con este codigo");
                }
                temp = temp.getNext();
            }
            if(temp.getData().getIdentification().equals(pet.getIdentification())){
                throw new ListDeException("400","Ya existe una mascota con este codigo");
            }
            NodeDE newNode = new NodeDE(pet);
            temp.setNext(newNode);
            temp.setPrevious(temp);
        } else {
            this.head = new NodeDE(pet);
        }
        size++;
    }

    public void addPetToBeginning(Pet pet) {
        NodeDE newNode = new NodeDE(pet);
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                if (temp.getData().getIdentification().equals(pet.getIdentification())) {
                    throw new ListDeException("400", "Ya existe una mascota con ese codigo");
                }
                temp = temp.getNext();
            }
            if (temp.getData().getIdentification().equals(pet.getIdentification())) {
                throw new ListDeException("400", "Ya existe una mascota con ese codigo");
            }
            this.head.setPrevious(newNode);
            newNode.setNext(this.head);
        }
        this.head = newNode;

        size++;
    }
    /*
 deletePet que busca y elimina una mascota de una lista doblemente enlazada de mascotas.La búsqueda se debe hacer en
 base al número de teléfono del propietario de la mascota. Si se encuentra una mascota
 con el número de teléfono especificado, se elimina el nodo que la contiene de la lista.

Para poder hacer la búsqueda, debo utiliza un bucle que recorre la lista, comparando el número
de teléfono de cada mascota con el número de teléfono que nos da el usuario que quiere elimianr .
Si se encuentra la mascota,
el nodo que la contiene a esta mascota se elimina de la lista. Si no se encuentra la mascota,
se lanza una excepción
que indica que la mascota no se encuentra en la lista.

cuando se elimina del nodo debo hacer una actualizando de las referencias de los nodos vecinos en la lista.
Si el nodo que contiene la mascota a eliminar es la cabeza en la lista, se actualiza, Si no es el primer nodo,
se actualiza con el del nodo anterior al nodo pero si es el
siguiente al nodo que se está eliminando, para que la lista doblemente, siga siendo válida. Además,
si nuestro nodo siguiente al nodo que se está eliminando existe, se actualiza su referencia al nodo anterior para
mantener la lista doble.

Finalmente, después de eliminar el nodo, se disminuye el tamaño de la lista.

     */
    public void deletePetByage(byte age) {
        NodeDE temp = head;
        ListDE listDE1 = new ListDE();
        boolean found = false;
        if (this.head !=null){
            while (temp !=null) {
                if(temp.getData().getAge()  != age ){
                    listDE1.addPetToBeginning(temp.getData());
                }else{
                    found = true;
                }
                temp = temp.getNext();
            }
            if(!found) {
                throw new ListDeException("404", "no hay datos con esa edad indicada");
            }
            this.head = listDE1.getHead();
        }else{
            throw new ListDeException("404", "no hay datos suficientes");
        }
    }
    public void deletePet(String id) throws ListDeException {
        NodeDE empt = null;
        NodeDE temp = head;

        while (temp != null && !temp.getData().getIdentification().equals(id)) {
            empt = temp;
            temp = temp.getNext();
        }

        if (temp == null) {
            throw new ListDeException("400","la mascotas que se busca para eliminar no existe");
        }

        if (empt == null) {
            head = temp.getNext();
        } else {
            empt.setNext(temp.getNext());
        }if (temp.getNext() !=null){
            temp.getNext().setPrevious(empt);
        }
        size--;
    }
    /*
    METODO PARA ELIMINAR EN SITIO DE LA LISTADE
    en este metodo necesito al ayudante de que me recorra la listade que este se pone principalmente en la cabeza de
    la lista (tambien necesito un parametro  que me identifique al objeto que debo eliminar principalmente esta puede ser el ID de la
    mascota)
    esta hacion se inicia mientras las que el temporal se diferente de null tambien debo tener en cuentas cuando este recorre la lista y
    debo tener en cuenta que si el ayudante llega al final de la lista no de error
    si encontro la id y coincide con la id requqerida para la esto se deben actulizar los datos previos y siguientes de la lista para
    asi no perder los datos
    tambien debo tener en cuenta que si el nodo a eliminar es la cabeza se debe actualizar los enlaces de este primer dato para que asi
    el siguiente dato quede como la cabeza y no perder la lista
    pero si se cumple los datos de la id con el que encontro el temp debe actualizar los enlaces que tiene este con el previus y next
    y para realizar este metodo puedo reutilizar este metodo de deletePet pero agregando el dato que se necesita eliminar que nos los daria la
    id necesaria para esto y asi no perder los datos ya de la listaDE
    -Debo tener en cuenta si la id o la mascota que se quiere eliminar es cabeza debe se debe actualizar y colocar una nueva cabeza el dato siguiente
    -Debo tener tambien en cuenta que si el dato que se quiere eliminar es del final
    Creo que en este metodo tambien puedo combinar dos metodos tanto el de deletePet que tienee en cuenta si es cabeza el dato al eliminar
    y si esta tambien esta al final y el otro que le puedo utilizar es de addPetInPos ya que en esta nos la una posicion determinada

     RemovePetInPosition
     */
    public void RemovePetInPosition(String id, int pos1) throws ListDeException {
        if (pos1 < 0 || pos1 >= size){

        }
if (this.head !=null){
    NodeDE temp = this.head;
    if(pos1 == 0){
     if(temp.getNext() != null){
         this.head = head.getNext();
         this.head.setPrevious(null);
     }else{
         setHead(null);
     }
    }else{
        for (int i = 0; temp != null && i < pos1 - 1; i++) {
            temp = temp.getNext();
    }
        if (temp != null) {
            if (temp.getNext() != null) {
                temp.getPrevious().setNext(temp.getNext());
                temp.getNext().setPrevious(temp.getPrevious());
            } else {
                temp.getPrevious().setNext(null);
            }
        } else {
            throw new ListDeException("404", "La posición indicada no existe en la lista");
        }
    }
    size--;
} else {
    throw new ListDeException("404", "No hay datos en la lista, no se pueden eliminar nodos");
   }
 }
/*
ALGORITMO ADDPETINPOS:
addPetInPos que agrega una nueva mascota en una posición específica en una
lista doblemente enlazada de mascotas. La posición deseada se especifica mediante un parámetro pos2.

primero creo un nuevo nodo con la mascota que se va a agregar y luego busca el nodo en la
posición deseada en la lista, para luego colocar el nuevo nodo en ese lugar. Si la posición especificada
es menor que cero o mayor o igual que el tamaño de la lista, la nueva mascota se  debe agregará al final de la lista.
 Si la posición especificada es cero, la nueva mascota se agregará al principio de la lista.

 despues que se ha insertado el nuevo nodo en la lista, se actualizan las referencias de los nodos vecinos
 para asegurar que la lista doblemente enlazada se mantenga y al final, se incrementa el tamaño de la lista.
 */


public void addPetInPos(Pet pet, int pos2) throws ListDeException {
    NodeDE temp = this.head;
    NodeDE newNode = new NodeDE(pet);
    if (this.head != null) {
        if (verifyID(pet) == 0) {
            if (pos2 > size) {
                addPet(pet);
            } else if (pos2 < 0 || pos2 == 0) {
                addPetToBeginning(pet);
            } else {
                for (int i = 0; i < pos2 - 1 && temp.getNext() != null; i++) {
                    temp = temp.getNext();
                }

                temp.getNext().setPrevious(newNode);
                newNode.setNext(temp.getNext());
                temp.setNext(newNode);
                newNode.setPrevious(temp);

            }
        } else {
            throw new ListDeException("400", "ya existe esta mascota");
        }
    }

}

    public int getCounPetLocCode(String code){
        int count =0;
        if( this.head!=null) {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }else{
            throw new ListDeException("400","No hay mascotas en la lista");
        }
        return count;
    }

    public int getCountPetByLocCodeMale(String code){
        int male =0;
        if( this.head!=null) {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code) && temp.getData().getGender() == 'M') {
                    male++;
                }
                temp = temp.getNext();
            }
        }else{
            throw new ListDeException("400","no hay mascotas en la lista ");
        }
        return male;
    }
    public int getCountPetByLocCodeFemale(String code){
        int female =0;
        if( this.head!=null){
            NodeDE temp = this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().equals(code)&&temp.getData().getGender() == 'F'){
                    female++;
                }
                temp = temp.getNext();
            }
        }else{
            throw new ListDeException("400","no hay mascotas en la lista ");
        }
        return female;
    }

    public void orderByGender() throws ListDeException {
        ListDE listDE1 = new ListDE();
        int sum = 0;
        NodeDE temp = this.head;
        if (head == null) {
            throw new ListDeException("404", "no hay datos en la lista");
        } else {
            while (temp != null) {
                if (temp.getData().getGender() == 'F') {
                    listDE1.addPetToBeginning(temp.getData());

                }
                temp = temp.getNext();
            }
            temp = head;
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    listDE1.addPetInPos(temp.getData(), sum);
                    temp = temp.getNext();
                    sum = sum + 2;
                } else {
                    temp = temp.getNext();

                }
            }
            this.head = listDE1.getHead();
        }
    }
    public void losePositions(String id, int lose)  {
        NodeDE temp = this.head;
        int sum = 0;
        ListDE listDE1 = new ListDE();
        if (head != null) {
            while (temp != null) {
                if (!temp.getData().getIdentification().equals(id)) {
                    listDE1.addPet(temp.getData());
                    temp = temp.getNext();
                } else {
                    temp = temp.getNext();
                }
            }
        } else {
            throw new ListDeException("404", "no hay datos en la lista");
        }
        sum = getPosById(id)+lose;
        listDE1.addInPosValidations(getPetByid(id), sum);
        this.head = listDE1.getHead();
    }
    public void addInPosValidations(Pet pet, int pos2) throws ListDeException {
        NodeDE temp = head;
        NodeDE newNode = new NodeDE(pet);
        int listLength = getLength();
        if (pos2 < 0 || pos2 >= listLength)
            addPet(pet);
        if (pos2 == 0) {
            newNode.setNext(head);
            this.head.setPrevious(newNode);
            head = newNode;


        } else {
            for (int i = 0; temp.getNext() != null && i < pos2 - 1; i++) {
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            newNode.setPrevious(temp);
            temp.setNext(newNode);
            temp.getNext().setPrevious(newNode);
        }
    }

    public int getPosById(String id) {
        NodeDE temp = this.head;
        int acum = 0;
        if (head != null) {
            while (temp != null && !temp.getData().getIdentification().equals(id)) {
                acum = acum + 1;
                temp = temp.getNext();

            }
        }
        return acum;
    }
    public int getLength() {
        int total = 0;
        NodeDE temp = head;
        while (temp != null) {
            total++;
            temp = temp.getNext();
        }
        return total;
    }

    public Pet getPetByid(String identification) {
        NodeDE temp = head;
        if (head != null) {
            while (temp != null) {
                temp = temp.getPrevious();
            }
            while (temp != null && !temp.getData().getIdentification().equals(identification)) {
                temp.getNext();
            }
        }

        Pet pet = new Pet(temp.getData().getAge(),
                temp.getData().getName(), temp.getData().getIdentification(), temp.getData().getGender(), temp.getData().getBreed(),
                temp.getData().getLocation());
        return pet;
    }



    public void changeExtremes() {
        NodeDE temp = this.head;
        if (this.head != null) {
            while (temp != null) {
                temp = temp.getPrevious();
            }
            Pet copy = temp.getData();
            if (this.head.getNext() != null && this.head.getPrevious() != null) {
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                }
                this.head.setData(temp.getData());
                temp.setData(copy);
            }
        } else {
            throw new ListDeException("400", "No hay suficientes datos en la lista");
        }

    }
    public void invert() {
        NodeDE temp = this.head;
        ListDE listDE2 = new ListDE();
        if (this.head != null) {
            while (temp != null) {
                listDE2.addPetToBeginning(temp.getData());
                temp = temp.getNext();
            }
            this.head = listDE2.getHead();
        }else{
            throw new ListDeException("400","No hay suficientes datos en la lista");
        }
    }
    public void putPetBeginning() {
        NodeDE temp = this.head;
        ListDE listDE1 = new ListDE();
        if (this.head != null) {
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    listDE1.addPetToBeginning(temp.getData());

                } else if (temp.getData().getGender() == 'F') {
                    listDE1.addPet(temp.getData());
                }
                temp = temp.getNext();
            }
            this.head = listDE1.getHead();
        } else{
        throw new ListDeException("400","No hay suficientes datos en la lista");
        }
    }

    //method to delete a pet with a specified age-----------------------------------------------
    public void deleteByAge(byte age) throws ListDeException{
        NodeDE temp = this.head;
        ListDE listDE1 = new ListDE();
        boolean found = false;
        if (this.head != null) {
            while (temp != null) {
                if (temp.getData().getAge() != age) {
                    listDE1.addPetToBeginning(temp.getData());
                }else{
                    found = true;
                }
                temp = temp.getNext();
            }
            if (!found){
                throw new ListDeException("404","No hay mascotas con esta edad");
            }
            this.head = listDE1.getHead();
        }else{
            throw new ListDeException("400","No hay suficientes datos en la lista");
        }
    }


    public double getHalfAgeDog()throws ListDeException {
        double averageAge = 0;
        NodeDE temp = this.head;
        if (this.head != null) {
            while (temp != null) {
                averageAge = averageAge + temp.getData().getAge();
                temp = temp.getNext();
            }
            averageAge = averageAge / size;
            return averageAge;
        }else {
            throw new ListDeException("400","No hay datos en la lista");
        }
    }



    public void winPos(String id, int earn) throws ListDeException{
        NodeDE temp = head;
        int sum = 0;
        ListDE listDE1 = new ListDE();
        if (head != null) {
            while (temp != null) {
                if (!temp.getData().getIdentification().equals(id)) {
                    listDE1.addPet(temp.getData());
                    temp = temp.getNext();
                } else {
                    temp = temp.getNext();
                }
            }
        }else{
            throw new ListDeException("400","No hay datos en la lista");
        }
        sum = getPosById(id) - earn;
        listDE1.addPetInPos(getPetByid(id), sum);
        this.head = listDE1.getHead();
    }

    public String ReportByage() {
        int quantity1 = 0;
        int quantity2 = 0;
        int quantity3 = 0;
        int quantity4 = 0;
        int quantity5 = 0;
        NodeDE temp = this.head;
        if (this.head != null) {
            while (temp != null) {
                if (temp.getData().getAge() >= 0 && temp.getData().getAge() <= 3) {
                    quantity1++;
                } else if (temp.getData().getAge() > 3 && temp.getData().getAge() <= 6) {
                    quantity2++;
                } else if (temp.getData().getAge() > 6 && temp.getData().getAge() <= 9) {
                    quantity3++;
                } else if (temp.getData().getAge() > 9 && temp.getData().getAge() <= 12) {
                    quantity4++;
                } else if (temp.getData().getAge() > 12 && temp.getData().getAge() <= 15) {
                    quantity5++;
                }
                temp = temp.getNext();
            }
        }else{
            throw new ListDeException("400","no hay datos en la lista");

        }

        return " niños entre 0-3 años:" + quantity1 +
                " niños entre 4-6 años:" + quantity2 +
                " niños entre 7-9 años:" + quantity3 +
                " niños entre 10-12 años:" + quantity4 +
                " niños entre 13-15 años:" + quantity5;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        NodeDE temp = this.head;
        sb.append("[");
        while (temp != null){
            sb.append(temp.getData().toString());
            temp = temp.getNext();
            if (temp != null){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();

    }
    public int verifyID(Pet pet) {
        NodeDE temp = this.head;
        boolean found = false;
        while (temp != null) {
            if (temp.getData().getIdentification().equals(pet.getIdentification())) {
                found = true;
                break;
            }
            temp = temp.getNext();
        }
        return found ? 1 : 0;
    }

    public void sendPetsToEndByChar(char user) throws ListDeException{
        ListDE listDE1 = new ListDE();
        NodeDE temp = this.head;
        if (this.head != null) {
            while (temp != null) {
                if (temp.getData().getName().charAt(0) != user) {
                    listDE1.addPetToBeginning(temp.getData());
                } else {
                    listDE1.addPet(temp.getData());
                }
                temp = temp.getNext();
            }
        }else{
            throw new ListDeException("400","no hay datos en la lista");
        }
        this.head = listDE1.getHead();
    }
    public ArrayList<Pet> showList() {
        ArrayList<Pet> pets = new ArrayList<>();
        if (this.head != null) {
            NodeDE temp = this.head;

            do {
                pets.add(temp.getData());
                temp = temp.getNext();
            } while (temp != null);
        }
        return pets;

    }
}