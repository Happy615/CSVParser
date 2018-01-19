/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvparser;

import java.io.File;

/**
 *
 * @author happy
 */
public class CSVParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file = new File("C:\\Users\\sun\\Documents\\NetBeansProjects\\xlsscript\\text.csv");
        csvparser.CSVFileReader reader = new csvparser.CSVFileReader(file);
        if (args.length == 0){
            printUsage();
            System.exit(0);
        }
        if (args[0].equalsIgnoreCase("testprint")){
            reader.testprint();            
            System.exit(0);
        }
        if (args[0].equalsIgnoreCase("withindex")){
            if(args.length < 5){
                printUsage();
                System.exit(2);
            }
            reader.computeDaytotal(Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4]));
            reader.save();
            System.exit(0);
        }
        
        if (args[0].equalsIgnoreCase("settings")){
            if(args.length < 2){
                printUsage();
                System.exit(2);
            }
            //TODO
            System.exit(0);
        }
        
        printUsage();
        System.exit(1);
    }

    private static void printUsage() {
        System.out.println("testprint");
        System.out.println("withindex [date] [nbox] [totalprice] [daytotal]");
        System.out.println("settings [filename]");
        System.out.println("\tfilename format:");
        System.out.println("\t\tfirst line     : separator regex");
        System.out.println("\t\tfollowing lines: field name");
    }
    
}
