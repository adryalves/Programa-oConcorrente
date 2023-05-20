/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Inicio...........: 19/03/2022
* Ultima alteracao.: 24/03/2022
* Nome.............: Avore Genealogica
* Funcao...........: Tem-se a simulacao de uma arvore genealogica por meio da criacao de varias threads, 
* para mostrar o funcionamento de threads que ocorrem de forma concorrente. Essa classe eh quem faz o 
* controle entre as nossas threads, seus codigos e a GUI do Java, por meio dessa classe que a tela eh modificada a depender do funcionamento das threads
*************************************************************** */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import model.*;

public class TelaController implements Initializable {

  @FXML
  private AnchorPane anchor;
  @FXML
  private ImageView imgBisneto = new ImageView(); // Apenas instaciamos os objetos de image view que usaremos para
                                                  // colocar as imagens, os ids declarados aqui sao os mesmo do scene
                                                  // builder
  @FXML
  private ImageView imgPai = new ImageView();
  @FXML
  private ImageView imgFilho1 = new ImageView();
  @FXML
  private ImageView imgFilho2 = new ImageView();
  @FXML
  private ImageView imgFilho3 = new ImageView();
  @FXML
  private ImageView imgNeto1 = new ImageView();
  @FXML
  private ImageView imgNeto2 = new ImageView();
  @FXML
  private ImageView imgfundo;
  @FXML
  private Label idLabel; // Instanciando o objeto Label
  @FXML
  private ImageView imgBisnetoMorto = new ImageView();
  @FXML
  private ImageView imgFilho1Morto = new ImageView();
  @FXML
  private ImageView imgFilho2Morto = new ImageView();
  @FXML
  private ImageView imgFilho3Morto = new ImageView();
  @FXML
  private ImageView imgNeto2Morto = new ImageView();
  @FXML
  private ImageView imgNetoMorto = new ImageView();
  @FXML
  private ImageView imgPaiMorto = new ImageView();
  @FXML
  private Label idPaiAno;
  @FXML
  private Label idFilho1;
  @FXML
  private Label idNeto1Ano;
  @FXML
  private Label idNeto2Ano;
  @FXML
  private Label idBisnetoAno;
  @FXML
  private Label idFilho1ano;
  @FXML
  private Label idFilho2Ano;
  @FXML
  private Label idFilho3Ano;
  @FXML
  private Button idbotao; // Instanciando o objeto botao
  @FXML
  private Button idbotao2;

