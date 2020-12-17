package es.ull.esit.utilities;

import es.ull.esit.transports.Transport;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @class CsvGenerator
 * @brief Class to generate a CSV file
 *
 * @details This class writes a CSV with each transport type and its location.
 *
 */
public class CsvGenerator
{
    /**
     * @brief Private constructor to avoid instantiation of static utility class
     */
    private CsvGenerator() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @brief Generates a CSV file
     * @param fileName -> file path name
     * @param transports -> array of transports to be written
     */
    public static void generateCsvFile(String fileName, List<Transport> transports)
    {
        try (FileWriter writer = new FileWriter(fileName)) {

            writer.append("TYPE");
            writer.append(';');
            writer.append("LATITUDE");
            writer.append(';');
            writer.append("LONGITUDE");
            writer.append('\n');

            for (Transport t : transports) {
                writer.append(t.getType());
                writer.append(';');
                writer.append(Double.toString(t.getLatitude()));
                writer.append(';');
                writer.append(Double.toString(t.getLongitude()));
                writer.append('\n');
            }

            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
