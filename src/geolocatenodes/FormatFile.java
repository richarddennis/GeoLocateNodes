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
public class FormatFile {

    public FormatFile() throws IOException {

        String inp = "";
        File file = new File("IpAddresses.txt");
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                inp += scan.nextLine() + " ";
            }
        } catch (FileNotFoundException f) {
        }

        System.out.println("File inp string is " + inp);
        Pattern pattern
                = Pattern.compile("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
        Matcher match = pattern.matcher(inp);
        while (match.find()) {
            System.out.println("IP found: " + match.group());
            try {
                String filename = "IP.txt";
                try (FileWriter fw = new FileWriter(filename, true)) {
                    fw.write(match.group() + "\n");
                }
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }
//        Locate location = new Locate();

    }

}
