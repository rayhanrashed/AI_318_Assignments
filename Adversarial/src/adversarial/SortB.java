package adversarial;

import java.util.Comparator;

/**
 *
 * @author Rayhan
 */
public class SortB implements Comparator<State>{

    @Override
    public int compare(State o1, State o2) {
        if((o1.board.grid[o1.board.mancalahforB]- o1.board.grid[o1.board.mancalahforA])>(o2.board.grid[o2.board.mancalahforB]- o2.board.grid[o2.board.mancalahforA]))
        {
            return -1;
        }
        else if((o1.board.grid[o1.board.mancalahforB]- o1.board.grid[o1.board.mancalahforA])<(o2.board.grid[o2.board.mancalahforB]- o2.board.grid[o2.board.mancalahforA]))
        {
            return 1;
        }
        
        return 0;
    }
    
}
