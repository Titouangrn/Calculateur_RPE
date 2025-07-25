package Modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReaderDemo {
    public static void main(String[] args) {

        // Chemin du CSV
        String csvFile = "sample.csv";

        // Liste pour stocker les données
        List<List<String>> data = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(csvFile))) {
            // Lis chaque ligne du fichier
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Sépare la ligne avec les virgule et la convertie en liste
                String[] values = line.split(",");
                List<String> lineData = Arrays.asList(values);

                // Ajout de la liste temporaire dans la liste principale
                data.add(lineData);
            }

            // Affichage des données lues
            System.out.println("\nData read from CSV file:");
            for (int i = 0; i < data.size(); i++) {
                List<String> row = data.get(i);
                System.out.println("Row " + i + ": " + String.join(", ", row));
            }

        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + e.getMessage());
            e.printStackTrace();
        }
    }
}