
/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Inicio...........: 19/03/2022
* Ultima alteracao.: 24/03/2022
* Nome.............: Avore Genealogica
* Funcao...........: Tem-se a simulacao de uma arvore genealogica por meio da criacao de varias threads, 
* para mostrar o funcionamento de threads que ocorrem de forma concorrente, foi criado uma thread pai que
* sera pai de outras threads e sera mostrado desde o nascimento de cada thread ate a morte, mostrando tambem
* o tempo de ocorrencia de cada thread, sendo mostrada na interface elas ocorrendo de forma simultanea, nessa 
* classe Principal eh onde a tela eh carregada e eh onde temos o main,o que possibilita de o programa rodar, entao aqui que comecamos o programa 
*************************************************************** */
import Controller.TelaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Principal extends Application {
  @Override
  public void start(Stage stage) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("/view/Scene1.fxml")); // Passo o endereco da tela
    // que tera que ser aberta
    Scene scene = new Scene(root); // instancio uma nova scene
    stage.setScene(scene); // coloco essa scene no que seria o "palco"
    scene.getStylesheets().add("/view/Style.css"); // Aqui eu adiciono o arquivo
    // css no meu projeto
    stage.setResizable(false); // para nao ser possivel aumentar de tamanho
    stage.setTitle("Arvore Genealogica"); // para declarar o titulo do
    // programa
    stage.getIcons().add(new Image("/view/icon.png"));
    stage.setWidth(700); // Estamos dando um valor para a largura da telinha
    stage.setHeight(550); // Estamos dando um valor para a altura da telinha
    stage.show(); // rodar e iniciar a tela

  }

  public static void main(String[] args) {
    launch(args);
  }
}