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
            NodeDE newNode=new NodeDE(pet);
            setHead(newNode);
        }
        size++;
    }
    /*
    metodo para add pets to beginning
 creo primero que todo un nuevo nodo con la mascota que recibo para agregar
 verifico si mi lista no esta vacia
 si no esto es porque ya hay datos en mi lista
 creo un temp o ayudante y este inicia en cabeza
 con un bucle puedo recorrer toda la lista y verificando si el codigo de
 la mascota que se quiere agregar no coincide con algun codigo que ya esta en la
 lista si este codigo de la mascota ya esta debo mostrar una exepcion que me
 diga que ya existe una mascota con este codigo
 ya despuesd e avitar cualquier exepcion
 debemos poner el nuevo nodo que se quiere poner en cabeza y se estrablece como la nueva cabeza
 y el que estaba en esta posicion se como el siguiente y por ultimo incrementamos
 el contador de la lista en 1 ya que se agrego una nueva mascota
     */

    public void addPetToBeginning(Pet pet) throws ListDeException{
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
/*
algoritmo para el metodo get count pet location code
iniciamos un contador en cero para asi ir llevando la cuenta de las
mascotas encontrdas
verifico si mi lista no esta vacia
si esta no esta vacia creo un temp un ayudante que empiece desde la cabeza
con unbucle recorremos toda la lista
con este tambien verificamos el codigo de la ubicacion del nodo coincide con
el codigo de la ubicacion que necesitan
y si estos coinciden aumento el  contador y asi avazamos por cada nodo con el
ayudante temp
cuando ya el temporal termine de recorrer toda la lista devolvemos el contador
que esta nos dira cuantas mascotas hay con este codigo de ubicacion.
 */

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
/*
metodo para ordernar pro genero
primero creo un cntador en ceroque la utilizo para controlar la posicion de
insercion en la nueva lista copia
se crea un temporal en cabeza de la lista original
verifico si la lista no esta vacia y si esta vacia necesito una exepcion
si la lista no esta vacia
con un bucle se recorre la lista original mientras el temp no sea nulo
dentro de este verifico el genero si es F (femenino )
si es feemenino se agrega al inicio de lista copia y se utiliza el metodo agregar
al inicio
despues de esto reinicio el el temporal de la lista original cabeza
inicio otro bucle para recorrer la lista
y en este otro bucle verifico si es del genero M (masculino)
y si es de este genero se agrega en una posicion especifica en una lista copia
y utilizo el añadir en posicion
despues de agregar se incrementa el contador en 2 para asegurar que las
mascotas de genero masculino queden en distintas posiciones, separadas
si el genero del nodo no es masculino se avanza al siguiente nodo
y al final se actualiza la cabeza de la lista original para que cabeza
de esta manera la lista original queda ordenada por el genero
 */
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
    /*
    metodo para perder posiciones se crea un ayudante temporal que empieza
    en cabeza
    se inicia tambien un contador que ayuda para el seguimiento de la posicion
    en la lista
    se creo una lista copia para guardar las mascotas ya modificadas o la posicion
    se verifica si nuestra lista no esta vacia
    si la lista no esta vacia se realiza un bucle para recorrer
    la lista mientras el temporal no sea nulo
    dentro de este nucleo se verifica si el codigo que sea diferente al codido
    dek parametro
    si este es diferente se agrega a la lista copia utilizando el metodo
    añadir mascota
    despues de recorrer toda la lista se calcula la nueva posicion con ayuda del
    contador, despues con el codigo de la mascota sumado al resultado y el dato
    de cuantas posiciones se quiere perder
    se utiliza el metodo la validacion de añadir por posicion para agregar la mascota
    con el codigo de la indentificacion especifico a la nueva lista con la nueva posicion
    determinada por el contador
    por ultimo se actualiza la cabeza de la lista original
     */
    public void losePositions(String id, int lose) throws ListDeException {
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
        }
        sum = getPosById(id)+lose;
        listDE1.addInPosValidations(getPetByid(id), sum);
        this.head = listDE1.getHead();
    }
    /*
    metodo para obtenr la posicion por la identificacion
    se crea una ayudante temporal que inicia desde la cabeza
    se crea un acumulador para llecar el conteo de las posiciones
    se verifica que la lista no este vacia
    si la lista no esta vacia
    con un bucle se recorre toda la lista miesntras el temp no sea nulo
    y el codigo de la identificacion coincida con el dato que ya nos dan
    dentro de este bucle se incrementa el acumulador en 1 para contar la
    posicion se avanza al siguiente nodo de la lista
    despuies de esto se decuelve el acumulador que nos da la posiciond de
    la mascota en nuestra lista si no se encuentra esta se decuelte 1

     */

    public int getPosById(String id) {
        NodeDE temp = this.head;
        int acum = 1;
        if (head != null) {
            while (temp != null && temp.getData().getIdentification().equals(id)) {
                acum = acum + 1;
                temp = temp.getNext();

            }
        }
        return acum;
    }
    /*
    se cra un ayudante temporal que se inicia desde la cabeza de la lista
    se verifica que la lista no este vacia
    si esta lista no esta vacia se crea un bucle que recorre toda la lista mientras
    el costal no sea nulo dentro de este se verifica que el codigo de la id
    coincida con el dato que nos dan si estos coinciden se devuelve la mascota
    se avanza al siguiente nodo de la lista
    sino se encuntre ninguna mascota con este codigo de id se retorna un null
    ya que esta no esta en la lista
     */

    public Pet getPetByid(String identification) throws ListDeException{
        NodeDE temp = head;
        if (head != null) {
            while (temp != null) {
                if (temp.getData().getIdentification().equals(identification)) {
                    return temp.getData() ;
                }
                temp = temp.getNext();
            }
        }
        return null;

    }

    /*
    este metodo para cambiar los extremo de la lista
    primero se verifica que esta lista no este vacia y si no tiene menos de
    de dos datos yq que con solo un dato no se puede intercambiar los
    extremos de la lista
    si cumple con esto se crea un ayudante que inicia desde la cabeza
    y con un bucle se recorre la lista hsata llegar al final de la lista
    luego se realiza el intercambio de los datos del final con los del inico
    de la lista
    se guarda un copia de los datos del primero en la lisat
    se actualiza los datos de la cabeza con los datos del ultimo
    de la lista
    y estos mismo pero con los datos del inicio para el final de la lista

     */

    public void changeExtremes() throws ListDeException {
        if(head!=null && head.getNext()!=null) {
            NodeDE temp=head;
            while(temp.getNext()!=null) {
                temp=temp.getNext();
            }
            Pet copy=this.getHead().getData();
            head.setData(temp.getData());
            temp.setData(copy);
        }
    }
