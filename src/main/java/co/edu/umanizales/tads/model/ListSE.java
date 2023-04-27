package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.KidDTO;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ListSE {
    private Node head;
    private int size;


    /*
    Algoritmo de adicionar al final
    Entrada
        un niño
    si hay datos
    si
        llamo a un ayudante y le digo que se posicione en la cabeza
        mientras en el brazo exista algo
            pasese al siguiente
        va estar ubicado en el ùltimo

        meto al niño en un costal (nuevo costal)
        y le digo al ultimo que tome el nuevo costal
    no
        metemos el niño en el costal y ese costal es la cabeza
     */
    public void add(Kid kid) {
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            /// Parado en el último
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        } else {
            head = new Node(kid);
        }
        size++;
    }

    /* Adicionar al inicio
    si hay datos
    si
        meto al niño en un costal (nuevocostal)
        le digo a nuevo costal que tome con su brazo a la cabeza
        cabeza es igual a nuevo costal
    no
        meto el niño en un costal y lo asigno a la cabez
     */
    public void addToStart(Kid kid) {
        if (head != null) {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node(kid);
        }

    }

    public void deleteKidByage(byte age) {
        Node temp = head;
        ListSE listSE1 = new ListSE();
        if (this.head !=null){
            while (!temp.getNext().getData().getIdentification().equals(age)) {
                if(temp.getData().getAge()  != age ){
                    listSE1.addToStart(temp.getData());
                }
                temp = temp.getNext();
            }
          this.head = listSE1.getHead();
         }
    }
    public void deleteKid(String id) {
        Node empt = null;
        Node temp = head;

        while (temp != null && !temp.getData().getIdentification().equals(id)) {
            empt = temp;
            temp = temp.getNext();
        }

        if (temp == null) {
            return;
        }

        if (empt == null) {
            head = temp.getNext();
        } else {
            empt.setNext(temp.getNext());
        }
        size--;
    }

    public void addInPos(Kid kid, int pos) {
        Node temp = head;
        Node newNode = new Node(kid);
        int listLength = getLength();
        if (pos < 0 || pos >= listLength)
            add(kid);
        if (pos == 0) {
            newNode.setNext(head);
            head = newNode;

        } else {
            for (int i = 0; i < pos - 1 && temp.getNext() != null; i++) {
                temp = temp.getNext();

            }
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
        }
    }


    public int getPosByid(String id) {
        Node temp = head;
        int acum = 0;
        if (head != null) {
            while (temp != null && !temp.getData().getIdentification().equals(id)) {
                acum = acum + 1;
                temp = temp.getNext();
            }
        }
        return acum;
    }

    /*el orden por cada genero de los niños primero necesito crea una copia de la lista principal de los niños, necesito poner
    el ayudante en la cabeza
    /*
     */
    public void orderBygender(char gender) {
        ListSE LisSE1 = new ListSE();
        Node temp = head;
        int sum = 0;
        while (temp != null && temp.getNext().getData().getGender() == 'F') {
            LisSE1.addToStart(temp.getData());
        }
        while (temp != null && temp.getNext().getData().getGender() == 'M') {
            sum = sum + 2;
            LisSE1.addInPos(temp.getData(), sum);
            temp.getNext();
        }
        this.head = LisSE1.getHead();
    }
//---------------------------------end the metod order by gender-------------------------------------------------- //


    public void losePosition(String id, int lose) {
        Node temp = head;
        int sum = 0;
        ListSE listSE1 = new ListSE();
        if (head != null) {
            while (temp!=null){
                if(!temp.getData().getIdentification().equals(id)){
                    listSE1.add(temp.getData());
                    temp = temp.getNext();
                }else{
                    temp = temp.getNext();
                }

            }

        }

    }

//---------------------------------end the metod lose positionsp-------------------------------------------------- //



    public Kid getKidById(String id) {
        Node temp = this.head;
        if (head != null) {
            while (temp!= null && !temp.getData().getIdentification().equals(id)) {
              temp=temp.getNext();
            }
        }
  Kid kid = new Kid(temp.getData().getIdentification(), temp.getData().getName(),temp.getData().getAge(),temp.getData().getGender(),temp.getData().getLocation());
    return kid;
    }

//---------------------------------end the metod get kids by identification-------------------------------------------------- //

    public int getLength(){
    int total = 0;
    Node act = head;
    while (act != null ){
        total++;
        act = act.getNext();
    }
    return total;

    }
    //---------------------------------end the metod get length-------------------------------------------------- //
    public void invert() {
        Node temp = this.head;
        ListSE listSE2 = new ListSE();
        if(this.head!= null) {
            while (temp!= null){
                listSE2.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listSE2.getHead();
        }
    }
    //---------------------------------end the metod invert list-------------------------------------------------- //
      public void changeExtremes(){
        if(this.head!=null && this.head.getNext() != null){
            Node temp = this.head;
            while(temp.getNext() !=null){
                temp = temp.getNext();
            }
            Kid copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }
      }

    public int verifyid(KidDTO kid){
      Node temp = this.head;
      boolean found = false;
      while (temp != null){
          if(temp.getData().getIdentification().equals(kid.getIdentification())){
              found = true;
              break;
          }
          temp = temp.getNext();
      }
      return found ? 1 : 0;
    }

}



