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

    public static final int N_TRANSPORTS = 5;
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
            //double latitude = (Math.random() * 180.0) - 90.0;
            //double longitude = (Math.random() * 360.0) - 180.0;

            Point2D coordinates = Water.getOceanCoordinates();
            assert coordinates != null;
            double latitude = coordinates.getX();
            double longitude = coordinates.getY();

            Transport t = factory.createTransport();

            t.setCoordinates(latitude, longitude);

            transports.add(t);
        }
    }

    public static void main(String[] args) {
        ConsoleMain consoleMain = new ConsoleMain();

        String fileName = "./src/main/java/es/ull/esit/utilities/transports.csv";
        CsvGenerator.generateCsvFile(fileName, consoleMain.getTransports());
    }
}