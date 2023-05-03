package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.KidDTO;
import lombok.Data;

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
    public void orderBygender() {
        ListSE listSE1 = new ListSE();
        Node temp = head;
        int sum = 0;
        if (head==null){
            System.out.println("no hay datos");
        }else{
            while(temp!=null){
                if(temp.getData().getGender() == 'F'){
                    listSE1.addToStart(temp.getData());
                }
                temp = temp.getNext();
            }
            temp = head;
            while (temp !=null){
                if(temp.getData().getGender() == 'M') {
                    listSE1.addInPos(temp.getData(),sum);
                    temp =temp.getNext();
                    sum = sum+2;
                } else {
                    temp = temp.getNext();
                }

            }
            this.head = listSE1.getHead();
        }

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
     sum = lose + getPosByid(id);
        listSE1.addInPositionVali(getKidById(id), sum);
        this.head = listSE1.getHead();
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

    public void addInPositionVali(Kid kid, int pos2){
        Node temp = head;
        Node newNode = new Node(kid);
        int listlength = getlistlength();
        if (pos2 < 0 || pos2 >= listlength)
            add(kid);
        if(pos2 == 0){
            newNode.setNext(head);
            head =newNode;
        }else{
            for(int i = 0; temp.getNext() !=null && i < pos2 -1; i++){
                temp = temp.getNext();

            }
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
        }
    }

    public int getlistlength(){
        int total = 0;
        Node actu = head;
        while (actu!=null){
            total++;
            actu = actu.getNext();
        }
        return total;
    }
    public void putKidsBeginning(){
        Node temp = this.head;
        ListSE listSE1 = new ListSE();
        if (this.head != null){
            while (head !=null){
                if (temp.getData().getGender() == 'M'){
                    listSE1.addToStart(temp.getData());
                } else if (temp.getData().getGender() == 'F') {
                    listSE1.add(temp.getData());
                }
               temp = temp.getNext();
            }
         this.head = listSE1.getHead();
        }
    }
    public void WinPos(String id , int earn){
        Node temp = head;
        int sum = 0;
        ListSE listSE1 = new ListSE();
        if(head != null){
            while(temp !=null){
                if(!temp.getData().getIdentification().equals(id)){
                    listSE1.add(temp.getData());
                    temp = temp.getNext();
                }else{
                    temp = temp.getNext();
                }
            }
        }
        sum = getPosByid(id)- earn;
        listSE1.addInPositionVali(getKidById(id),sum);
        this.head = listSE1.getHead();
    }
    public int getCountKidByLocationCode(String code){
        int count = 0 ;
        if(this.head !=null){
            Node temp = this.head;
            while (temp!=null){
                if(temp.getData().getLocation().getCode().equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }
    public String ReportByage(){
        int quantity1 = 0;
        int quantity2 = 0;
        int quantity3 = 0;
        int quantity4 = 0;
        int quantity5 = 0;
        Node temp = this.head;
        if(this.head !=null){
            while(temp !=null){
                if(temp.getData().getAge()>= 0 && temp.getData().getAge()<~3){

                } else if (temp.getData().getAge()>3 && temp.getData().getAge()<=6) {

                } else if (temp.getData().getAge()>6 && temp.getData().getAge()<=9) {

                } else if (temp.getData().getAge()>9 && temp.getData().getAge()<=12) {

                } else if (temp.getData().getAge()>12 && temp.getData().getAge()<=15) {
                    quantity5++;
                }
            temp = temp.getNext();
            }
        }
        return "los niños entre 0-3 años:"+ quantity1 +
        "los niños entre 3-6 años:"+ quantity2 +
        "los niños entre 6-9 años:"+ quantity3 +
        "los niños entre 9-12 años:"+ quantity4 +
        "los niños entre 12-15 años:"+ quantity5;

    }
    public int getCountKidByLocCodeFemale(String code){
        int female =0;
        if( this.head!=null){
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().equals(code)&&temp.getData().getGender() == 'F'){
                    female++;
                }
                temp = temp.getNext();
            }
        }
        return female;
    }
    public int getCountKidByLocCodeMale(String code){
        int male =0;
        if( this.head!=null){
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().equals(code)&&temp.getData().getGender() == 'M'){
                    male++;
                }
                temp = temp.getNext();
            }
        }
        return male;
    }


}



