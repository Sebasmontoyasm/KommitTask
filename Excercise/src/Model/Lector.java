/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * File reader
 * @author Juan Sebastian Montoya Acevedo
 * @date 09/04/2020
 */
public class Lector {


    public Lector() {
    }

    protected LinkedList<String> nodes = new LinkedList<>();
    LinkedList<String[]> relations = new LinkedList<>();
    /**
     *
     * @param nodeList nodes in the file.
     * @return number of posibility conections
     * @throws FileNotFoundException Don't Found File
     * @throws IOException Problem with JPanel.
     */
    public int getData(LinkedList<String> nodeList) throws FileNotFoundException, IOException{
        try {
            JFileChooser selectorFile = new JFileChooser("src\\Repository");
            selectorFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File","txt");
            selectorFile.setFileFilter(filter);
            selectorFile.showOpenDialog(new JPanel());

            File file = selectorFile.getSelectedFile();

            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);

            String[] gNodes;
            String line;

            int utility = 0;

            while((line=br.readLine())!=null){
                gNodes = line.split(",");
                if(this.nodes.isEmpty()){
                    nodes.add(gNodes[0]);
                    nodes.add(gNodes[1]);
                    relations.add(gNodes);
                }else{
                    for (String node: gNodes) {
                        if(nodes.indexOf(node) == -1){
                            nodes.add(node);
                        }
                    }
                    relations.add(gNodes);
                }

               utility = utility + 2; 
            }

            return utility;
        
        } catch (Exception e) {
            System.out.println("Error with loading file");
            return 0;
        }
        
    }
    /**
     * Create logical matrix 
     * @return graph logical matrix
     */
    public int[][] createGraph() {
        int[][] m = new int[nodes.size()][nodes.size()];
        int i;
        int j;
       
        for (int[] m1 : m) {
            for (int c = 0; c < m1.length; c++) {
                m1[c] = 0;
            }
        }
        
        for (String[] r: relations) {
            i = nodes.indexOf(r[0]);
            j = nodes.indexOf(r[1]);
            
            m[i][j] = 1;
            m[j][i] = 1;       
        }
        
        return m;       
    }
}