  /*
   * ***************************************************************
   * Metodo: Escolher imagem
   * Funcao: a depender do parametro ele instancia a imagem referente e insere ela
   * dentro do image view para ser mostrado na tela
   * Parametros: recebe uma string epoca que se refere a qual epoca esta o
   * personagem e vai ser usado para identificar qual das fotos
   * de tal personagem (representaca da thread) deve ser colocado na tela, e
   * tambem recebe o parametro string geracao que eh para sabermos de qual thread
   * esta se referindo
   * Retorno: void, nao retorna nada
   */
  public void EscolherImagem(String epoca, String geracao) { // Este metodo serve para que o programa coloque na tela
                                                             // imagem correspondente e na image view correspondente
    switch (geracao) { // Usamos o switch com o geracao, para descobrir de qual pessoa esta se
                       // referindo e o epoca eh para identificarmos qual foto/epoca da pessoa deseja
                       // que apareca
      case "pai": // caso o metodo tenha sido chamado passando o segundo parametro como pai,
                  // sabemos que esta se referindo ao pai e ai analisaremos a epoca para saber
                  // qual imagem do pai inserir

        if (epoca == "jovem") { // analisando o parametro epoca

          Image image = new Image("view/personagens/pai-jovem.png"); // Instanciando um objeto imagem passando a url da
                                                                     // imagem que temos para que possamos usa-la na
                                                                     // image view
          imgPai.setImage(image); // Aqui usamos o metdo proprio do java, para setar a imagem que foi a do
                                  // endereco acima na image view imgPai
        }
        if (epoca == "adulto") {
          Image image = new Image("view/personagens/pai-adulto.png"); // Fazemos o mesmo que acima, porem usando o image
                                                                      // view correspondente
          imgPai.setImage(image);
        }
        if (epoca == "velho") {

          Image image = new Image("view/personagens/pai-velho.png");
          imgPai.setImage(image);
        }
        if (epoca == "idoso") { // Trocar outra imagem
          Image image = new Image("view/personagens/pai-idoso.png");
          imgPai.setImage(image);
        }
        if (epoca == "morto") { // Trocar outra imagem
          Image image = new Image("view/personagens/pai-morto.png");
          imgPai.setImage(image);
          TornarMorto("pai");
        }

        break;
      case "filho1": // caso o metodo tenha sido chamado passando o segundo parametro como filho1,
        // sabemos que esta se referindo ao filho1 analisaremos a epoca para saber
        // qual imagem do filho1 inserir
        if (epoca == "bebe") {
          Image image = new Image("view/personagens/bebe.png");
          imgFilho1.setImage(image);
        }
        if (epoca == "crianca") {
          Image image = new Image("view/personagens/filha1-crianca.png");
          imgFilho1.setImage(image);
        }
        if (epoca == "jovem") {
          Image image = new Image("view/personagens/filha1-jovem.png");
          imgFilho1.setImage(image);
        }
        if (epoca == "adulto") {
          Image image = new Image("view/personagens/filha1-adulta.png");
          imgFilho1.setImage(image);
        }
        if (epoca == "velho") {
          Image image = new Image("view/personagens/filha1-velha.png");
          imgFilho1.setImage(image);
        }
        if (epoca == "idoso") {
          Image image = new Image("view/personagens/filha1-idosa.png");
          imgFilho1.setImage(image);
        }

        if (epoca == "morto") {
          Image image = new Image("view/personagens/filha1-morta.png");
          imgFilho1.setImage(image);
        }

        break;
      case "filho2": // caso o metodo tenha sido chamado passando o segundo parametro como filho2,
        // sabemos que esta se referindo ao filho2 analisaremos a epoca para saber
        // qual imagem do filho2 inserir
        if (epoca == "bebe") {
          Image image = new Image("view/personagens/bebe-menino.png");
          imgFilho2.setImage(image);
        }

        if (epoca == "crianca") {
          Image image = new Image("view/personagens/filho2-crianca.png");
          imgFilho2.setImage(image);
        }
        if (epoca == "jovem") {
          Image image = new Image("view/personagens/filho2-jovem.png");
          imgFilho2.setImage(image);
        }

        if (epoca == "adulto") {
          Image image = new Image("view/personagens/filho2-adulto.png");
          imgFilho2.setImage(image);
        }
        if (epoca == "idoso") {
          Image image = new Image("view/personagens/filho2-idoso.png");
          imgFilho2.setImage(image);
        }
        if (epoca == "morto") {
          Image image = new Image("view/personagens/filho2-morto.png");
          imgFilho2.setImage(image);
        }

        break;
      case "filho3": // caso o metodo tenha sido chamado passando o segundo parametro como filho3,
        // sabemos que esta se referindo ao filho3 analisaremos a epoca para saber
        // qual imagem do filho3 inserir
        if (epoca == "bebe") {
          Image image = new Image("view/personagens/bebe-menino.png");
          imgFilho3.setImage(image);
        }

        if (epoca == "crianca") {
          Image image = new Image("view/personagens/filho3-crianca.png");
          imgFilho3.setImage(image);
        }
        if (epoca == "jovem") {
          Image image = new Image("view/personagens/filho3-jovem.png");
          imgFilho3.setImage(image);
        }
        if (epoca == "adulto") {
          Image image = new Image("view/personagens/filho3-adulto.png");
          imgFilho3.setImage(image);
        }
        if (epoca == "velho") {
          Image image = new Image("view/personagens/filho3-velho.png");
          imgFilho3.setImage(image);
        }

        if (epoca == "morto") {
          Image image = new Image("view/personagens/filho3-morto.png");
          imgFilho3.setImage(image);
        }

        break;

      case "neto1": // caso o metodo tenha sido chamado passando o segundo parametro como neto1,
        // sabemos que esta se referindo ao neto1 analisaremos a epoca para saber
        // qual imagem do neto1 inserir
        if (epoca == "bebe") {
          Image image = new Image("view/personagens/bebe.png");
          imgNeto1.setImage(image);
        }
        if (epoca == "crianca") {
          Image image = new Image("view/personagens/neto1-crianca.png");
          imgNeto1.setImage(image);
        }
        if (epoca == "adulto") {
          Image image = new Image("view/personagens/neto1-adulto.png");
          imgNeto1.setImage(image);
        }
        if (epoca == "morto") {
          Image image = new Image("view/personagens/neto1-morto.png");
          imgNeto1.setImage(image);
        }
        break;

      case "neto2": // caso o metodo tenha sido chamado passando o segundo parametro como neto2,
        // sabemos que esta se referindo ao neto2 analisaremos a epoca para saber
        // qual imagem do neto2 inserir
        if (epoca == "bebe") {
          Image image = new Image("view/personagens/bebe.png");
          imgNeto2.setImage(image);
        }
        if (epoca == "jovem") {
          Image image = new Image("view/personagens/neto2-jovem.png");
          imgNeto2.setImage(image);
        }
        if (epoca == "adulto") {
          Image image = new Image("view/personagens/neto2-adulto.png");
          imgNeto2.setImage(image);
        }
        if (epoca == "morto") {
          Image image = new Image("view/personagens/neto2-morto.png");
          imgNeto2.setImage(image);

        }
        break;

      case "bisneto": // caso o metodo tenha sido chamado passando o segundo parametro como bisneto,
        // sabemos que esta se referindo ao bisneto analisaremos a epoca para saber
        // qual imagem do bisneto inserir
        if (epoca == "bebe") {
          Image image = new Image("view/personagens/bebe-menino.png");
          imgBisneto.setImage(image);
        }
        if (epoca == "crianca") {
          Image image = new Image("view/personagens/bisneto-crianca.png");
          imgBisneto.setImage(image);
        }

        if (epoca == "morto") {
          Image image = new Image("view/personagens/bisneto-morto.png");
          imgBisneto.setImage(image);

        }

        break;
    }

  }

