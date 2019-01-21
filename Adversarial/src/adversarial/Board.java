/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adversarial;

/**
 *
 * @author Rayhan
 */
public class Board implements Cloneable{
    int size;
    int n;
    int grid[];
    int stoneperbox;
    int mancalahforA;
    int mancalahforB;
    int beginA;
    int beginB;
    public Board()
    {
        
    }
    public Board retcopy()
    {
        Board b=new Board();
        b.size=this.size;
        b.beginA=this.beginA;
        b.beginB=this.beginB;
        b.mancalahforA=this.mancalahforA;
        b.mancalahforB=this.mancalahforB;
        b.stoneperbox=this.stoneperbox;
        b.n=this.n;
        b.grid=new int[b.size +1];
        for(int i=0;i<=b.size;i++)
        {
            b.grid[i]=this.grid[i];
        }
        return b;
    }
    public Board(int n,int m) {
        this.n=n;
        this.stoneperbox=m;
        this.size = 2*n+2;
        this.grid = new int[size+1];
        for(int i=1;i<=size;i++)
        {
            grid[i]=stoneperbox;
            if( i % (n+1) == 0)
            {
                grid[i]=0;
            }
        }
        mancalahforA=n+1;
        mancalahforB=2*mancalahforA;
        beginA=1;
        beginB=n+2;
    }
    int getopposite(int i)
    {
        int val= this.size - i;
        return val;
    }
    boolean belongtoplayerA(int i)
    {
        return i>=1 && i<=this.n;
    }
    boolean belongtoplayerB(int i)
    {
        return i>=this.n+2 && i<this.size;
    }
    
    void printboard()
    {
        for(int i=1;i<=this.size;i++)
        {
            System.out.print(this.grid[i] +" ");
        }
        System.out.println("");
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
    public boolean equals(Board b)
    {
        for(int i=1;i<=size;i++)
        {
            if(this.grid[i] != b.grid[i])
            {
                return false;
            }
        }
        return true;
    }
    
}
