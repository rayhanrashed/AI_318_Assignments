/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Rayhan
 */
public class CheapestInsertion implements TSPALGO {

    Random r;

    @Override
    public int[] maketour(Graph g, boolean ran) {
        System.out.println("Calculating Tour using Cheapest Insertion Heuristics: B2");
        r = new Random();
        int[] tour;
        int n = g.n;
        tour = new int[n + 1];
        int beg;
        if (!ran) {
            System.out.println("Then Give the initial node: ");
            Scanner in = new Scanner(System.in);
            beg = in.nextInt();
        } else {
            beg = r.nextInt(g.n);
            if (beg == 0) {
                beg = 1;
            }
            System.out.println("BEG" + beg);
        }
        int[] vis;
        vis = new int[n + 1];
        int min = Integer.MAX_VALUE;
        int id = 0;
        for (int i = 1; i <= n; i++) {
            if (i == beg) {
                continue;
            }
            if (min > g.grid[beg][i]) {
                id = i;
                min = g.grid[beg][i];
            }
        }
        tour[0] = beg;
        tour[1] = id;
        tour[2] = beg;
        vis[beg] = 1;
        vis[id] = 1;
        int x=0, y=0, z=0;
        int toursize = 2;
        while (toursize < n) {
            int temp=Integer.MAX_VALUE;
            for (int i = 0; i < toursize; i++) 
            {
                for (int j = 1; j <= n; j++)
                {
                    if (vis[j] != 1)
                    {
                        int val=g.grid[tour[i]][j] + g.grid[j][tour[i+1]] - g.grid[tour[i]][tour[i+1]];
                        if(val<temp)
                        {
                            temp=val;
                            x=i;
                            y=i+1;
                            z=j;
                        }

                    }
                }
            }
            for(int i=toursize;i>=y;i--)
            {
                tour[i+1]=tour[i];
            }
            tour[y]=z;
            toursize++;
            vis[z]=1;
        }
        return tour;
    }
}