  /*
   * ***************************************************************
   * Metodo: MostrarAno
   * Funcao: recebe cada iteracao do ano passado na thread e mostra ela em uma
   * label na tela, sendo mudada a cada segundo, serve para mostrarmos o tempo
   * passado no programa rodando constantemente, recebemos o valor como parametro
   * e setamos ele na label para poder
   * aparecer na tela
   * Parametros: recebe um parametro do tipo int que se refere ao tempo passado a
   * cada iteracao da thread pai, por quem esse metodo eh chamado
   * Retorno: void, nao retorna nada
   */
  public void MostrarAno(int ano) { // esse metodo serve para que consigamos receber da thread cada ano que vai
                                    // passando(vindo la da thread pai) e mostrar na tela mudando a cada segundo
    Platform.runLater(() -> { // metodo proprio do java para permitir essa atualizacao a cada segundp sendo
                              // mostrada na tela
      idLabel.setText("Tempo: " + ano); // aqui setamos o valor na label para aparecer na interface
    });
  }

  /*
   * ***************************************************************
   * Metodo: MostrarIdadePai
   * Funcao: mostrarmos o tempo passado ou idade da thread pai na tela, de forma
   * constante, recebemos o valor como parametro e setamos ele na label para poder
   * aparecer na tela
   * Parametros: recebe um valor inteiro que representa a idade desse pai a cada
   * iteracao
   * Retorno: void, nao retorna nada
   */
  public void MostrarIdadePai(int idade) { // esse metodo serve para que consigamos receber da thread a idade do pai, ou
                                           // seja cada ano que eh passado (vindo la da thread pai) e mostrar na tela
                                           // mudando a cada segundo
    Platform.runLater(() -> {
      idPaiAno.setText("idade: " + idade);
    });
  }

  /*
   * ***************************************************************
   * Metodo: MostrarIdadeFilho1
   * Funcao: mostrarmos o tempo passado ou idade da thread filho na tela, de forma
   * constante, recebemos o valor como parametro e setamos ele na label para poder
   * aparecer na tela
   * Parametros: recebe um valor inteiro que representa a idade desse filho a cada
   * iteracao
   * Retorno: void, nao retorna nada
   */
  public void MostrarIdadeFilho1(int idade) { // esse metodo serve para que consigamos receber da thread a idade do
                                              // filho1, ou
    // seja cada ano que eh passado (vindo la da thread filho) e mostrar na tela
    // mudando a cada segundo

    Platform.runLater(() -> {
      idFilho1ano.setText("idade: " + idade);
    });
  }

