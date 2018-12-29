/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.util.Random;

/**
 *
 * @author Rayhan
 */
public class SearchRunner {
    Random r;
    boolean flag; ///random or na
    Graph graph;
    TSPALGO currentTspalgo;
    
    public SearchRunner(Graph g,int f)
    {
        this.graph=g;
        r=new Random();
        flag=true;
        if(f==0)
        {
            flag=false;
        }
        //this.currentTspalgo=new NearestNeighbour();
        this.currentTspalgo=new NearestInsertion();
    }
    void setAlGO(TSPALGO algo)
    {
        this.currentTspalgo=algo;
    }
    int [] returnTour()
    {
        return this.currentTspalgo.maketour(this.graph,this.flag);
    }
    
    void printtour(int[] tour)
    {
        for(int i=0;i<=this.graph.n;i++)
        {
            System.out.print(tour[i]+"-->");
        }
        System.out.println("");
        int sz=this.graph.n;
        int cost=0;
        double dist=0.0;
        for(int i=0;i<sz;i++)
        {
           this.graph.points[tour[i]].printCoord();
            System.out.print("---->   "+this.graph.grid[tour[i]][tour[i+1]]+" -----> ");
            this.graph.points[tour[i+1]].printCoord();
            System.out.println("");
            cost+=this.graph.grid[tour[i]][tour[i+1]];
            dist+= Math.sqrt(this.graph.grid[tour[i]][tour[i+1]]);
        }
        System.out.println("Total Cost: "+ cost);
        System.out.println("Total Dist: "+ dist);
    }
    
    
}
