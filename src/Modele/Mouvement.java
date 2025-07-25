package Modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Mouvement {
    private int repCible;               // nombre de reps de la série de l'utilisateur
    private Double max;                 // max de l'utilisateur sur un mouvement
    private List<List<Double>> lst;     // liste qui contiendra des pourcentages du max



    // A utiliser plus tard ou sous une autre forme
    private HashMap<String,Double> variationSquat = new HashMap<>(){{   // Variations pour le squat
        put("Comp",1.0);
        put("HighBar",0.92);
        put("TempoSquat",0.88);
        put("PauseSquat",0.92);
    }};
    private HashMap <String,Double> variationBench = new HashMap<>(){{  // Variations pour le bench
        put("Comp",1.0);
        put("CloseGrip",0.96);
        put("TempoBench",0.92);
        put("PauseBench",0.87);
        put("LarsenPress",0.91);

    }};
    private HashMap <String,Double> variationDeadlift = new HashMap<>(){{
        put("Comp",1.0);
        put("PauseDeadlift",0.91);
        put("RDL", 0.75);
    }};

    String capacity;

    /* Constructeur */
    public Mouvement(Double max, String capacity, int repCible){
        this.max = max;
        this.capacity = capacity;   // capacité de l'utilisateur
        this.lst = importCSV();     // liste des pourcentages en fonction de la capacité
        this.repCible = repCible;
    }

    /* Getters */
    public Double getMax() {
        return this.max;
    }

    public int getRepCible() {
        return repCible;
    }

    public List<List<Double>> getLst() {
        return this.lst;
    }

    /* Setter */
    public void setRepCible(int repCible) {
        this.repCible = repCible;
    }




    /* Procédures */

    // Import du CSV
    public List<List<Double>> importCSV() {
        // Construire le chemin du CSV avec la capacité
        String csvFile = "data/" + this.capacity + "RepCapacity.csv";

        // Création de la liste pour les différents pourcentages
        List<List<Double>> data = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(csvFile))) {
            // Tant qu'il reste des lignes dans le CSV
            while (scanner.hasNextLine()) {
                // On récupère la ligne suivante et on sépare les différe"ntes valeurs avec le séparateur
                String line = scanner.nextLine();
                String[] values = line.split(",");
                List<String> lineData = Arrays.asList(values);

                // Et on convertit les différentes valeurs en double pour les ré-utiliser pour le calcul
                List<Double> lstConv = new ArrayList<>();
                for (String val : lineData) {
                    lstConv.add(Double.parseDouble(val));
                }
                data.add(lstConv);
            }
        }
        // Exception
        catch (FileNotFoundException e) {

            System.err.println("CSV file not found: " + e.getMessage());
            e.printStackTrace();
        }

        return data;
    }

    // Procédure calculant le RPE
    public List<Double> calculerRPE(){
        List<Double> lstcharge = new ArrayList<>();

        // Pour chaque pourcentage présent dans la liste :
        for (int i = 0 ; i < this.lst.size() ; i++){
            // Une charge arrondie au 2.5 près est calculée et ajoutée dans la liste des charges
            double valeur = (this.lst.get(i).get(getRepCible() - 1) * this.getMax()) / 100.0;
            double arrondi = Math.round(valeur / 2.5) * 2.5;  // arrondi au plus proche multiple de 2.5
            lstcharge.add(arrondi);
        }
        return lstcharge;
    }

}
