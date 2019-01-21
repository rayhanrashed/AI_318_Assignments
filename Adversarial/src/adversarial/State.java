/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adversarial;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Rayhan
 */
public class State {
    Board board;
    State nextState;
    boolean freeturn;
    boolean TURNOFALPHA;
    

    public State(Board board) throws CloneNotSupportedException {
        this.board = board.retcopy();
        nextState=null;
        freeturn=false;
    }
    public State GetChildState() throws CloneNotSupportedException
    {
        State s=new State(board);
        s.freeturn=false;
        s.TURNOFALPHA= this.TURNOFALPHA ^ this.TURNOFALPHA;
        return null;
    }
    public void setfreeturn()
    {
        this.freeturn=true;
    }
    public void setNext(State next)
    {
        this.nextState=next;
    }
    public State[] GetAllChildren() throws CloneNotSupportedException
    {
        //State[] children=new State[6];
        int[] mark=new int[7];
        int[] taken=new int[7];
        for(int i=1;i<=6;i++)
        {
            mark[i]=0;
            taken[i]=0;
        }
        int target=this.board.mancalahforB;
        int bg=this.board.beginB;
        if(this.TURNOFALPHA)
        {
            target=this.board.mancalahforA;
            bg=this.board.beginA;
        }
        int ta=0;
        int total=0;
        for(int s=bg;s<target;s++)
        {
            if(this.board.grid[s]==0)
            {
                continue;
            }
            total++;
            if(s+this.board.grid[s]==target)
            {
                mark[ta++]=s-bg+1;
                taken[s-bg+1]=1;
            }
        }
        int prod=ta;
//        for(int s=bg;s<target;s++)
//        {
//            if (taken[s - bg + 1] != 1)
//            {
//
//                if (this.TURNOFALPHA && this.board.belongtoplayerA(s + this.board.grid[s]))
//                {
//                    if (this.board.grid[s + this.board.grid[s]] == 0) {
//                        mark[ta++] = s - bg + 1;
//                        taken[s - bg + 1] = 1;
//                    }
//                } 
//                else if (!this.TURNOFALPHA && this.board.belongtoplayerB(s + this.board.grid[s]))
//                {
//                    if (this.board.grid[s + this.board.grid[s]] == 0) {
//                        mark[ta++] = s - bg + 1;
//                        taken[s - bg + 1] = 1;
//                    }
//                }
//
//            }
//              
//        }
        for(int s=bg;s<target;s++)
        {
            if(this.board.grid[s]!=0 && taken[s-bg+1]!=1)
            {
                taken[s-bg+1]=1;
                mark[ta++]=s-bg+1;
            }
        }
        State[] children=new State[ta];
        
//        System.out.println("Ta: "+ta);
//        for(int i=0;i<ta;i++)
//        {
//            
//            System.out.print(mark[i] + " ");
//        }
//        System.out.println("");
        for(int i=0;i<ta;i++)
        {
            
            children[i]=GetIthState(mark[i]);
        }
        if(ta==prod)
        {
            return children;
        }
        if(this.TURNOFALPHA)
        {
            Arrays.sort(children, prod, ta-1, new SortA());
        }
        else
        {
            Arrays.sort(children, prod, ta-1, new SortB());
        }
        //System.out.println("");
        return children;
    }
    public State GetIthState(int i) throws CloneNotSupportedException
    {
        State retstate=new State(this.board);
        if(this.TURNOFALPHA)
        {
            retstate.TURNOFALPHA=false;
            int x=this.board.grid[i];
            int t;
            for(t=i+1;x>=1;t++,x--)
            {
               if(t==this.board.mancalahforB)
               {
                   t=this.board.beginA;
               }
               retstate.board.grid[t]++;
            }
            retstate.board.grid[i]=0;
            t--;
            if(t==this.board.mancalahforA)
            {
                retstate.freeturn=true;
                retstate.TURNOFALPHA=true;
            }
            if(retstate.board.belongtoplayerA(t))
            {
               int opp=retstate.board.getopposite(t);
               if(retstate.board.grid[t]== 1)
               {
                   retstate.board.grid[retstate.board.mancalahforA]+=(1+retstate.board.grid[opp]);
                   retstate.board.grid[opp]=0;
                   retstate.board.grid[t]=0;
               }
            }

        }
        else
        {
            retstate.TURNOFALPHA=true;
            int j=i+retstate.board.n+1;
            
            int x = this.board.grid[j];
            int t = 0;
            for (t = j + 1; x >= 1; t++, x--) {
                
                if(t == retstate.board.mancalahforA)
                {
                    t++;
                }
                
                retstate.board.grid[t]++;
                
                if (t == this.board.mancalahforB) {
                    t = 0;
                }
                
            }
            t--;
            retstate.board.grid[j]=0;
            if (t == this.board.mancalahforB) {
                retstate.freeturn = true;
                retstate.TURNOFALPHA = false;
            }
            if (this.board.belongtoplayerB(t)) {
                int opp = this.board.getopposite(t);
                if (retstate.board.grid[t] == 1) {
                    retstate.board.grid[retstate.board.mancalahforB] += (1+retstate.board.grid[opp]);
                    retstate.board.grid[opp] = 0;
                    retstate.board.grid[t]=0;
                }
            }

            
        }
        //System.out.println("Parent"+ this.TURNOFALPHA +"- Child -" + retstate.TURNOFALPHA);
        //this.board.printboard();
        //retstate.board.printboard();
        return retstate;
        //return null;
    }
    
    
    public boolean isTerminal()
    {
        boolean test=true;
        int x=1,y=1;
        for(int i=1;i<=this.board.n;i++)
        {
            if(this.board.grid[i]!= 0)
            {
                x=0;
                break;
            }
        }
        int rev=this.board.size;
        for(int i=1;i<=this.board.n;i++)
        {
            if(this.board.grid[rev - i]!= 0)
            {
               y=0;
               break;
            }
        }
        
        if(x==1 || y==1)
        {
            return true;
        }
        return false;
    }
    
    public int utility()
    {
        if(isTerminal())
        {
            int sum=0;
            for(int i=1;i<=this.board.n+1;i++)
            {
                sum+=this.board.grid[i];
            }
            //System.out.println("Returning Sum: "+ sum);
            return sum;
            
        }
        else
        {
            return 0;
        }
    }
    @Override
    public int hashCode()
    {
        int x;
        x = Arrays.hashCode(this.board.grid);
       if(this.TURNOFALPHA)
       {
           x=x ^ ( 1 << 5) ;
           x+=31;
       }
       if(this.freeturn)
       {
           x=x ^ (1 << 20);
           x+=31;
       }
       return x;
        
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (this.freeturn != other.freeturn) {
            return false;
        }
        if (this.TURNOFALPHA != other.TURNOFALPHA) {
            return false;
        }
        if (!Objects.equals(this.board, other.board)) {
            return false;
        }
        return true;
    }
}
