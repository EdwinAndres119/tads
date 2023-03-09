package model;

import lombok.Data;

@Data
public class Node {
    private kid data;
    private Node next;

    public Node (kid data) {
        this.data = data;
    }
    // el nodo es lo costal que esta encargado para por asi decirlo almacenar el niño y el brazo para otro niño
}
