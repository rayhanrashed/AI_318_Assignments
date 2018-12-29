/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rayhan
 */
public class TwoOptimal implements TSPALGO{
    Random r;
    @Override
    public int[] maketour(Graph g, boolean ran) {
        System.out.println("Calculating Tour using TwoOptimal Improvement Heuristics: C1");
        r = new Random();
        int[] tour;
        int n = g.n;
        tour = new int[n + 1];
        if (!ran) {
            System.out.println("Then Give the Tour: ");
            File f=new File("tour.txt");
            Scanner in=null;
            try {
                in = new Scanner(f);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TwoOptimal.class.getName()).log(Level.SEVERE, null, ex);
            }
            for(int i=0;i<n;i++)
            {
                tour[i]=in.nextInt();
            }
            tour[n]=tour[0];
        } else {
            int[] vis;
            vis=new int[n+1];
            for(int i=0;i<=n;i++)
            {
                vis[i]=0;
            }
            for(int i=0;i<n;i++)
            {
                while(true)
                {
                    int beg = r.nextInt(g.n + 1);
                    if(beg==0)
                    {
                        beg=1;
                    }
                    if(vis[beg]==1)
                    {
                        continue;
                    }
                    vis[beg]=1;
                    tour[i]=beg;
                    break;
                }                
            }
            tour[n]=tour[0];
            


        }
        print(tour, g);
        int idea=1;
        
        int aa=0,bb=0;
        while (idea==1) {
            idea=0;
            int valaa=0;
            for(int i=1;i<n-1;i++)
            {
                for(int j=i+1;j<n;j++)
                {
                    if(i==1 && j==n-1)
                    {
                        continue;
                    }
                    //System.out.println(i-1 + " ,"+ i+","+j+","+ (j+1));
                    int now=g.grid[tour[i-1]][tour[i]]+g.grid[tour[j]][tour[j+1]];
                    int then=g.grid[tour[i-1]][tour[j]]+g.grid[tour[i]][tour[j+1]];
                    int ext=then-now;
                    if(ext<valaa)
                    {
                        //opt2swap(tour, i, j);
                        idea=1;
                        valaa=ext;
                        aa=i;
                        bb=j;
                    }
                }
            }
            if(idea==1)
            {
                System.out.println("Improvement made");
                int x=bb;
                for(int t=aa;t<=x;t++)
                {
                    int temp=tour[t];
                    tour[t]=tour[x];
                    tour[x]=temp;
                    x--;
                }
            }
            
        }
        print(tour, g);
        return tour;
    }
    
    void opt2swap(int [] array,int i,int k)
    {
        int x=k;
        for(int t=i;t<=x;t++)
        {
            int temp=array[t];
            array[t]=array[x];
            array[x]=temp;
            x--;
        }
//        for(int t=0;t<array.length;t++)
//        {
//            System.out.print("- B --> "+ array[t]);
//        }
    }
    
    
    void print(int[] tour,Graph graph)
    {
        for(int i=0;i<=graph.n;i++)
        {
            System.out.print(tour[i]+"-->");
        }
        System.out.println("");
        int cost=0;
        double dist=0.0;
        for(int i=0;i<graph.n;i++)
        {
           graph.points[tour[i]].printCoord();
            System.out.print("---->   "+graph.grid[tour[i]][tour[i+1]]+" -----> ");
            graph.points[tour[i+1]].printCoord();
            System.out.println("");
            cost+=graph.grid[tour[i]][tour[i+1]];
            dist+=Math.sqrt(graph.grid[tour[i]][tour[i+1]]);
        }
        System.out.println("Total Cost: "+ cost);
        System.out.println("Total Dist: "+ dist);
    }
    
}

