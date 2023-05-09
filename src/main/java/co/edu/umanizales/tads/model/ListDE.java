package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.exception.ListDeException;
import co.edu.umanizales.tads.exception.ListSEException;
import lombok.Data;


@Data
public class ListDE {
    private NodeDE head;
    private int size;
    public void addPet(Pet pet) throws ListDeException {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                if(temp.getData().getOwnernumb().equals(pet.getOwnernumb())){
                    throw new ListDeException("400","Ya existe una mascota con ese codigo");
                }
                temp.getNext();
            }
            if(temp.getData().getOwnernumb().equals(pet.getOwnernumb())){
                throw new ListDeException("400","Ya existe una mascota con ese codigo");
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
            this.head.setPrevious(newNode);
        }
        this.head = newNode;
        size++;
    }
    public void deletePet(String phone) throws ListDeException {
        NodeDE empt = null;
        NodeDE temp = head;

        while (temp != null && !temp.getData().getOwnernumb().equals(phone)) {
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
    public void RemovePetInPosition(String phone, int pos1) throws ListSEException {
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
            //------------------eliminamos el elemento encontrado------------------------//
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




    public void addPetInPos(Pet pet, int pos2) {
        NodeDE temp = head;
        NodeDE newNode = new NodeDE(pet);

        if (pos2 < 0 || pos2 >= size)
            addPet(pet);
        if (pos2 == 0) {
            addPetToBeginning(pet);

        }
        for (int i = 0; temp.getNext() != null && i < pos2 - 1; i++) {
            temp = temp.getNext();
        }
        newNode.setNext(temp.getNext());
        temp.setNext(newNode);

        if (newNode.getNext() !=null){
            newNode.getNext().setPrevious(newNode);
        }
        newNode.setPrevious(temp);
        size++;

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

    public void orderByGender()  {
        ListDE listDE1 = new ListDE();
        int sum = 0;
        NodeDE temp = head;
        if (head == null) {
            throw new ListDeException("400","No hay mascotas en la lista");
        } else {
            while (temp != null) {
                if (temp.getData().getGender() == 'F') {
                    listDE1.addPetToBeginning(temp.getData());

                }
                temp = temp.getNext();
            }

        }
        temp = head;
        sum = listDE1.getSize();
        while (temp != null) {
            if (temp.getData().getGender() == 'M') {
                listDE1.addPetInPos(temp.getData(), sum);
                temp = temp.getNext();
                sum = sum + 2;
            } else {
                temp = temp.getNext();
            }
            this.head = listDE1.getHead();
        }
    }
    public void losePositions(String phone, int lose) {
        NodeDE temp = head;
        int sum = 0;
        ListDE listDE1 = new ListDE();
        boolean found = false;
        boolean added = false;
        while (temp != null) {
            if (temp.getData().getOwnernumb().equals(phone)) {
                if (added) {

                    listDE1.addPet(temp.getData());
                } else {

                    listDE1.addPet(temp.getData());
                    added = true;
                }
                found = true;
            } else {
                listDE1.addPet(temp.getData());
            }
            temp = temp.getNext();
        }
        if (!found) {
            throw new ListDeException("400","El id buscado no se encuentra en la lista");
        }
        sum = lose + getPosById(phone);
        if (sum>size){
            addPet(getPetByPhone(phone));
        } else {
            if (!listDE1.getPetByPos(sum).getOwnernumb().equals(phone)) {
                listDE1.addPetInPos(getPetByPhone(phone), sum);
            }
            this.head = listDE1.getHead();
        }
    }

    public int getPosById(String id) {
        NodeDE temp = this.head;
        int acum = 0;
        if (head != null) {
            while (temp != null && !temp.getData().getOwnernumb().equals(id)) {
                acum = acum + 1;
                temp = temp.getNext();

            }
        }
        return acum;
    }
    public Pet getPetByPos(int pos) {
        if (pos < 0 || pos >= size) {
            return null;
        } else {
            NodeDE temp = head;
            for (int i = 0; i < pos; i++) {
                temp = temp.getNext();
            }
            return temp.getData();
        }
    }
    public Pet getPetByPhone(String id) {
        NodeDE temp = this.head;
        if (head != null) {
            while (temp!=null){
                temp = temp.getPrevious();
            }
            while (temp != null && !temp.getData().getOwnernumb().equals(id)) {
             temp = temp.getNext();

            }

        }
      Pet pet = new Pet(temp.getData().getAge(), temp.getData().getName(),
              temp.getData().getGender(), temp.getData().getOwnernumb(),
              temp.getData().getBreed(), temp.getData().getLocation());
        return pet;
    }



    public void changeExtremes() {
        NodeDE temp = this.head;
        if (this.head != null) {
            while (temp!=null){
                temp = temp.getPrevious();
            }Pet copy = temp.getData();
            if (this.head.getNext() != null && this.head.getPrevious() != null) {
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                }//temp is in the last data
                //creation of a copy
                this.head.setData(temp.getData());//to use the data of the temp that is in the last data
                temp.setData(copy);//the head data into the last
            }
        }else{
            throw new ListDeException("400","No hay suficientes datos en la lista");
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
                throw new ListDeException("404","No hay mascotas con la edad indicada");
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



    public void winPos(String phone, int earn) throws ListDeException{
        NodeDE temp = head;
        int sum = 0;
        ListDE listDE1 = new ListDE();
        if (head != null) {
            while (temp != null) {
                if (!temp.getData().getOwnernumb().equals(phone)) {
                    listDE1.addPet(temp.getData());
                    temp = temp.getNext();
                } else {
                    temp = temp.getNext();
                }
            }
        }else{
            throw new ListDeException("400","No hay datos en la lista");
        }
        sum = getPosById(phone) - earn;
        listDE1.addPetInPos(getPetByPhone(phone), sum);
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
    public int verifyPhone(PetDTO petDTO) {
        NodeDE temp = this.head;
        boolean found = false;
        while (temp != null) {
            if (temp.getData().getOwnernumb().equals(petDTO.getOwnernumb())) {
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
}