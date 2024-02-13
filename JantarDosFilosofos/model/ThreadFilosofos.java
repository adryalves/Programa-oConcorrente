/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Inicio...........: 04/05/2023
* Ultima alteracao.:  13 /05/2023
* Nome.............:Jantar dos filosofos, essa é a classe ThreadFilosofos
* Funcao...........: Essa classe que implementa a Thread, é nela que sao criadas as threads quando sao chamados os construtores, aqui que tem o metodo run dela e eh aqui que é feito o codigo para que nao haja problemas de concorrencia, utilizando a oslucao de semaforos, os metodos estao implementados nessa clase
*************************************************************** */
package model;

import java.util.concurrent.Semaphore;

import controller.Controller;
import javafx.application.Platform;
import javafx.scene.control.Slider;

public class ThreadFilosofos extends Thread {

  // int N = 5; /* numero de filosofos */

  // Left == (i+N−1)%N ;

  // RIGHT == (i+1)%N /* numero do vizinho a direita de i */

  public final static int THINKING = 0; /* o filosofo esta pensando */
  public final static int HUNGRY = 1;
  public final static int EATING = 2; /* o filosofo esta comendo */

  public static int state[] = new int[5]; /* arranjo para controlar o estado de cada um */
  public static Semaphore mutex = new Semaphore(1);

  public static Semaphore[] s = new Semaphore[5]; /* um semaforo por filosofo */

  int i; // numero do filosofo
  Controller c;

  final int LEFT;

  final int RIGHT;/* numero do vizinho a direita de i */

  Slider sliderComer;

  Slider sliderPensar;

  int velocidadePensar = 5;
  int VelocidadeComer = 5;

  // private static boolean exit = false;

  private boolean flagPausa = false;

  private int condicao = 0;

  public ThreadFilosofos(int i, Controller c, Slider sliderPensar, Slider sliderComer) {
    this.i = i;
    this.c = c;
    this.LEFT = (i + 5 - 1) % 5;
    this.RIGHT = (i + 1) % 5;
    this.sliderPensar = sliderPensar;
    this.sliderComer = sliderComer;

    modificarSliderPensar(sliderPensar);
    modificarSliderComer(sliderComer);

    /*
     * sliderPensar.valueProperty().addListener((observable, oldValue, newValue) ->
     * {
     * System.out.println("Valor do slider mudou: " + newValue);
     * });
     * 
     * sliderComer.valueProperty().addListener((observable, oldValue, newValue) -> {
     * System.out.println("Valor do slider mudou: " + newValue);
     * });
     * // s[i] = new Semaphore(0);
     */
  }

  /*
   * ***************************************************************
   * Metodo: run
   * Funcao: metodo executado quando as threads sao chamadas com o start, ele
   * chama os metodos que a threda precisa executar infinitamente
   * Parametros: vazio
   * Retorno: void
   */
  @Override
  public void run() {

    while (condicao >= 0) {
      /* repete para sempre */
      while (flagPausa) {
        System.out.println("O filosofo " + i + " esta pausado");
      }
      if (condicao == 0) {
        think(i); /* o filosofo esta pensando */
      }
      if (condicao == 1) {
        take_forks(i); /* pega dois garfos ou bloqueia */
      }
      if (condicao == 2) {
        eat(i); /* hummm, espaguete! */
      }
      if (condicao == 3) {
        condicao = -1;
        put_forks(i); /* devolve os dois garfos a mesa */
      }
      condicao++;
    }
    System.out.println("A thread acabou, foi finalizada");
  }

  public void setPausar() {
    this.flagPausa = !flagPausa;
  }

  public int getcondicao() {
    return condicao;
  }

