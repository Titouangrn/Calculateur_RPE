package Vue;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import Controleur.Main;
import Modele.Mouvement;

import java.util.ArrayList;
import java.util.List;

public class CtrlFen {

    /* Boutons de l'interface */
    @FXML private Button bnCalculate;
    @FXML private Button bnQuit;

    /* Interface menu des capacités */
    @FXML private SplitMenuButton menuCapacity;
    @FXML private MenuItem itemHigh;
    @FXML private MenuItem itemAverage;
    @FXML private MenuItem itemLow;

    /* Saisies de texte */
    @FXML private TextField txtReps;
    @FXML private TextField txtMax;

    /* Tableau d'affichage */
    @FXML private TableView<ObservableList<String>> tabAffichage = new TableView<>();
    String[] headers = {"RPE", "Charge", "Pourcentage"};
    ObservableList<ObservableList<String>> dataTable = FXCollections.observableArrayList();

    /* Colonnes du tableau */
    @FXML private TableColumn<ObservableList<String>, String> colRPE;
    @FXML private TableColumn<ObservableList<String>, String> colCharge;
    @FXML private TableColumn<ObservableList<String>, String> colPourcentage;



    @FXML
    public void initialize() {
        // Choix difficulté
        itemHigh.setOnAction(e -> menuCapacity.setText("High"));
        itemAverage.setOnAction(e -> menuCapacity.setText("Average"));
        itemLow.setOnAction(e -> menuCapacity.setText("Low"));

        // Liaison des colonnes à une valeur
        colRPE.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().get(0)));
        colCharge.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().get(1)));
        colPourcentage.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().get(2)));

        // Lier la table à la liste
        tabAffichage.setItems(dataTable);
    }


    @FXML
    public void calculerRPE(ActionEvent event) {
        // Effacement des données dans les colonnes
        dataTable.clear();

        // Récupération des valeurs des différentes saisies
        String capacity = "High";
        double max = Double.parseDouble(txtMax.getText());
        int reps = Integer.parseInt(txtReps.getText());
        capacity = menuCapacity.getText();

        // Création d'un mouvement pour calculer le RPE
        Mouvement mouv = new Mouvement(max,capacity,reps);
        List<Double> listRPE = mouv.calculerRPE();

        // Affichage sous forme de liste du RPE, de la charge et du pourcentage par rapport au max
        float j = 10;
        for (int i = 0 ; i < listRPE.size() ; i++) {
            dataTable.add(FXCollections.observableArrayList(
                    "RPE : "+j,
                    String.valueOf(listRPE.get(i)),
                    String.valueOf((listRPE.get(i)/max)*100)+"%")
            );
            j -=0.5;
        }


    }

    @FXML
    void quitter(ActionEvent event) {
        // Fait appel à la fonction quitter du main pour fermer la fenêtre
        Main.quitterApplication();
    }

}