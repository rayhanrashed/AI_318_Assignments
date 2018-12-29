/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

/**
 *
 * @author Rayhan
 */
public class Coord{
    int x;
    int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void printCoord()
    {
        System.out.print("( "+ this.x+", "+this.y+" )");
    }
}