/*
el metodo inverti la lista
se crea unayudante temporal que inicia desde la cabeza de la lista
se crea una lista copia para guardar los datos que se invirtieron
se verifica si la lista no esta vacia
si esta no esta vacia entonces
con un bucle se recorre la lista original desde el inico hasta el final
a cada una se le toma y se agrega al incio de nueva lista utilizando el metodo
agregar al inicio de la lista
por ultimo se actualiza la cabeza la lista original para que esta apunte a la
cabeza de la otra lista
y si esta lista es vacia se debe mandar una exepcion que no hay datos para hacer este
metodo
 */
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
    /*
    netodo para put pet begibning
    se creao un ayudante temporal y este se inicia en la cabeza
    se crea un alista copia
    se verifica si la lista si no esta vacia
    si esta no esta vacia entonces
    con un bucle se recorre toda la lista por cada nod se verifica el genero de
    cada mascota
    si el genro de la mascota es de genro masculino se agregar al inicio de la lista
    con el metodo agrgar al incio de la lista
    si esta es de genero femenino se agrega al final de la lista con el metod
    añadir
    se actualiza la cabeza de la lista original parq ue queden organizada con
    los genros masculinos al inicio y los femeninos al final de la lista

     */
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

/*
metodo para saber el promedio de la edad de las mascotas
se crea la variable promedio de edad que inicia en 0
se crea un ayudante que empiece desde la caebeza de la lista
se cerifica qeu la lisat no este vacia
si esta no esta vacia se hace un bucle que recorra la lista
en cada uno de los nodos que pase recoje la edad de cada uno de estos y se suma
al promedio de edad
depues de recorrer toda la lista y obtener las edadesd e cada uno de la lista
se calcula el promedio de esta dividiendo el promedio de la edad con el tamaño de
la lista finalmente se decuelve la edad promedio de la lisata
 */
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

    public void earnPositions(String id, int win)  {
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
        }if (win!=1){
            sum = win-getPosById(id) ;
            listDE1.addInPosValidations(getPetByid(id), sum);

        }else {
            listDE1.addPetToBeginning(getPetByid(id));
        }
        this.head = listDE1.getHead();
    }
