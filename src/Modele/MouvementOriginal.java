package Modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MouvementOriginal {
    private int repCible;
    private Double max;
    private List<List<Double>> lst = importCSV();

    public String chemin;
    String high = "data/HighRepCapacity.csv";
    String average = "data/AverageRepCapacity.csv";
    String low = "data/LowRepCapacity.csv";

    /* Constructeur */
    public MouvementOriginal(Double max){
        this.max = max;
    }


    /* Getters */
    public Double getMax() {
        return this.max;
    }
    public int getRepCible() {
        return repCible;
    }



    /* Procédures */

    // Import du CSV
    public List<List<Double>> importCSV() {

        Scanner choix = new Scanner(System.in);
        System.out.println("Choisissez difficulté (Low, Avg, High -> L,A,H) : \n");
        String val = choix.nextLine();

        // Application du choix de la difficulté
        switch (val){
            case "L":
                this.chemin = "data/LowRepCapacity.csv";
                break;

            case "A":
                this.chemin = "data/AverageRepCapacity.csv";
                break;

            case "H":
                this.chemin = "data/HighRepCapacity.csv";
        }



        // Chemin du CSV
        String csvFile = this.chemin;

        // Liste pour stocker les données
        List<List<Double>> data = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(csvFile))) {
            // Lis chaque ligne du fichier
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Sépare la ligne avec les virgule et la convertie en liste
                String[] values = line.split(",");
                List<String> lineData = Arrays.asList(values);


                // Conversion des données en Double
                List<Double> lstConv = new ArrayList<Double>();
                for (int i = 0 ; i < lineData.size() ; i++){
                    lstConv.add(Double.parseDouble(lineData.get(i)));
                }
                // Ajout de la liste temporaire dans la liste principale
                data.add(lstConv);
            }


        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + e.getMessage());
            e.printStackTrace();
        }

        return data;
    }

    // Procédure calculant le RPE
    public List<Double> calculerLeRPE(){
        List<Double> lstcharge = new ArrayList<Double>();

        Scanner scanNbRep = new Scanner(System.in);
        System.out.println("Pour combien de reps cible ? (entre 1 et 12) : \n");
        this.repCible = scanNbRep.nextInt();
        for (int i = 0 ; i < this.lst.size() ; i++){
            double valeur = (this.lst.get(i).get(getRepCible() - 1) * this.getMax()) / 100.0;
            double arrondi = Math.round(valeur / 2.5) * 2.5;  // arrondi au plus proche multiple de 2.5
            lstcharge.add(arrondi);
        }


        return lstcharge;
    }


    public String chargeRPE() {
        List<Double> listeRPE = calculerLeRPE();

        String affichage  = "Voici la charge en fonction du RPE pour "+ this.getRepCible()+" reps"+
                "\n";
        float j = 10;
        for (int i = 0 ; i < listeRPE.size() ; i++){
            affichage = affichage + "RPE "+j+" : "+listeRPE.get(i)+" \n";
            j -=0.5;
        }
        return affichage;
    }

    public void afficherRPE(){
        System.out.println(this.chargeRPE());
    }


}
