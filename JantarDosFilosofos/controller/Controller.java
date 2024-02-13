/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Inicio...........: 04/05/2023
* Ultima alteracao.: 13/05/2023
* Nome.............: Jantar dos filosofos, Classe controller
* Funcao...........: essa classe eh responsavel por fazer a implementacao da interface utilizando o a thread de filosofos, ou seja ela quem relaciona a parte de codigo com a interface e faz a conexao entre os dois, tambem enssa classe que se cria as instancia das threads e chama o start, dando inicio a elas
*************************************************************** */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ThreadFilosofos;

public class Controller implements Initializable {

  @FXML
  private ImageView garfo0;
  @FXML
  private ImageView garfo1;
  @FXML
  private ImageView garfo2;
  @FXML
  private ImageView garfo3;
  @FXML
  private ImageView garfo4;
  @FXML
  private ImageView imgfinnick;
  @FXML
  private ImageView imgkatniss;
  @FXML
  private ImageView imgpeeta;
  @FXML
  private ImageView imgrue;
  @FXML
  private ImageView imgsnow;
  @FXML
  private ImageView garfofinnick1;

  @FXML
  private ImageView garfofinnick2;

  @FXML
  private ImageView garfokatniss1;

  @FXML
  private ImageView garfokatniss2;

  @FXML
  private ImageView garfopeeta1;

  @FXML
  private ImageView garfopeeta2;

  @FXML
  private ImageView garforue1;

  @FXML
  private ImageView garforue2;

  @FXML
  private ImageView garfosnow1;

  @FXML
  private ImageView garfosnow2;

  @FXML
  private Slider sliderComerFinnick;

  @FXML
  private Slider sliderComerKatniss;

  @FXML
  private Slider sliderComerPeeta;

  @FXML
  private Slider sliderComerRue;

  @FXML
  private Slider sliderComerSnow;

  private Slider[] sliderDeComer;

  @FXML
  private Slider sliderPensarFinnick;

  @FXML
  private Slider sliderPensarKatniss;

  @FXML
  private Slider sliderPensarPeeta;

  @FXML
  private Slider sliderPensarRue;

  @FXML
  private Slider sliderPensarSnow;

  private Slider[] sliderDePensar;

  @FXML
  private Button botaoiniciar;

  @FXML
  private Button botaoreiniciar;

  @FXML
  private Button T0;

  @FXML
  private Button T1;

  @FXML
  private Button T2;

  @FXML
  private Button T3;

  @FXML
  private Button T4;

  ThreadFilosofos[] filosofos = new ThreadFilosofos[5];

  @FXML
  private ImageView imgT0;

  @FXML
  private ImageView imgT1;

  @FXML
  private ImageView imgT2;

  @FXML
  private ImageView imgT3;

  @FXML
  private ImageView imgT4;

  @FXML
  private int controleT0 = 0;

  @FXML
  private int controleT1 = 0;

  @FXML
  private int controleT2 = 0;

  @FXML
  private int controleT3 = 0;

  @FXML
  private int controleT4 = 0;

  private int controleImgPausa = 0;
  /*
   * ***************************************************************
   * Metodo: think
   * Funcao: a depender de qual numero recebido(que se refere ao id da thread) ele
   * modifica a imagem do "filosofo" para a imagem que represente ele pensnado e
   * as imagens que representa os garfos quando eles estao comendo como nullo, ja
   * que agora eles estao pensando
   * Parametros: id, eh um numero inteiro que se refere ao id da thread, ele eh de
   * 0-5 e cada um representa uma thread que foi criada, e eh por meio dela que se
   * sabe qual imageview de qual filosofo sera alterada
   * Retorno: void
   */