  /*
   * ***************************************************************
   * Metodo: take_forks
   * Funcao: metodo da implementacao de semaforos para esse problema classico, e
   * eh aqui que ocorre a
   * tentativa de pegar os dois garfos analisando se os dois estao disponiveis
   * para isso, se nao ocorre isso, eles vao para o estadp de bloqueio/espera
   * enquanto nao eh liberado
   * Parametros: i, eh um numero inteiro que se refere ao id da thread, ele eh de
   * 0-5 e cada um representa uma thread que foi criada
   * Retorno: void
   */
  public void take_forks(int i) /* i: o numero do filosofo, de 0 a N–1 */
  {
    c.hungry(i);
    try {
      // System.out.println("O filosofo " + i + " esta tentando pegar o " + s[i] +
      // "garfo");
      mutex.acquire();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // down(&mutex); /* entra na regiao critica */
    state[i] = HUNGRY; /* registra que o filosofo esta faminto */
    test(i); /* tenta pegar dois garfos */
    mutex.release();
    // System.out.println("O filosofo " + i + " esta liberando " + s[i] + "garfo");
    try {
      // System.out.println("O filosofo " + i + " esta tentando pegar outro garfo");
      s[i].acquire();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /*
   * ***************************************************************
   * Metodo: pararThread
   * Funcao: ele seta a condicao para -2 para que o run da thread saia do while e
   * entao a thread finalize, alem disso seta a velocidade de Comer e pensar para
   * 0
   * Parametros: nenhum
   * Retorno: void
   */
  public void pararThread() {
    condicao = -2;
    VelocidadeComer = 0;
    velocidadePensar = 0;

  }

  /*
   * ***************************************************************
   * Metodo: recomecoThread
   * Funcao: ele seta os valores dos dois sliders(comer e pensar) para 1, ou seja
   * volta ao comportamento inicial do sliders
   * Parametros: nenhum
   * Retorno: void
   */
  public void recomecoThread() {
    sliderComer.setValue(1);
    sliderPensar.setValue(1);
  }

  /*
   * ***************************************************************
   * Metodo: put_forks
   * Funcao: metodo da implementacao de semaforos para esse problema classico, e
   * eh aqui que ocorre a liberacao dos garfos, ou seja sao colocados na mesa e
   * liberados para outro filosofo pegar
   * Parametros: i, eh um numero inteiro que se refere ao id da thread, ele eh de
   * 0-5 e cada um representa uma thread que foi criada
   * Retorno: void
   */
  public void put_forks(int i) /* i: o numero do filosofo, de 0 a N–1 */
  {
    int LEFT = (i + 5 - 1) % 5;
    int RIGHT = (i + 1) % 5;
    try {
      mutex.acquire();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // down(&mutex); /* entra na regiao critica */
    state[i] = THINKING; /* o filosofo acabou de comer */
    test(LEFT); /* ve se o vizinho da esquerda pode comer agora */
    test(RIGHT); /* ve se o vizinho da direita pode comer agora */
    mutex.release(); // sai da regiao critica
    c.put_forks(i);
  }

  /*
   * ***************************************************************
   * Metodo: test
   * Funcao: metodo da implementacao de semaforos para esse problema classico, e
   * eh aqui que se analisa se o filosofo que esta usando o metodo esta com fome e
   * se os filosofos a direita e a esquerda nao estao comendo, ou seja utilizando
   * o garfo
   * Parametros: i, eh um numero inteiro que se refere ao id da thread, ele eh de
   * 0-5 e cada um representa uma thread que foi criada
   * Retorno: void
   */
  public void test(int i)/* i: o numero do filosofo, de 0 a N–1 */
  {
    int LEFT = (i + 5 - 1) % 5;
    int RIGHT = (i + 1) % 5;
    if (state[i] == HUNGRY && state[LEFT] != EATING && state[RIGHT] != EATING) {
      state[i] = EATING;
      s[i].release(); // dps daq chamaria para poder modificar a imagem do garfo(sumir da mesa)

    }
  }

  /*
   * ***************************************************************
   * Metodo: eat
   * Funcao: metodo criado paar representar quando o filosofo esta comendo, ele
   * chama por meio da classe controller o metodo eat de la, passando o id
   * referente a thread, para trocar a imagem e
   * a cada iteracao do laco ele da um sleep que eh calculado utilizando a
   * velocidade que
   * esta referente no slider, para que possa ficar menos ou mais tempo pensando
   * Parametros: i, eh um numero inteiro que se refere ao id da thread, ele eh de
   * 0-5 e cada um representa uma thread que foi criada
   * Retorno: void
   */
  public void eat(int i) {

    System.out.println("O filosofo " + i + " esta comendo");
    // for (int j = 0; j < 10; j++) {
    c.eat(i);

    try {
      if (VelocidadeComer == 0) {
        sleep(0);
      } else {
        sleep(20000 / VelocidadeComer);
      }
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // }

  }

  /*
   * ***************************************************************
   * Metodo: think
   * Funcao: metodo criado paar representar quando o filosofo esta pensando, ele
   * chama por meio da classe controller o metodo think de la, passando o i que
   * representa o numero da thread para trocar a imagem e
   * a cada iteracao ele da um sleep que eh calculado utilizando a velocidade que
   * esta referente no slider, para que possa ficar menos ou mais tempo comendo
   * Parametros: i, eh um numero inteiro que se refere ao id da thread, ele eh de
   * 0-5 e cada um representa uma thread que foi criada
   * Retorno: void
   */
  public void think(int i) {
    // for (int j = 0; j < 10; j++) {
    c.think(i);
    try {
      if (velocidadePensar == 0) {
        sleep(0);
      } else {
        sleep(20000 / velocidadePensar);
      }
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // }

    System.out.println("O filosofo " + i + " esta pensando");
    if (i == 0) {
      System.out.println("O tempo esta: " + velocidadePensar);
    }
  }

  /*
   * ***************************************************************
   * Metodo: modificarSliderComer
   * Funcao: serve para modificar/configurar o valor da velocidadeDeComer de forma
   * simultanea
   * com o valor colocado/arrastado pelo slider, paar que seja , modificando assim
   * que o usuario aumentar o diminuir o valor no slider
   * Parametros: slider, ele recebe um elemento slider do tipo Slider que se
   * refere ao slider inserido na tela que representa a velocidade que deseja que
   * o filosofo coma
   * Retorno: void
   */
  public void modificarSliderComer(Slider slider) {
    slider.valueProperty().addListener((observable, oldValue, newValue) -> {
      VelocidadeComer = newValue.intValue();
      // slider.setValue(newValue.intValue());
    });
  }

  /*
   * ***************************************************************
   * Metodo: modificarSliderPensar
   * Funcao: serve para modificar/configurar o valor da velocidadeDePensar de
   * forma simultanea
   * com o valor colocado/arrastado pelo slider, paar que seja ,modificando assim
   * que o usuario aumentar o diminuir o valor no slider
   * Parametros: slider, ele recebe um elemento slider do tipo Slider que se
   * refere ao slider inserido na tela que representa a velocidade que deseja que
   * o filosofo pense
   * Retorno: void
   */
  public void modificarSliderPensar(Slider slider) {
    slider.valueProperty().addListener((observable, oldValue, newValue) -> {
      velocidadePensar = newValue.intValue();
      // slider.setValue(newValue.intValue());

    });

  }

}