/*
metod para generar un reporte por las edades
se crean variables iniciadas en 0 para controlar la cantidad de mascotas en
cada rango de edad
se crea un ayudante temp que inicia desde la cabeza de la lista
se verifica que la lista no este vacia
si la lista no esta vacia
con un bucle recorro toda la lista
en cada nodo se obtine la edad actual de cada perro
y se verifica en que rango de eadad esta
dependiendo el rango de edad se incrementa las variables
luego se avanza por cada nodo
despues de recorre toda la lista se hace un informe en cadena que muestra
la cantidad de perros en cada rango de edad
y a lo ultimo se devuelve el informe que se genero
 */
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
    /*
    metodo para verificar por la identificacion
    se crea un ayudante temporal que se para en la caebza de la lista
    se crea un booleano que inicia en falso para asi encontrar la mascota con
    este id
    se inicia un bucle que recorre toda la lista
    en cada nodo o costal se compara la id para ver si esta la id que se requiere verificar
    si la id se encuentra se actuliaza el booleano a verdadero y se rompe el ciclo
    si las id son diferentes se avanza poe toda la lista hasta encontrar esta id
    depsues de recorrer toda la lista se devuelve un valor entero 1 y si no
    en un valor de 0
     */
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
/*
metodo para enviar por un caracter al final de la lista
se cre una lista copia para ahi guardar las mascotas ya organizadas
se crea un ayudante tempoal que se para en la cabaza de la lista
se verifica que la lista no este vacia
si la lista no esta vacia
con un bucle que se recorre toda la lista
en cada nodo se obtine el nombre de la mascota y se verifica que el primer caracter
que tiene en el nombre es diferente al que nos estan pidiendo
si el caracter es diferente, se agrega al principio de la nueva lista con el metodo
agregar el inicio
si el primer caracter es igual se agrega la mascota al final de la lista copia
depues de recorrer toda la lista se actualiza la lista original la cabeza con la
cabeza de la lisat copia
 */
    public void sendPetsToEndByChar(char user) throws ListDeException {
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
        } else {
            throw new ListDeException("400", "no hay datos en la lista");
        }
        this.head = listDE1.getHead();
    }
/*
metodo par ver la lista
se crea un array para lamacenar los datos de la lista
se verifica que la cabeza no sea nulo o que tenga datos la lsita
se la lista no es vacia se crea un ayudante temporal que se inicia desde
la cabeza
se utiliza un bucle par recorrer la lista y ir agregando los datos al array
despues de agregar los datros se actualiza el ayudante para que este siga
con el otro de la lista  y este bucle sigue hasta el final de la lista
y al final de este se retorna los dato que recogio la lista
 */
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
    /*
    metodo add in position validation
    se crea el ayudante temporal que se posiciona en la cabeza de la lista
    se crea un nuevo nodo o costal con la mascota que se desea agregar
    se obtiene la longitud de la lista
    se hace una validacion de la posicion
    si la posicion es menor o mayor que 0 o es igual a la longitud de la lisat
    se agrega la mascota al finalde la llista
    si la posicion es igual a 0 se pone como el nodo anterior de la cabeza actual
    si la posicion no es de 0 y esta entre los limites de la lisat se realiza un
    bucle para llegar alnodo anterior a la posicion deseada
    una vez ya ubicado en el nod anterior, se organizan los enlaces del nodo nuevo
    con el anterior.
     */
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
    /*
    metodo para obtener la longitud de la lista
    se inicia un contador en 0 para llavr el conteo de la longitud de esta
    lista
    se crea un temporal o ayudante que se inicia desde la cabeza
    se utiliza un bucle par recorrer toda la lista
    en cada nodo que pase el bucle se incrementa el contador
    depues de incrementar el contador, se actualiza el ayudante par que pase al
    siginte de la lista
    el bucle continua hasta que recorra toda la lista hasta el final
    y por ultimo de retorna el total del contador
     */
    public int getLength() {
        int total = 0;
        NodeDE temp = head;
        while (temp != null) {
            total++;
            temp = temp.getNext();
        }
        return total;
    }
}