/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_offline;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rayhan
 */
public class AI_OFFLINE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //State.setBeginState(3, 3);
        File file = new File("inputs.txt");
        SearchRunner sr;
        Node result;
        Scanner in;
        try {
            in = new Scanner(file);
            while (in.hasNextInt()) {
                int cap = in.nextInt();
                int miss = in.nextInt();
                int cann = in.nextInt();
                
                System.out.println("Missionaries: "+miss+"\nCannibals: "+cann+"\nCapacity: "+cap);
                sr = new SearchRunner(30000, 1000000, cap, miss, cann);
                result = sr.DoDFS();
                if(result!=null)System.out.println(result.cost);
                //sr.printpath(result);
                result = sr.DoBFS();
                if(result!=null)System.out.println(result.cost);
                System.out.println("-----------------------------------------------------------");
                //sr.printpath(result);  
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AI_OFFLINE.class.getName()).log(Level.SEVERE, null, ex);
        }

        //SearchRunner sr=new SearchRunner(30000, 1000000, 13, 198, 169);
        //SearchRunner sr=new SearchRunner(30000, 1000000, 2, 6, 6);        
        //State initState=new State(5, 5);
//        
    }

}
