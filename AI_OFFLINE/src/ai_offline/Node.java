/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_offline;

/**
 *
 * @author Rayhan
 */
public class Node {
    State nodeState;
    int cost;
    Node parent;

    public Node(State nodeState, int cost) {
        this.nodeState = nodeState;
        this.cost = cost;
    }
}
