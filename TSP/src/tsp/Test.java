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
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        int[][] tt;
        File a=new File("temp.txt");
        Scanner in=new Scanner(a);
        tt=new int[49][49];
        for(int i=1;i<=48;i++)
        {
            for(int j=1;j<=48;j++)
            {
                tt[i][j]=in.nextInt();
            }
        }
        File b=new File("tour.txt");
        in=new Scanner(b);
        int dist=0;
        int x=in.nextInt();
        int y;
        for(int i=1;i<=48;i++)
        {
            
            y=in.nextInt();
            System.out.println(x+" "+y);
            dist+=tt[x][y];
            x=y;
        }
        System.out.println(dist);
    }
    
}
