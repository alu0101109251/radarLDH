package es.ull.esit.utilities;

import es.ull.esit.products.Transport;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvGenerator
{
    public static void generateCsvFile(String fileName, ArrayList<Transport> transports)
    {
        try
        {
            FileWriter writer = new FileWriter(fileName);

            writer.append("TIPO");
            writer.append(';');
            writer.append("LATITUD");
            writer.append(';');
            writer.append("LONGITUD");
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
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