  public void think(int id) {
    Image img = new Image("view/fotopensamento.png");

    switch (id) {
      case 0: // é o snow
        imgsnow.setImage(img);
        garfosnow1.setImage(null);
        garfosnow2.setImage(null);

        break;
      case 1:
        imgfinnick.setImage(img);
        garfofinnick1.setImage(null);
        garfofinnick2.setImage(null);
        break;
      case 2:
        imgrue.setImage(img);
        garforue1.setImage(null);
        garforue2.setImage(null);
        break;
      case 3:
        imgpeeta.setImage(img);
        garfopeeta1.setImage(null);
        garfopeeta2.setImage(null);
        break;
      case 4:
        imgkatniss.setImage(img);
        garfokatniss1.setImage(null);
        garfokatniss2.setImage(null);
        break;
      default:
        break;
    }
  }

  /*
   * ***************************************************************
   * Metodo: hungry
   * Funcao: a depender de qual numero recebido(que se refere ao id da thread) ele
   * modifica a imagem do "filosofo" para a imagem que represente ele com fome
   * Parametros: id, eh um numero inteiro que se refere ao id da thread, ele eh de
   * 0-5 e cada um representa uma thread que foi criada, e eh por meio dela que se
   * sabe qual imageview de qual filosofo sera alterada
   * Retorno: void
   */
  public void hungry(int id) { // sera chamado dentro de take forks
    Image imgfome = new Image("view/comfome.png");

    switch (id) {
      case 0: // é o snow
        imgsnow.setImage(imgfome);
        break;
      case 1:
        imgfinnick.setImage(imgfome);
        break;
      case 2:
        imgrue.setImage(imgfome);
        break;
      case 3:
        imgpeeta.setImage(imgfome);
        break;
      case 4:
        imgkatniss.setImage(imgfome);
        break;
      default:
        break;
    }
  }

  /*
   * ***************************************************************
   * Metodo: eat
   * Funcao: a depender de qual numero recebido(que se refere ao id da thread) ele
   * modifica a imagem do "filosofo" para a imagem que represente ele comendo, ja
   * que agora eles estao comendo
   * e insere nas imageview quere presenta os garfos quando eles estao comendo
   * como a imagem do garfo, ja
   * que agora como estao comendoo, entai is dois garfos estao no seu prato
   * Parametros: id, eh um numero inteiro que se refere ao id da thread, ele eh de
   * 0-5 e cada um representa uma thread que foi criada, e eh por meio dela que se
   * sabe qual imageview de qual filosofo sera alterada
   * Retorno: void
   */
  public void eat(int id) {
    Image imgcomendo = new Image("view/comendo.png");
    Image garfo = new Image("view/garfo.png");

    switch (id) {
      case 0: // é o snow
        garfo0.setImage(null);
        garfo1.setImage(null);
        garfosnow1.setImage(garfo);
        garfosnow2.setImage(garfo);
        imgsnow.setImage(imgcomendo);
        break;
      case 1:
        garfo1.setImage(null);
        garfo2.setImage(null);
        garfofinnick1.setImage(garfo);
        garfofinnick2.setImage(garfo);
        imgfinnick.setImage(imgcomendo);
        break;
      case 2:
        garfo2.setImage(null);
        garfo3.setImage(null);
        garforue1.setImage(garfo);
        garforue2.setImage(garfo);
        imgrue.setImage(imgcomendo);
        break;
      case 3:
        garfo3.setImage(null);
        garfo4.setImage(null);
        garfopeeta1.setImage(garfo);
        garfopeeta2.setImage(garfo);
        imgpeeta.setImage(imgcomendo);
        break;
      case 4:
        garfo4.setImage(null);
        garfo0.setImage(null);
        garfokatniss1.setImage(garfo);
        garfokatniss2.setImage(garfo);
        imgkatniss.setImage(imgcomendo);
        break;
      default:
        break;
    }
  }

