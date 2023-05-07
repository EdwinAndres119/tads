package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.PetDTO;
import lombok.Data;


@Data
public class ListDE {
    private NodeDE head;
    private int size;
    public void addPet(Pet pet) {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                temp.getNext();

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
    public void deletePet(String phone) {
        NodeDE empt = null;
        NodeDE temp = head;

        while (temp != null && !temp.getData().getOwnernumb().equals(phone)) {
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
        }if (temp.getNext() !=null){
            temp.getNext().setPrevious(empt);
        }
        size--;
    }
    public void addPetInPos(Pet pet, int pos2) {
        NodeDE temp = head;
        NodeDE newNode = new NodeDE(pet);

        if (pos2 < 0 || pos2 >= size)//to do a validation and add the kid in the last position
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
        if( this.head!=null){
            NodeDE temp = this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int getCountPetByLocCodeMale(String code){
        int male =0;
        if( this.head!=null){
            NodeDE temp = this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().equals(code)&&temp.getData().getGender() == 'M'){
                    male++;
                }
                temp = temp.getNext();
            }
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
        }
        return female;
    }

    public void orderByGender()  {
        ListDE listDE1 = new ListDE();
        int sum = 0;
        NodeDE temp = head;
        if (head == null) {
            System.out.println("no hay datos");
        } else {
            while (temp != null) {
                if (temp.getData().getGender() == 'F') {
                    listDE1.addPetToBeginning(temp.getData());

                }
                temp = temp.getNext();
            }
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
            this.head = listDE1.getHead();
        }
    }
    public void losePositions(String phone, int lose) {
        NodeDE temp = head;
        int sum = 0;
        ListDE listDE1 = new ListDE();
        if (head != null) {
            while (temp != null) {
                if (!temp.getData().getName().equals(phone)) {
                    listDE1.addPet(temp.getData());
                    temp = temp.getNext();
                } else {
                    temp = temp.getNext();
                }
            }
        }
        sum = lose + getPosByPhone(phone);
        listDE1.addPetInPos(getPetByPhone(phone), sum);
        this.head = listDE1.getHead();
    }
    public int getPosByPhone(String phone) {
        NodeDE temp = this.head;
        int acum = 0;
        if (head != null) {
            while (temp != null && !temp.getData().getOwnernumb().equals(phone)) {
                acum = acum + 1;
                temp = temp.getNext();

            }
        }
        return acum;
    }
    public Pet getPetByPhone(String phone) {
        NodeDE temp = this.head;
        if (head != null) {
            while (temp!=null){
                temp = temp.getPrevious();
            }
            while (temp != null && !temp.getData().getOwnernumb().equals(phone)) {
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
        }
    }

    //method to delete a pet with a specified age-----------------------------------------------
    public void deleteByAge(byte age) {
        NodeDE temp = this.head;
        ListDE listDE1 = new ListDE();
        if (this.head != null) {
            while (temp != null) {
                if (temp.getData().getAge() != age) {
                    listDE1.addPetToBeginning(temp.getData());
                }
                temp = temp.getNext();
            }
            this.head = listDE1.getHead();
        }
    }

    //method to get the average age of the pets-----------------------------------------------
    public double getHalfAgeDog() {
        double averageAge = 0;
        NodeDE temp = this.head;
        if (this.head != null) {
            while (temp != null) {
                averageAge = averageAge + temp.getData().getAge();
                temp = temp.getNext();
            }
            averageAge = averageAge / size;
            return averageAge;
        }
        return averageAge;
    }

    //method to earn positions------------------------------------------------
    public void winPos(String phone, int earn) {
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
        }
        sum = getPosByPhone(phone) - earn;
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

    public void sendPetsToEndByChar(char user) {
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
        }
        this.head = listDE1.getHead();
    }
}