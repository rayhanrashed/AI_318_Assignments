/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adversarial;

import java.util.Comparator;

/**
 *
 * @author Rayhan
 */
public class SortA implements Comparator<State>{

    @Override
    public int compare(State o1, State o2) {
        if((o1.board.grid[o1.board.mancalahforA]- o1.board.grid[o1.board.mancalahforB])>(o2.board.grid[o2.board.mancalahforA]- o2.board.grid[o2.board.mancalahforB]))
        {
            return -1;
        }
        else if((o1.board.grid[o1.board.mancalahforA]- o1.board.grid[o1.board.mancalahforB])<(o2.board.grid[o2.board.mancalahforA]- o2.board.grid[o2.board.mancalahforB]))
        {
            return 1;
        }
        
        return 0;
    }
    
}
