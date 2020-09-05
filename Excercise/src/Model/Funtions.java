/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.LinkedList;

/**
 * Logical class
 * @author Juan Sebastian Montoya Acevedo
 * @date 09/04/2020
 */
public class Funtions {
    
    private final LinkedList<Integer> childNodes = new LinkedList<>();
    private final int utility;
    private final int[][]m;
    

    
    
    public Funtions(int util,int[][] m){
        this.utility=util;
        this.m = m;
    }
   
    public String SolveForAll(){
        for (int i = 0; i < m.length; i++) {
           String sol = SolveFor1(i);
           
           if(sol.equalsIgnoreCase("Si")){
               return "Si";
           }
        }
        return "No";
    }
     
     /**
      * 
      * Function looking for redundancy in the graph.
      * @param pivot target node
      * @return found redundancy 
      */
     public String SolveFor1(int pivot){
        System.out.println("--------------------------------------------"); 
        int before = pivot; // which is the previous node so it doesn't go back
        int ind = pivot; // move for matrix
        int aux; // conteiner
        int[][] mc = new int[m.length][m.length];
         
        for (int i = 0; i < mc.length; i++) { //reset mc
            System.arraycopy(m[i], 0, mc[i], 0, mc[i].length);
         }

        int util = this.utility;
        
        System.out.println("--------------------------------");
        System.out.println("pivot: "+pivot);
         
        while(util != 0){ //Don't finish all possible courses.
            /** 
             * Found and put childen nodes in the matrix.
             */
           
            for (int i = 0; i < mc[ind].length; i++) { // Get row in the matrix
                
                if(mc[ind][pivot] == 1 && before != pivot){ // exit condition
                    
                    mc[ind][pivot] = -1;
                    mc[pivot][ind] = -1;
                    
                    System.out.println("["+ind+"-"+pivot+"]"+" ["+pivot+"-"+ind+"]");
                    
                    return "Si";   
                }else if(before != i && m[ind][i] == 1 ){ // Add childs nodes 
                    System.out.println("------------------------------");
                    System.out.println("child: "+i);
                    childNodes.add(i);
                }
            }
            
            if(!childNodes.isEmpty()){ // It can continue, it have some child and change root node.
                util=util-2;
                aux=ind;
                ind = childNodes.removeLast();            
                mc[aux][ind] = -1;
                mc[ind][aux] = -1;
            
                before = aux;
            }
            else{
                //showCourse(mc);
                return "No";
            }
        }
        //showCourse(mc);
        return "No";
    }
    /**
     * Show, How many courses it take
     * @param mc solve course 
     */
    private void showCourse(int[][] mc) {
         for (int i = 0; i < mc.length; i++) {
            for (int j = 0; j < mc[i].length; j++) {
                System.out.println("MC["+i+"]["+j+"]: "+mc[i][j]);
            }
        }
    }
}
