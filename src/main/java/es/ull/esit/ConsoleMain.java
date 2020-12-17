package es.ull.esit;

import es.ull.esit.factories.CruiseShipFactory;
import es.ull.esit.factories.FreighterFactory;
import es.ull.esit.factories.OilTankerFactory;
import es.ull.esit.factories.TransportFactory;
import es.ull.esit.transports.Transport;
import es.ull.esit.utilities.CsvGenerator;
import es.ull.esit.utilities.Water;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ConsoleMain {

    public static final int N_TRANSPORTS = 2;
    private final ArrayList<Transport> transports = new ArrayList<>();

    public ConsoleMain() {
        generateRandomTransports(new CruiseShipFactory());
        generateRandomTransports(new FreighterFactory());
        generateRandomTransports(new OilTankerFactory());
    }

    public ArrayList<Transport> getTransports() {
        return transports;
    }

    private void generateRandomTransports(TransportFactory factory) {
        for(int i = 0; i < N_TRANSPORTS; i++) {
            double latitude = (Math.random() * 180.0) - 90.0;
            double longitude = (Math.random() * 360.0) - 180.0;

            Point2D[] coordinates = Water.getOceanCoordinates();

            assert coordinates != null;
            Point2D startLocation = coordinates[0];
            Point2D endLocation = coordinates[1];

            Transport t = factory.createTransport();

            t.setStartLocation(startLocation.getX(), startLocation.getY());
            t.setEndLocation(endLocation.getX(), endLocation.getY());
            t.setPath();

            transports.add(t);
        }
    }

    public static void main(String[] args) {
        ConsoleMain consoleMain = new ConsoleMain();

        ArrayList<Transport> transports = consoleMain.getTransports();

        // TODO: loop and update transports location
        for(Transport transportIterator : transports) {
            transportIterator.move();
        }

        // TESTING simple case
        String fileName = "./src/main/java/es/ull/esit/utilities/transports.csv";
        CsvGenerator.generateCsvFile(fileName, transports);



        String fileName1 = "./src/main/java/es/ull/esit/utilities/transports1.csv";
        CsvGenerator.generateCsvFile(fileName1, transports);
    }
}