package Controleur;
import Vue.Fen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    static private Fen fFenetre;
    public void start(Stage f) throws Exception{
        // Chargement du fichier FXML
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/Vue/Fenetre.fxml"));
        Parent root = loader.load();

        // Création de la fenêtre
        Scene scene = new Scene(root);
        f.setScene(scene);
        f.setTitle("Mon application");
        f.show();
    }
    // Création de la fenêtre au début du programme
    public static void main(String[] args) {
        Application.launch();
    }

    // Ferme l'application suite à une action sur le bouton quit
    public static void quitterApplication(){
        System.exit(0);
    }

}