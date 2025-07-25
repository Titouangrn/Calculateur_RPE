package Vue;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import java.io.File;
import java.io.IOException;

public class Fen extends Stage {

    private CtrlFen ctrl;
    
    public Fen() throws IOException {
        this.setTitle("RPE calculator");
        this.setResizable(true);
        Scene laScene = new Scene(creerSceneGraph());
        this.setScene(laScene);
    }

    private Pane creerSceneGraph() throws IOException {
        File f = new File("/Vue/Fenetre.fxml");
        FXMLLoader loader;
        loader = new FXMLLoader(f.toURI().toURL());
        Pane racine = loader.load();
        ctrl = loader.getController();
        return racine;
    }


}
