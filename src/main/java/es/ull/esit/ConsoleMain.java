package es.ull.esit;

import es.ull.esit.builder.TransportBuilder;
import es.ull.esit.factories.CruiseShipFactory;
import es.ull.esit.factories.FreighterFactory;
import es.ull.esit.factories.OilTankerFactory;
import es.ull.esit.factories.TransportFactory;
import es.ull.esit.transports.Transport;
import es.ull.esit.utilities.CsvGenerator;

import java.util.ArrayList;

public class ConsoleMain {

    public static final int N_TRANSPORTS = 1;
    public static final ArrayList<Transport> transports = new ArrayList<>();

    private static void generateRandomTransports(TransportFactory factory) {
        for(int i = 0; i < N_TRANSPORTS; i++) {

            TransportBuilder builder = new TransportBuilder(factory);

            transports.add(builder.getBuild());
        }
    }

    public static void main(String[] args) {

        generateRandomTransports(new CruiseShipFactory());
        generateRandomTransports(new FreighterFactory());
        generateRandomTransports(new OilTankerFactory());

        // Storing initial coordinates in CSV
        String fileName = "./src/main/java/es/ull/esit/utilities/transports.csv";
        CsvGenerator.generateCsvFile(fileName, transports);


        // Simulating transport tracking
        try {
            boolean iterate = true;

            while(iterate) {

                iterate = false;

                for(Transport t : transports) {
                    System.out.println(t);
                    if(t.move()) iterate = true;
                }

                System.out.println('\n');
                Thread.sleep(3000);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Storing final coordinates in CSV
        String fileName1 = "./src/main/java/es/ull/esit/utilities/transports1.csv";
        CsvGenerator.generateCsvFile(fileName1, transports);
    }
}