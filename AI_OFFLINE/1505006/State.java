/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_offline;

/**
 *
 * @author Rayhan
 */
public class State {
    int Missionary;
    int Cannibal;
    int direction;
    static int Miss;
    static int Cann;
    int hashval;
   
    public State(int Missionary, int Cannibal,int direction) {
        this.Missionary = Missionary;
        this.Cannibal = Cannibal;
        this.direction = direction;
    }

    public State(int Missionary, int Cannibal) {
        this.Missionary = Missionary;
        this.Cannibal = Cannibal;
        this.direction=1;
    }
    
    @Override
    public int hashCode()
    {
        int hasha=1;
        hasha=hasha*31+this.Cannibal;
        hasha=hasha*37+this.Missionary;
        hasha=hasha+(2-this.direction)*7;
        return hasha;
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
        if (this.Missionary != other.Missionary) {
            return false;
        }
        if (this.Cannibal != other.Cannibal) {
            return false;
        }
        if (this.direction != other.direction) {
            return false;
        }
        return true;
    }
    
}