  /*
   * ***************************************************************
   * Metodo: MostrarIdadeFilho2
   * Funcao: mostrarmos o tempo passado ou idade da thread filho2 na tela, de
   * forma
   * constante, recebemos o valor como parametro e setamos ele na label para poder
   * aparecer na tela
   * Parametros: recebe um valor inteiro que representa a idade desse filho2 a
   * cada
   * iteracao
   * Retorno: void, nao retorna nada
   */
  public void MostrarIdadeFilho2(int idade) { // esse metodo serve para que consigamos receber da thread a idade do
                                              // filho2, ou
    // seja cada ano que eh passado (vindo la da thread filho2) e mostrar na tela
    // mudando a cada segundo

    Platform.runLater(() -> {
      idFilho2Ano.setText("idade: " + idade);
    });
  }

  /*
   * ***************************************************************
   * Metodo: MostrarIdadeFilho3
   * Funcao: mostrarmos o tempo passado ou idade da thread filho3 na tela, de
   * forma
   * constante, recebemos o valor como parametro e setamos ele na label para poder
   * aparecer na tela
   * Parametros: recebe um valor inteiro que representa a idade desse filho3 a
   * cada
   * iteracao
   * Retorno: void, nao retorna nada
   */
  public void MostrarIdadeFilho3(int idade) { // esse metodo serve para que consigamos receber da thread a idade do
                                              // filho3, ou
    // seja cada ano que eh passado (vindo la da thread filho3) e mostrar na tela
    // mudando a cada segundo

    Platform.runLater(() -> {
      idFilho3Ano.setText("idade: " + idade);
    });
  }

  /*
   * ***************************************************************
   * Metodo: MostrarIdadeNeto1
   * Funcao: mostrarmos o tempo passado ou idade da thread neto1 na tela, de forma
   * constante, recebemos o valor como parametro e setamos ele na label para poder
   * aparecer na tela
   * Parametros: recebe um valor inteiro que representa a idade desse neto1 a cada
   * iteracao
   * Retorno: void, nao retorna nada
   */
  public void MostrarIdadeNeto1(int idade) { // esse metodo serve para que consigamos receber da thread a idade do
                                             // neto1, ou
    // seja cada ano que eh passado (vindo la da thread neto1) e mostrar na tela
    // mudando a cada segundo

    Platform.runLater(() -> {
      idNeto1Ano.setText("idade: " + idade);
    });
  }

  /*
   * ***************************************************************
   * Metodo: MostrarIdadeNeto2
   * Funcao: mostrarmos o tempo passado ou idade da thread neto2 na tela, de forma
   * constante, recebemos o valor como parametro e setamos ele na label para poder
   * aparecer na tela
   * Parametros: recebe um valor inteiro que representa a idade desse neto2 a cada
   * iteracao
   * Retorno: void, nao retorna nada
   */
  public void MostrarIdadeNeto2(int idade) { // esse metodo serve para que consigamos receber da thread a idade do
                                             // neto2, ou
    // seja cada ano que eh passado (vindo la da thread neto2) e mostrar na tela
    // mudando a cada segundo

    Platform.runLater(() -> {
      idNeto2Ano.setText("idade: " + idade);
    });
  }

  /*
   * ***************************************************************
   * Metodo: MostrarIdadeBisneto
   * Funcao: mostrarmos o tempo passado ou idade da thread bisneto na tela, de
   * forma
   * constante, recebemos o valor como parametro e setamos ele na label para poder
   * aparecer na tela
   * Parametros: recebe um valor inteiro que representa a idade desse bisneto a
   * cada
   * iteracao
   * Retorno: void, nao retorna nada
   */
  public void MostrarIdadeBisneto(int idade) { // esse metodo serve para que consigamos receber da thread a idade do
                                               // bisneto, ou
    // seja cada ano que eh passado (vindo la da thread bisneto) e mostrar na tela
    // mudando a cada segundo

    Platform.runLater(() -> {
      idBisnetoAno.setText("idade: " + idade);
    });
  }

  /*
   * ***************************************************************
   * Metodo: TornarMorto
   * Funcao: ele instancia a imagem de um x vermelho e insere na image view
   * correspondente e torna a label da idade de cor vermelha quando chamado
   * Parametros: recebe uma String pessoa que se refere a qual thread esta se
   * referindo, para sabermos de qual pessoa fazermos alteracao para representar a
   * sua morte
   * Retorno: void, nao retornar nada
   */

