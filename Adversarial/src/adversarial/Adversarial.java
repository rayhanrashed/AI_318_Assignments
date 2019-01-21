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
public class Adversarial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        int n=6,m=4;
        Board board=new Board(n, m);
        SearchRunner sr=new SearchRunner(board);
        int result;
        result=sr.runsearch();
        System.out.println("Result is: " + result);
        // TODO code application logic here
    }
    
}
