/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.util.Comparator;

/**
 *
 * @author Rayhan
 */
public class MyComparator implements Comparator<Node>{

    @Override
    public int compare(Node a,Node b) {
        
        if(a.val>b.val)
        {
            return 1;
        }
        else if(a.val<b.val)
        {
            return -1;
        }
        return 0;
    }
    
}
