/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Controller class
 * @author Juan Sebastian Montoya Acevedo
 * @date 09/04/2020
 */
public class Excercise {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException for lector funtion
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Lector L = new Lector();
        LinkedList<String> nodesList = new LinkedList<>();
        
        int utility = L.getData(nodesList);
        int[][] logicM = L.createGraph();
        
        /*
        for (int i = 0; i < logicM.length; i++) {
            for (int j = 0; j < logicM[i].length; j++) {
                 System.out.println("M["+i+"]["+j+"]: "+logicM[i][j]);
            }
        }
        */
        
        Funtions F = new Funtions(utility,logicM);
        System.out.println(F.SolveForAll());
        
       
    }
    
}
