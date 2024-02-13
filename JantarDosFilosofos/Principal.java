
/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Inicio...........: 04/05/2023
* Ultima alteracao.: 13/05/2023
* Nome.............: Jantar dos filosofos, Classe Principal
* Funcao...........: o jantar dos filosofos representa um problema classico de programação concorrente, em que 5 filosofos estao em uma mesa, cada um com um garfo e prato de macarrao, entretanto eles so conseguem comer com dois garfos, entao eles precisam compartilhar esse garfo, o programa tem o intuito de resolver esse problema utilizando semaforos e evitando conliftos. Os filosofos tem dois estados: pensar e comer. Sempre que terminam de pensar eles tentam comer e so consegue se estiver o seu garfo e o lado disponivel, e caso nao esteja ele precisa esperar.Essa classe apenas chama a tela e inicia o programa
*************************************************************** */
import java.util.concurrent.Semaphore;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.ThreadFilosofos;
import controller.Controller;

public class Principal extends Application {

  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("view/TelaFilosofos.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setResizable(false); // para nao ser possivel aumentar de tamanho
    stage.setTitle("Jantar dos Tributos"); // para declarar o titulo do
    // programa
    scene.getStylesheets().add("view/Style.css"); // Aqui eu adiciono o arquivo
    // css no meu projeto
    stage.getIcons().add(new Image("view/icons8-spaghetti-96.png"));

    stage.setOnCloseRequest(t -> {
      Platform.exit();
      System.exit(0);
    });

    stage.show(); // rodar e iniciar a tela

  }

  public static void main(String[] args) {
    launch(args);
    // passar pro controller

  }
}
