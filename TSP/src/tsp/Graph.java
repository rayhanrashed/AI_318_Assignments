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


public class Graph {
    int[][] grid;
    int n;
    Coord[] points;

    public Graph() {

        
    }
    void takeInput() throws FileNotFoundException
    {

        File file = new File("inputs.txt");
        Scanner sc;
        boolean path=false;
        sc = new Scanner(file);
        this.n=sc.nextInt();
        this.grid=new int[n+1][n+1];
        points=new Coord[n+1];
        int[] x;
        x=new int[n+1];
        int[] y;
        y=new int[n+1];
        for(int i=1;i<=n;i++)
        {
            x[i]=sc.nextInt();
            y[i]=sc.nextInt();
            points[i]=new Coord(x[i], y[i]);
        }
        for(int i=1;i<=n;i++)
        {
            for(int j=i;j<=n;j++)
            {
                if(i==j)
                {
                    grid[i][j]=0;
                }
                else
                {
                    grid[i][j]=( x[i] - x[j] )*( x[i] - x[j] ) + (y[i] - y[j] )*(y[i] - y[j]);
                    grid[j][i]=grid[i][j];
                }
            }
        }
    }
    
    void printgraph()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                System.out.print(grid[i][j] +" ");
            }
            System.out.println("");
        }
    }
    
    
}