  /*
   * ***************************************************************
   * Metodo: put_forks
   * Funcao: a depender de qual numero recebido(que se refere ao id da thread) ele
   * coloca novamente na imageview do garfo na mesa, as imagens dos garfos, ja que
   * agora os garfos estao sendo colocados de volta na mesa, e seta a imagem que
   * se refere aos garfos quando o filosofo esta comendo como nulo, ja que ele nao
   * esta mais usando o garfo
   * Parametros: id, eh um numero inteiro que se refere ao id da thread, ele eh de
   * 0-5 e cada um representa uma thread que foi criada, e eh por meio dela que se
   * sabe qual imageview de qual filosofo sera alterada
   * Retorno: void
   */
  public void put_forks(int id) {
    Image imggarfo = new Image("view/garfo.png");

    switch (id) {
      case 0: // é o snow
        garfo0.setImage(imggarfo);
        garfo1.setImage(imggarfo);
        garfosnow1.setImage(null);
        garfosnow2.setImage(null);

        break;
      case 1:
        garfo1.setImage(imggarfo);
        garfo2.setImage(imggarfo);
        garfofinnick1.setImage(null);
        garfofinnick2.setImage(null);
        break;
      case 2:
        garfo2.setImage(imggarfo);
        garfo3.setImage(imggarfo);
        garforue1.setImage(null);
        garforue2.setImage(null);

        break;
      case 3:
        garfo3.setImage(imggarfo);
        garfo4.setImage(imggarfo);
        garfopeeta1.setImage(null);
        garfopeeta2.setImage(null);

        break;
      case 4:
        garfo4.setImage(imggarfo);
        garfo0.setImage(imggarfo);
        garfokatniss1.setImage(null);
        garfokatniss2.setImage(null);

        break;
      default:
        break;
    }
  }

  /*
   * ***************************************************************
   * Metodo: iniciar
   * Funcao: ele inicializa dois arrays de sliders como um array de comer e um de
   * pensar para cada thread, ele inicializa as 5 threads por meio do laco de
   * interacao, passando o id, a classe controller(a atual), o slider de pensar e
   * de comer, alem disso ele inicializa o semaforo referente ao garfos/filosofos
   * passando o valor 0, eh aqui que eh feito o start de cada thread,
   * inciializando elas e depois o botao de iniciar que realiza essa operacao fica
   * nao visivel
   * Parametros: nenhum
   * Retorno: void
   */
  public void iniciar() {
    Slider slidersComer[] = { sliderComerSnow, sliderComerFinnick, sliderComerRue, sliderComerPeeta,
        sliderComerKatniss };

    sliderDeComer = slidersComer;

    Slider sliderspensar[] = { sliderPensarSnow, sliderPensarFinnick, sliderPensarRue, sliderPensarPeeta,
        sliderPensarKatniss };

    sliderDePensar = sliderspensar;

    for (int i = 0; i < 5; i++) {
      filosofos[i] = new ThreadFilosofos(i, this, sliderDePensar[i], sliderDeComer[i]);
      ThreadFilosofos.s[i] = new Semaphore(0);
    }

    for (ThreadFilosofos tf : filosofos) {
      tf.start();
    }

    botaoiniciar.setDisable(true);

    botaoreiniciar.setVisible(true);

    habilitarBotoesPausa();
    ReiniciarBotoesPausa();

  }

  /*
   * ***************************************************************
   * Metodo: habilitarBotoesPausa
   * Funcao: ele apenas torna os botoes de pausa habilitados, pois sera chamado
   * esse
   * metodo quando iniciar o programa e ai o usuario podera usar os botoes de
   * pausa
   * Parametros: nenhum
   * Retorno: void
   */
  public void habilitarBotoesPausa() {

    T0.setDisable(false);
    T1.setDisable(false);
    T2.setDisable(false);
    T3.setDisable(false);
    T4.setDisable(false);
  }

