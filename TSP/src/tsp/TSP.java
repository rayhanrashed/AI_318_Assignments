/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Rayhan
 */
public class TSP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Graph graph=new Graph();
        graph.takeInput();
        graph.printgraph();
        SearchRunner sr=new SearchRunner(graph,0);
        int[] temp;
        TSPALGO a=new NearestNeighbour();
        TSPALGO b1=new NearestInsertion();
        TSPALGO b2=new CheapestInsertion();
        TSPALGO c1=new TwoOptimal();
        TSPALGO c2=new ThreeOptimal();
        sr.setAlGO(a);
        temp=sr.returnTour();
        sr.printtour(temp);
        System.out.println("RAYHAN");
        sr.setAlGO(b1);
        temp=sr.returnTour();
        sr.printtour(temp);
        sr.setAlGO(b2);
        temp=sr.returnTour();
        sr.printtour(temp);
        
        sr.setAlGO(c1);
        temp=sr.returnTour();
        
        sr.setAlGO(c2);
        temp=sr.returnTour();
        
        
        
        //sr.printtour(temp);
    }
    
}
