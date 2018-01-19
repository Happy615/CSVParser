/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvparser;

/**
 *
 * @author Happy
 */
public class CSVLine {
    
    private String[] splitline;
    private final String separator;
    
    public CSVLine(String line, String separator){
        splitline = line.split(separator);
        this.separator = separator;
    }
    
    /**
     * NOTE: ignores array out of bounds
     * @param index
     * @return 
     */
    public String get(int index){
        return (index < splitline.length)? splitline[index]: "";
    }
    
    
    /**
     * NOTE: ignores array out of bounds
     * @param index
     * @param text
     */
    public void set(int index, String text){
        if (index < splitline.length)
            splitline[index] = text;
    }
    
    @Override
    public String toString(){
        String result = splitline[0];
        for(int i = 1; i< splitline.length; i++){
            result += separator +splitline[i];
        }
        return result;
    }
    
    public void testPrint(){
        System.out.printf("%5s = %s%n", "index", "value");
        for(int i = 0; i < splitline.length; i++ ){
            System.out.printf("%5d = %s%n", i, this.get(i));
        }
    }
    
    public int size(){
        return splitline.length;
    }
}
