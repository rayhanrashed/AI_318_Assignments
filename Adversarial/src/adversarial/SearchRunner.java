/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adversarial;

import java.util.Hashtable;

/**
 *
 * @author Rayhan
 */
public class SearchRunner {
    
    State beginState;
    Hashtable<State, Integer> hs;
    
    public SearchRunner(Board b) throws CloneNotSupportedException
    {
        beginState=new State(b);
        hs=new Hashtable<>(1000000);
    }
    public int runsearch() throws CloneNotSupportedException
    {
        int beta=Integer.MAX_VALUE;
        int alpha=Integer.MIN_VALUE;
        beginState.TURNOFALPHA=true;
        beginState.freeturn=false;
        int rv=alphabeta(beginState, 30, alpha, beta, true);
        return rv;
    }
    public int alphabeta(State currentState,int depth,int alpha,int beta, boolean isMAX) throws CloneNotSupportedException
    {
        int val;
        System.out.println("IN:");
        currentState.board.printboard();
        if(depth==0 || currentState.isTerminal())
        {
            return currentState.utility();
        }
        
        if(isMAX)
        {
            val=Integer.MIN_VALUE;
            State [] results=currentState.GetAllChildren();
            for(int i=0;i<results.length;i++)
            {
                boolean isNow;
                //if(currentState.board.grid[i] != 0)
                //{
                    State child=results[i];
//                    if(hs.containsKey(child))
//                    {
//                        System.out.println("Touching Void with A");
//                        child.board.printboard();
//                        return hs.get(child);
//                    }
                    isNow = child.freeturn;
                    int temp=alphabeta(child, depth, alpha, beta, isNow);
                    if(val<temp)
                    {
                        val=temp;
                        currentState.setNext(child);
                    }
                    if(val>=beta)
                    {
                        break;
                    }
                    alpha=Integer.max(alpha, val);
                
            }
            System.out.println("OUT:");
            currentState.board.printboard();
            //hs.put(currentState, val);
            return val;
        }
        else
        {
            val=Integer.MAX_VALUE;
            State [] results=currentState.GetAllChildren();
            for(int i=0;i<results.length;i++)
            {
                boolean isNow;
                //int j=currentState.board.beginB+i-1;
                //if(currentState.board.grid[j] != 0)
                //{
                    State child=results[i];
//                    if(hs.containsKey(child))
//                    {
//                        System.out.println("Touching Void with B");
//                        child.board.printboard();
//                        return hs.get(child);
//                    }
                    isNow = !child.freeturn;
                    int temp=alphabeta(child, depth, alpha, beta, isNow);
                    if(val>temp)
                    {
                        val=temp;
                        currentState.setNext(child);
                    }
                    if(val<=alpha)
                    {
                        break;
                    }
                    beta=Integer.min(beta, val);
                //}
            }
            //hs.put(currentState, val);
            System.out.println("OUT:");
            currentState.board.printboard();
            return val;
        }
    }
    
    
}
