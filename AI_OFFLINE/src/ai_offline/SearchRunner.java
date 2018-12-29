/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_offline;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.lang.*;

/**
 *
 * @author Rayhan
 */
public class SearchRunner {
       long RunningTime;
       long StartingTime;
       int MaxNodeExpanded;
       int NodeExpanded;
       int NodeExplored;
       int BoatCapacity;
       int TotalCannibal;
       int TotalMissionary;
       Hashtable<State, Integer> OpenList;
       Hashtable<State, Integer> CloseList;
       Queue<Node> RunningQueue;
       Stack<Node> RunningStack;
       State finalState;
       State startingState;
    public SearchRunner(long RunningTime, int MaxNodeExpanded,int BoatCapacity,int Missionary,int Cannibals) {
        this.RunningTime = RunningTime;
        this.MaxNodeExpanded = MaxNodeExpanded;
        NodeExpanded=0;
        this.BoatCapacity=BoatCapacity;
        OpenList=new Hashtable<>(1000);
        CloseList=new Hashtable<>(1000);
        RunningQueue=new LinkedList<>();
        RunningStack=new Stack<>();
        finalState=new State(0,0,-1);
        startingState=new State(Missionary,Cannibals,1);
        TotalCannibal=startingState.Cannibal;
        TotalMissionary=startingState.Missionary;
    }
    public boolean isvalid(State s)
    {
        int therecanns = TotalCannibal - s.Cannibal;
        int theremiss = TotalMissionary - s.Missionary;
        //System.out.println(s.Missionary + " " + s.Cannibal + " " + theremiss + " " + therecanns);
        if (s.Missionary < 0 || s.Cannibal < 0 || theremiss < 0 || therecanns < 0) {
            return false;
        }
        if (s.Cannibal > s.Missionary && s.Missionary > 0) {
            return false;
        }
        if (therecanns > theremiss && theremiss > 0) {
            return false;
        }
        return true;
    }
    
    public Node DoBFS()
    {
       StartingTime=System.currentTimeMillis();
       OpenList.clear();
       CloseList.clear();
       RunningQueue.clear();
       NodeExpanded=0;
       NodeExplored=0;
       Node x=new Node(startingState, 0);
       x.parent=null;
       RunningQueue.add(x);
       Node Result=new Node(null,Integer.MAX_VALUE);
       //System.out.println("Here");
       OpenList.put(startingState, new Integer(0));
       while (!RunningQueue.isEmpty())
       {
           NodeExpanded++;
           if(NodeExpanded>MaxNodeExpanded)
           {
               System.out.println("Maximum Node Expansion Limit Reached!! ");
               return null;
           }
           if((System.currentTimeMillis()-StartingTime)>30000)
           {
               System.out.println("Maximum Time Limit Reached!! ");
               return null;
           }
            //System.out.println("Here");
            Node current=RunningQueue.remove();
            //System.out.println(current.nodeState.Cannibal);
            int cann=current.nodeState.Cannibal;
            int miss=current.nodeState.Missionary;
            int dir=current.nodeState.direction;
            //System.out.println(miss+"."+cann+"."+dir);
            

            
            for(int i=0;i<=BoatCapacity;i++)
            {   for(int j=0;i+j<=BoatCapacity;j++)
                {
                    //System.out.println(i+"i , j"+j);
                    if((i+j)==0)continue;
                    int heremiss=miss-i*(dir);
                    int herecanns=cann-j*dir;
                    State temp=new State(heremiss, herecanns, dir*(-1));
                    if(!isvalid(temp))
                    {
                        continue;
                    }
                    //System.out.println(heremiss+" "+herecanns);
                    NodeExplored++;
                    if(OpenList.containsKey(temp) || CloseList.containsKey(temp))
                    {
                        continue;
                    }
                    else
                    {
                        OpenList.put(temp, 1);
                        Node another=new Node(temp, current.cost+1);
                        RunningQueue.add(another);
                        
                        another.parent=current;
                        if(another.nodeState.equals(finalState))
                        {
                            Result=another;    
                            System.out.print("Node Expanded for BFS:");
                            System.out.println(this.NodeExpanded);
                            System.out.println("Node Explored for BFS:" + NodeExplored + "\nTime Taken: " + (System.currentTimeMillis() - StartingTime));
                            //System.out.println("Milliseconds!!");
                            return Result;

                        }
                    }
                }
            }
            OpenList.remove(current.nodeState);
            CloseList.put(current.nodeState, 1);
        }
            System.out.print("Node Expanded for BFS:");
            System.out.println(this.NodeExpanded);
            System.out.println("Node Explored for BFS:" + NodeExplored + "\nTime Taken: " + (System.currentTimeMillis() - StartingTime));
            System.out.println("But No Solution Found!!");
            return null;
    }
    