  /*
   * ***************************************************************
   * Metodo: Reiniciar botoes pausa
   * Funcao: eseta as imagens dos botoes de pausa para a imagem padrao
   * Retorno: void
   */
  public void ReiniciarBotoesPausa() {
    Image img = new Image("view/icon-pausa-vermelho.png");
    imgT0.setImage(img);
    imgT1.setImage(img);
    imgT2.setImage(img);
    imgT3.setImage(img);
    imgT4.setImage(img);

  }
  /*
   * ***************************************************************
   * Metodo: mudarImagemBotaoPausa
   * Funcao: ele modifica a imagem do botao a depender se o usuario esta pausando
   * ou despausando a thread, ele avalia se o numero passado por parametro eh par,
   * pq se for singiifca que temos que pausou e temos que setar a imagem que
   * significa despausar e se for impar o contrario
   * Parametros: i, que eh uma variavel que faz a contagem de que vez foi apertado
   * o botao pausar
   * Retorno: Image, retorna a imagem que iremos querer setar no botao
   */

  public Image mudarImagemBotaoPausa(int i) {
    Image img;
    if (i % 2 == 0) {
      img = new Image("view/botao-play.png");

    } else {
      img = new Image("view/icon-pausa-vermelho.png");
    }
    return img;
  }

  /*
   * ***************************************************************
   * Metodo: pausarT0
   * Funcao: ele chama por meio da primeira thread o metodo setPausar da classe
   * ThreadFilosofos que fara a thread ser pausada
   * Parametros: nenhum
   * Retorno: void
   */
  public void pausarT0() {
    filosofos[0].setPausar();
    Image img = mudarImagemBotaoPausa(controleT0);
    imgT0.setImage(img);
    controleT0++;
  }

  /*
   * ***************************************************************
   * Metodo: pausarT1
   * Funcao: ele chama por meio da segunda thread o metodo setPausar da classe
   * ThreadFilosofos que fara a thread ser pausada
   * Parametros: nenhum
   * Retorno: void
   */
  public void pausarT1() {
    filosofos[1].setPausar();
    Image img = mudarImagemBotaoPausa(controleT1);
    imgT1.setImage(img);
    controleT1++;
  }

  /*
   * ***************************************************************
   * Metodo: pausarT2
   * Funcao: ele chama por meio da terceira thread o metodo setPausar da classe
   * ThreadFilosofos que fara a thread ser pausada
   * Parametros: nenhum
   * Retorno: void
   */
  public void pausarT2() {
    filosofos[2].setPausar();
    Image img = mudarImagemBotaoPausa(controleT2);
    imgT2.setImage(img);
    controleT2++;
  }

  /*
   * ***************************************************************
   * Metodo: pausarT3
   * Funcao: ele chama por meio da quarta thread o metodo setPausar da classe
   * ThreadFilosofos que fara a thread ser pausada
   * Parametros: nenhum
   * Retorno: void
   */
  public void pausarT3() {
    filosofos[3].setPausar();
    Image img = mudarImagemBotaoPausa(controleT3);
    imgT3.setImage(img);
    controleT3++;
  }

  /*
   * ***************************************************************
   * Metodo: pausarT4
   * Funcao: ele chama por meio da quinta thread o metodo setPausar da classe
   * ThreadFilosofos que fara a thread ser pausada
   * Parametros: nenhum
   * Retorno: void
   */
  public void pausarT4() {
    filosofos[4].setPausar();
    Image img = mudarImagemBotaoPausa(controleT4);
    imgT4.setImage(img);
    controleT4++;
  }

  /*
   * ***************************************************************
   * Metodo: reiniciar
   * Funcao: ele itera pelo array de ThreadFilosofos e faz cada thread chamar o
   * metodo pararThread dessa classes para as threads serem finalizadas e chamam o
   * metodo de recomeco para modificar o valor dos sliders, ele chama o metodo
   * para devolver os garfos na mesa, depois ele chama o metodo iniciar para criar
   * as threads novamente
   * Parametros: nenhum
   * Retorno: void
   */
  public void reiniciar() {
    for (ThreadFilosofos threadFilosofos : filosofos) {
      threadFilosofos.pararThread();
      threadFilosofos.recomecoThread();
    }

    for (int i = 0; i < 5; i++) {

      put_forks(i);
    }

    iniciar();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

}