  public void TornarMorto(String pessoa) { // esse metodo serve para que possamos passar uma imagem de x vermelho em
                                           // cada pessoa para ajudar na representacao da sua morte, e tornar a label da
                                           // idade da cor vermelho, ele recebe uma
                                           // string para indicar de qual pessoa(pai ou filho..) sera feita a mudanca
    Image img = new Image("view/Xvermelho.png"); // instanciamos o objeto imagem passando o endereco

    switch (pessoa) { // um switch para sabermos de qual a pesssoa devera haver a mudanca
      case "pai":
        imgPaiMorto.setImage(img); // setamos no image view a imagem correspondente ao id da pessoa correspondente
        idPaiAno.setTextFill(Color.RED); // aqui nos colocamos o label que mostra a idade para vermelho depois que cada
                                         // pessoa morrer
        idbotao2.setVisible(true);
        break;

      case "filho1":
        imgFilho1Morto.setImage(img);
        idFilho1ano.setTextFill(Color.RED);
        break;

      case "filho2":
        imgFilho2Morto.setImage(img);
        idFilho2Ano.setTextFill(Color.RED);
        break;

      case "filho3":
        imgFilho3Morto.setImage(img);
        idFilho3Ano.setTextFill(Color.RED);
        break;

      case "neto1":
        imgNetoMorto.setImage(img);
        idNeto1Ano.setTextFill(Color.RED);
        break;

      case "neto2":
        imgNeto2Morto.setImage(img);
        idNeto2Ano.setTextFill(Color.RED);
        break;

      case "bisneto":
        imgBisnetoMorto.setImage(img);
        idBisnetoAno.setTextFill(Color.RED);

        break;
    }

  }

  /*
   * ***************************************************************
   * Metodo: Iniciar
   * Funcao: cria o objeto thread pai e inicializa essa thread, eh esse metodo que
   * inicializa o nosso programa e tornar o botao invisivel depois de apertado
   * Parametros: nao recebe parametro
   * Retorno: void, nao retorna nada
   */

  public void Iniciar() { // esse metodo serve para iniciar a thread, utilizamos um botao para que o
                          // usuario possa escolher quando quer que comece a rodar as threads
    ThreadPai threadPai = new ThreadPai(this); // criamos um objeto do tipo thread pai, estamos passando como parametro
                                               // a tela controller, pois para que na tela mostre coisas de diferentes
                                               // telas é preciso que cada uma telas tenha tambem acesso a tela de
                                               // controlle
    threadPai.start(); // metodo proprio de uma thread para iniciar ela, eh aqui que damos um start na
                       // thread e ai ela comeca a executar o que esta no metodo run dela
    idbotao.setVisible(false); // depois que o botao eh apertado, nao queremos mais que o usuario veja, por
                               // isso usamos esse metodo para nao torna-lo mais visivel
  }

  /*
   * ***************************************************************
   * Metodo: Reiniciar
   * Funcao: ele seta todas image view para nullo, ou seja sem foto e todas as
   * labels para vazio, para que o programa possa comecar do zero
   * Parametros: nao recebe parametro, ele eh chamado por meio de um botao
   * Retorno: descricao do valor retornado
   */
  public void Reiniciar() { // Crio um array com todas as images view para poder setar todas ao mesmo tempo
                            // com valor nulo
    ImageView[] imageViews = { imgBisneto, imgBisnetoMorto, imgFilho1, imgFilho1Morto, imgFilho2, imgFilho2Morto,
        imgFilho3, imgFilho3Morto, imgNeto1, imgNeto2, imgNeto2Morto, imgNetoMorto, imgPai, imgPaiMorto };
    for (ImageView imageView : imageViews) { // faco a iteracao por um for each para cad imagem view pegar valor nulo
      imageView.setImage(null);
    }

    Label[] labels = { idBisnetoAno, idFilho1ano, idFilho2Ano, idFilho3Ano, idNeto1Ano, idNeto2Ano, idPaiAno }; // Crio
                                                                                                                // um
                                                                                                                // array
                                                                                                                // com
                                                                                                                // todas
                                                                                                                // as
                                                                                                                // labels
                                                                                                                // para
                                                                                                                // poder
                                                                                                                // setar
                                                                                                                // todas
                                                                                                                // ao
                                                                                                                // mesmo
                                                                                                                // tempo
                                                                                                                // com
                                                                                                                // valor
                                                                                                                // vazio
    for (Label label : labels) { // faco um for each para iterar por todo o array e ir setando todos de uma vez
      label.setText(null); // torno a label vazia
      label.setTextFill(Color.BLACK); // coloco as labels com cor preta
    }
    idLabel.setText(null); // coloco a label do tempo como nulo/vazia

    Iniciar();
    idbotao2.setVisible(false); // torno o botao reiniciar agora inivisivel pro usuario
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub

  }

}
