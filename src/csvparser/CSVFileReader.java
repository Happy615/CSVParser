/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvparser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Happy
 */
public class CSVFileReader {
//    public enum Column {ID, check1, date, name, article, Nbox, Nperbox, check2, cadprice, totalprice, check3, daytotal};
    private File file;
    private ArrayList<CSVLine> lines = new ArrayList();
    private String separator= ",";
    
    public CSVFileReader(File file){
        this.file = file;
        load();
    }
    
    private void load(){
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                lines.add(new CSVLine(line, separator));
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVFileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CSVFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void save(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) { //append = false
            for(CSVLine line: lines) {
                bw.write(line.toString());
                bw.newLine();
            }
        }   catch (IOException ex) {
            Logger.getLogger(CSVFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void computeDaytotal(int dateindex, int nboxindex, int totalpriceindex, int daytotalindex){
        String lastdate = "";
        float mytotal = 0;
        CSVLine previousline = new CSVLine("dummy\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t", separator);
        for(CSVLine line: lines){
            line.set(daytotalindex, "");
            if(line.get(totalpriceindex).equals("") || line.get(nboxindex).startsWith("-")) continue;
            if(line.get(dateindex).equalsIgnoreCase(lastdate)){
                mytotal += Float.parseFloat(line.get(totalpriceindex));
            } else {
                previousline.set(daytotalindex, String.format("%.2f", mytotal));
                mytotal = Float.parseFloat(line.get(totalpriceindex));
            }
            lastdate = line.get(dateindex);
            previousline = line;
        }
        previousline.set(daytotalindex, String.format("%.2f", mytotal));
    }
    
    public void testprint(){
        testprint(1);
    }
    
    private int[] sort(int[] indexes){
        // TODO sort
        // TODO remove out of index
        // TODO remove duplicates
        return indexes;
    }
    
    public void testprint(int... indexes){
        indexes = sort(indexes);
        
        int max = 0;
        for (int i = 0; i < indexes.length ; i++){
            max = Math.max(max, lines.get(indexes[i]).size());
        }
        
        // print table head
        System.out.printf("%5s ", "index");
        for(int j=0; j < indexes.length; j++){
            System.out.printf("line %4d ", j);
        }
        System.out.println();
        
        // print table content
        for(int i=0; i < max; i++){
            System.out.printf("%5d ", i);
            for(int j=0; j < indexes.length; j++){
                System.out.printf("%9s ", lines.get(i).get(j));
            }
            System.out.println();
        }
    }

    /**
     * @return the separator
     */
    public String getSeparator() {
        return separator;
    }

    /**
     * @param separator the separator to set
     */
    public void setSeparator(String separator) {
        this.separator = separator;
    }
    
    public CSVLine getLine(int index){
        return index < lines.size() ? lines.get(index) : null;
    }
    
    public void insertLine(int index, CSVLine line){
        if (index < lines.size())
            lines.add(index, line);
        else
            lines.add(line);
    }
    
    public void appendLine(CSVLine line){
        lines.add(line);
    }
}