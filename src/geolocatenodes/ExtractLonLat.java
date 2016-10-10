/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geolocatenodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mad_r
 */
public class ExtractLonLat {

    public ExtractLonLat() throws IOException {

        System.out.println("In Long Lat");
        String inp = "";
        File file = new File("Locations.txt");
        try {
//            System.out.println("In scanner");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                inp += scan.nextLine() + " ";
            }
        } catch (FileNotFoundException f) {
        }

        System.out.println("File inp string is " + inp);
        Pattern pattern
                = Pattern.compile("(?<!\\d)([-+]?(?:[1-8]?\\d(?:\\.\\d+)?|90(?:\\.0+)?)),\\s*([-+]?(?:180(?:\\.0+)?|(?:(?:1[0-7]\\d)|(?:[1-9]?\\d))(?:\\.\\d+)?))(?!\\d)");
        Matcher match = pattern.matcher(inp);
        while (match.find()) {
            System.out.println("Lon Lat found: " + match.group());
            try {
                String filename = "LonLat.txt";
                try (FileWriter fw = new FileWriter(filename, true)) {
                    //                    fw.write("{lat: ");
                    //                    fw.append(match.group() + "},\n");
                    fw.write(match.group() +"\n");
                }
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }
//        System.out.println("Finished");
//        Locate location = new Locate();

    }

}
