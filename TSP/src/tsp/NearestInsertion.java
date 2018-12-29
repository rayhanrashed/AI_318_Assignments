/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Rayhan
 */
public class NearestInsertion implements TSPALGO {

    Random r;

    @Override
    public int[] maketour(Graph g, boolean ran) {
        System.out.println("Calculating Tour using Nearest Insertion Heuristics: B1");
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
        vis=new int[n+1];
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
        tour[0]=beg;
        tour[1]=id;
        tour[2]=beg;
        vis[beg]=1;
        vis[id]=1;
        int toursize=2;
        PriorityQueue<Node> pq;
        pq=new PriorityQueue<Node>(new MyComparator());
        for(int i=1;i<=n;i++)
        {
            if(i!=beg && i!=id)
            {
                pq.add(new Node(i,g.grid[beg][i]));
                pq.add(new Node(i,g.grid[id][i]));
            }
        }
//        for (Iterator<Node> iterator = pq.iterator(); iterator.hasNext();) {
//            Node next = iterator.next();
//            System.out.println(next.id+" "+ next.val);
//            
//        }
        while(!pq.isEmpty())
        {
            Node u=pq.poll();
            if(vis[u.id]==1)
            {
             continue;
            }
            vis[u.id]=1;
            int temp=Integer.MAX_VALUE;
            int tempid=0;
            for(int i=0;i<toursize;i++)
            {
               int x=g.grid[tour[i]][u.id]+g.grid[u.id][tour[i+1]] - g.grid[tour[i]][tour[i+1]];
               if(x<temp)
               {
                   temp=x;
                   tempid=i+1;
               }
            }
            if(toursize>n)
            {
                break;
            }
            for(int i=toursize;i>=tempid;i--)
            {
                tour[i+1]=tour[i];
            }
            tour[tempid]=u.id;
            toursize++;
            for(int i=1;i<=n;i++)
            {
                if(vis[i]!=1)
                {
                    pq.add(new Node(i,g.grid[u.id][i]));
                }
            }
            
            
        }
        return tour;
    }

}