    public Node DoDFS()
    {
        
        Node Result=new Node(null,Integer.MAX_VALUE);
        OpenList.clear();
        CloseList.clear();
        RunningStack.clear();
        Node x= new Node(startingState, 0);
        x.parent=null;
        RunningStack.push(x);
        NodeExpanded=0;
        NodeExplored=0;
        OpenList.put(startingState, new Integer(0));
        StartingTime=System.currentTimeMillis();
        
        while(!RunningStack.empty())
        {
           NodeExpanded++;
           if(NodeExpanded>MaxNodeExpanded)
           {
               System.out.println("Maximum Node Expansion Limit Reached!! ");
               return null;
           }
           if((System.currentTimeMillis()-StartingTime)>30000)
           {
               System.out.println("Maximum Time Limit Reached!! ");
               return null;
           }
            
            
            Node current=RunningStack.peek();
            RunningStack.pop();
            //System.out.println("Current : "+current.nodeState.Missionary+". "+current.nodeState.Cannibal);
            int cann=current.nodeState.Cannibal;
            int miss=current.nodeState.Missionary;
            int dir=current.nodeState.direction;
            
            for(int i=0;i<=BoatCapacity;i++)
            {   for(int j=0;i+j<=BoatCapacity;j++)
                {
                    //System.out.println(i+"i , j"+j);
                    if((i+j)==0)continue;
                    int heremiss=miss-i*(dir);
                    int herecanns=cann-j*dir;
                    State temp=new State(heremiss, herecanns, dir*(-1));
                    if(!isvalid(temp))
                    {
                        continue;
                    }
                    //System.out.println(heremiss+" "+herecanns);
                    NodeExplored++;
                    if(OpenList.containsKey(temp) || CloseList.containsKey(temp))
                    {
                        continue;
                    }
                    else
                    {
                        OpenList.put(temp, 1);
                        Node another = new Node(temp, current.cost + 1);
                        RunningStack.push(another);
                        another.parent = current;
                        if (another.nodeState.equals(finalState)) {
                            
                            Result=another;    
                            System.out.print("Node Expanded for DFS:");
                            System.out.println(this.NodeExpanded);
                            System.out.println("Node Explored for DFS:" + NodeExplored + "\nTime Taken: " + (System.currentTimeMillis() - StartingTime));
                            //System.out.println("Milliseconds!!");
                            return Result;
                             
                        }
                    }
                }
            }
            
            OpenList.remove(current.nodeState);
            CloseList.put(current.nodeState, 1);
        }


            System.out.print("Node Expanded for DFS:");
            System.out.println(this.NodeExpanded);
            System.out.println("Node Explored for DFS:" + NodeExplored + "\nTime Taken: " + (System.currentTimeMillis() - StartingTime));
            System.out.println("But No Solution Found!!");
            return null;
    }
    
    
    void RecDFS(State state)
    {
        
    }

    void printpath(Node result) {
        System.out.println("------------------------------------------------------------------");
        while(result!=null)
        {
            System.out.println(result.nodeState.Missionary+" "+result.nodeState.Cannibal);
            result=result.parent;
        }
                System.out.println("------------------------------------------------------------------");

    }
       
       
}
