package es.ull.esit;

import es.ull.esit.builder.TransportBuilder;
import es.ull.esit.factories.CruiseShipFactory;
import es.ull.esit.factories.FreighterFactory;
import es.ull.esit.factories.OilTankerFactory;
import es.ull.esit.factories.TransportFactory;
import es.ull.esit.transports.Transport;
import es.ull.esit.utilities.CsvGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleMain {

    private static final Logger LOGGER = Logger.getLogger(ConsoleMain.class.getName());

    public static final int N_TRANSPORTS = 3;
    protected static final List<Transport> transports = new ArrayList<>();

    private static void generateRandomTransports(TransportFactory factory) {
        for(int i = 0; i < N_TRANSPORTS; i++) {

            TransportBuilder builder = new TransportBuilder(factory);

            transports.add(builder.getBuild());
        }
    }

    // TODO: menu for manual and automatic mode

    public static void main(String[] args) {

        generateRandomTransports(new CruiseShipFactory());
        generateRandomTransports(new FreighterFactory());
        generateRandomTransports(new OilTankerFactory());

        // Storing initial coordinates in CSV
        String fileName = "./resources/transportsInput.csv";
        CsvGenerator.generateCsvFile(fileName, transports);


        // Simulating transport tracking
        try {
            boolean iterate = true;

            while(iterate) {

                iterate = false;

                for(Transport t : transports) {
                	LOGGER.info(t.toString());
                	if(t.move()) iterate = true;
                }

                LOGGER.info("\n");
                Thread.sleep(3000);
            }
        }
        catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Thread.currentThread().interrupt();
        }

        // Storing final coordinates in CSV
        String fileName1 = "./resources/transportsOutput.csv";
        CsvGenerator.generateCsvFile(fileName1, transports);
    }
}