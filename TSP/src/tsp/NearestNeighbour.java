/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import com.oracle.jrockit.jfr.DataType;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Rayhan
 */
public class NearestNeighbour implements TSPALGO{
    Random r;
    @Override
    public int[] maketour(Graph g,boolean ran) {
        System.out.println("Calculating Tour using Nearest Neieghbour Heuristics: A");
        r=new Random();
        int[] tour;
        int n=g.n;
        tour=new int[n+1];
        int beg;
        if(!ran)
        {    
            System.out.println("Then Give the initial node: ");
            Scanner in=new Scanner(System.in);
            beg=in.nextInt();
        }
        else
        {
            beg=r.nextInt(g.n);
            if(beg==0)
            {
                beg=1;
            }
            System.out.println("BEG"+beg);
        }
        int[] vis;
        vis=new int[n+1];
        for(int i=0;i<=n;i++)
        {
            vis[i]=0;
        }
        int cur=beg,idx=1;
        vis[beg]=1;
        tour[0]=beg;
        tour[n]=beg;
        while (true) {            
            if(idx>=n)
            {
                break;
            }
            int min=Integer.MAX_VALUE;
            int id=0;
            for(int i=1;i<=n;i++)
            {
                if(i==cur)continue;
                if(min>g.grid[cur][i] && vis[i]!=1)
                {
                    id=i;
                    min=g.grid[cur][i];
                }
            }
            cur=id;
            tour[idx++]=cur;
            vis[cur]=1;
        }
        
        return tour;
    }
    
}
