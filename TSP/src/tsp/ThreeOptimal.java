/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rayhan
 */
public class ThreeOptimal implements TSPALGO{
    Random random;
    int[] tour;
    int x,y,z,r;
    int[] temp;
    int[] order;
    int ea,eb,ec;
    public ThreeOptimal()
    {
        this.x=0;
        this.y=0;
        this.z=0;
        this.r=0;
    }
    @Override
    public int[] maketour(Graph g, boolean ran) {
        System.out.println("Calculating Tour using ThreeOptimal Improvement Heuristics: C2");
        random = new Random();
        
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
        }
        
        else
        {
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
                    int beg = random.nextInt(g.n + 1);
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
        
        temp=new int[7];
        
       boolean improvement=true;
       int valaa=0;
       int dhela;
       while(true)
       {
           dhela=valaa;
           valaa=0;
           improvement=false;
           for (int i = 0; i < n; i++) {
               for (int j = i + 2; j < n; j++) {
                   int x = n - 1;
                   if (i > 0) {
                       x++;
                   }
                   for (int k = j + 2; k < x; k++) {
                       //System.out.println(i + " " + (i + 1) + " " + j + " " + (j + 1) + " " + k + " " + (k + 1));
                       temp[1] = i;
                       temp[2] = i + 1;
                       temp[3] = j;
                       temp[4] = j + 1;
                       temp[5] = k;
                       temp[6] = k + 1;
                       int karim=checkforbetterment(g);
                       if(valaa>karim)
                       {
                           //System.out.println(valaa+" "+karim);
                           valaa=karim;
                           order=temp.clone();
//                           for(int v=1;v<order.length;v++)
//                           {
//                               System.out.print(order[v]+" ");
//                           }
//                           System.out.println("");
                           
                       }
                       
                   }

               }
           }
           if(valaa==0)
           {
               break;
           }
           else
           {
               //smallprint(tour, g);
               makeswap(g);
               //smallprint(tour, g);
           }
       }
        
        //System.out.println(valaa);
        print(tour, g);
        return tour;
    }
    
    int checkforbetterment(Graph g)
    {
        boolean retval=false;
        int mv=0;
        //int cst=g.grid[temp[1]][temp[2]] + g.grid[temp[3]][temp[4]] + g.grid[temp[5]][temp[6]];
        int cst=g.grid[tour[temp[1]]][tour[temp[2]]] + g.grid[tour[temp[3]]][tour[temp[4]]] + g.grid[tour[temp[5]]][tour[temp[6]]];
        for(int i=2;i<=5;i++)
        {
             int k,l;
             int j= i ^ 1;
             //System.out.println(i+" ," + j);
             if(i==2 || i==3)
                {
                 k=4;
                 l=5;
                }
                else
                {
                 k=2;
                 l=3;
                }
             for(int ida=1;ida<=2;ida++)
             {
                k=k^1;
                l=l^1;
                //System.out.println(temp[1] +" " + temp[i] +" " + temp[j] +" " + temp[k] + " " + temp[l] +" " + temp[6]);
                //int tempaa=g.grid[temp[1]][temp[i]] + g.grid[temp[j]][temp[k]] + g.grid[temp[l]][temp[6]];
                int tempaa=g.grid[tour[temp[1]]][tour[temp[i]]] + g.grid[tour[temp[j]]][tour[temp[k]]] + g.grid[tour[temp[l]]][tour[temp[6]]];
                int nv=tempaa-cst;
                //return nv;
                // System.out.println(nv);
                if(mv>nv)
                {
                    //System.out.println(nv);
                    mv=nv;
                    retval=true;
                    x=temp[i];
                    y=temp[j];
                    z=temp[k];
                    r=temp[l];
                    temp[2]=x;
                    temp[3]=y;
                    temp[4]=z;
                    temp[5]=r;
                    //makeswap(g);
                    //return nv;
                }
             }
        }

        return mv;
    }
//        int[] newarr;
//        newarr=new int[g.n+2];
//        int[] vals;
//        vals=new int[7];
//        for(int i=1;i<=6;i++)
//        {
//            vals[i]=temp[i];
//        }
//        vals[0]=x;vals[1]=y; vals[2]=z; vals[3]=r;
//        int[] aa;
//        aa=new int[g.n+2];
        

   
    
    void makeswap(Graph g)
    {
        //System.out.println("Sol: "+temp[1] +" " + temp[x] +" " + temp[y] +" " + temp[z] + " " + temp[r] +" " + temp[6]);
//        for(int zx=1;zx<order.length;zx++)
//        {
//            order[zx]--;
//        }
        int[] newa=new int[g.n+2];
        int idxfill=0;
        for(int ada=0;ada<=order[1];ada++)
        {
            newa[idxfill++]=tour[ada];
        }
        if(order[2]>order[3])
        {
            for(int ada=order[2];ada>=order[3];ada--)
            {
                newa[idxfill++]=tour[ada];
            }
        }
        else
        {
            for(int ada=order[2];ada<=order[3];ada++)
            {
                newa[idxfill++]=tour[ada];
            }   
        }
        if(order[4]>order[5])
        {
            for(int ada=order[4];ada>=order[5];ada--)
            {
                newa[idxfill++]=tour[ada];
            }
        }
        else
        {
            for(int ada=order[4];ada<=order[5];ada++)
            {
                newa[idxfill++]=tour[ada];
            }   
        }
        for(int ada=order[6];ada<=g.n;ada++)
        {
            newa[idxfill++]=tour[ada];
        }
        
//        if(temp[x]>temp[y])
//        {
//            opt2swap(temp[y],temp[x]);
//        }
//        if(temp[z]>temp[r])
//        {
//            opt2swap(temp[r],temp[y]);
//        }
        tour=newa.clone();
        //System.out.println("After making the swap");
        //smallprint(tour, g);
    }
   
    void opt2swap(int i,int k)
    {
        int x=k;
        for(int t=i;t<=x;t++)
        {
            int temp=tour[t];
            tour[t]=tour[x];
            tour[x]=temp;
            x--;
        }
//        for(int t=0;t<array.length;t++)
//        {
//            System.out.print("- B --> "+ array[t]);
//        }
    }
    
    void smallprint(int[] tour,Graph graph)
    {
        for(int i=0;i<=graph.n;i++)
        {
            System.out.print(tour[i]+"-->");
        }
        System.out.println("");
    }
    int calculate(Graph g)
    {
        int cost=0;
        for(int i=0;i<g.n;i++)
        {
            
        }
        return cost;
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
