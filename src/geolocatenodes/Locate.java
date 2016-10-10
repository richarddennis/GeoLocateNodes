/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geolocatenodes;

/**
 *
 * @author mad_r
 */
import java.io.File;
import java.io.IOException;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import geolocatenodes.ServerLocation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Locate {

//    public static void main(String[] args) throws IOException {
//    public static void test() { //     public Locate() throws IOException {
    public ServerLocation getLocation(String ipAddress) {
        File file = new File(
                "GeoLiteCity.dat");
        return getLocation(ipAddress, file);

    }

    public ServerLocation getLocation(String ipAddress, File file) {

        ServerLocation serverLocation = null;

        try {

            serverLocation = new ServerLocation();

            LookupService lookup = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);
            Location locationServices = lookup.getLocation(ipAddress);

            serverLocation.setCountryCode(locationServices.countryCode);
            serverLocation.setCountryName(locationServices.countryName);
            serverLocation.setRegion(locationServices.region);
            serverLocation.setRegionName(regionName.regionNameByCode(
                    locationServices.countryCode, locationServices.region));
            serverLocation.setCity(locationServices.city);
            serverLocation.setPostalCode(locationServices.postalCode);
            serverLocation.setLatitude(String.valueOf(locationServices.latitude));
            serverLocation.setLongitude(String.valueOf(locationServices.longitude));

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return serverLocation;

    }

    static class convert extends Locate {

        public convert() {

//            System.out.println("in test");
            Locate obj = new Locate();
            String file = "IP.txt";

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
//                System.out.println(line);

                    ServerLocation location = obj.getLocation(line);
                    System.out.println(location);

                    try {
                        String filename = "Locations.txt";
                        try (FileWriter fw = new FileWriter(filename, true)) {
                            fw.write(location + "\n");
                        }
                    } catch (IOException ioe) {
                        System.err.println("IOException: " + ioe.getMessage());
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Locate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

