/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 19/03/2022
* Ultima alteracao.: 24/03/2022
* Nome.............: Avore Genealogica
* Funcao...........: Tem-se a simulacao de uma arvore genealogica por meio da criacao de varias threads, 
* para mostrar o funcionamento de threads que ocorrem de forma concorrente. Essa classe representa a ThreadPai
* que eh a thread principal do nosso programa , pois as outras thread sao filhas dela, entao eh aqui que criamos
* a ThreadPai, colocamos o que sera executado quando ela for chamada e eh aqui que criamos e chamamos outras 3 Threads que serao as threadsFilhos dela
*************************************************************** */
package model;

import Controller.TelaController;

public class ThreadPai extends Thread {

  TelaController tc; // variavel do tipo tela controller
  ThreadFilho threadFilho; // declaramos uma variavel do tipo threadFilho
  ThreadFilho2 threadFilho2; // declaramos uma variavel do tipo threadFilho2
  ThreadFilho3 threadFilho3; // declaramos uma variavel do tipo threadFilho3

  public ThreadPai(TelaController controller) {
    this.tc = controller; // a variavel do tipo tela controller receeb a tela controller que foi passada
    // // como parametro
    threadFilho = new ThreadFilho(tc); // criamos um objeto do tipo threadFilho
    threadFilho2 = new ThreadFilho2(tc); // criamos um objeto do tipo threadFilho2
    threadFilho3 = new ThreadFilho3(tc); // criamos um objeto do tipo threadFilho3
    // criamos esses objetos pois essas 3 threads serao filhas da thread pai(essa
    // aqui) por isso serao inicializada nessa classe e para isso precisamos de um
    // objeto de cada
  }

  public void run() { // metodo proprio thread que eh o que eh executada quando chamamos start da
    // nossa thread

    tc.EscolherImagem("jovem", "pai"); // metodo para escolher imagem la da tela controller passando os 2 parametros
    // correspondentes
    System.out.println("Pai nasce");
    for (int i = 0; i <= 90; i++) { // usamos o for para que a thread possa ser executada no tempo correspondente
      // que deve estar rodando e viva e
      // possa ir sendo incrementada nesse laço, sendo feita as devidas alteracoes
      switch (i) { // utilizamos um switch para analisar a idade que se encontra a thread(i
        // representa a idade) e qual acao deve ser tomada a depender disso

        case 22:
          threadFilho.start(); // quando a thread pai ja estiver rodando por 22 segundos, ou seja tem 22
                               // "anos", usamos o objeto do tipo thread filho1 que criamos para dar um start
                               // na
                               // thread filho1,
          // e assim iniciar a thread que eh filha dessa aqui, aqui dentro
          break;
        case 25:
          tc.EscolherImagem("adulto", "pai");
          threadFilho2.start(); // quando a thread pai ja estiver rodando por 25 segundos, ou seja tem 25
          // "anos", usamos o objeto do tipo thread filho2 que criamos para dar um start
          // na
          // thread filho2,
          // e assim iniciar a thread que eh filha dessa aqui, aqui dentro

          break;
        case 32:
          threadFilho3.start(); // quando a thread pai ja estiver rodando por 32 segundos, ou seja tem 32
          // "anos", usamos o objeto do tipo thread filho3 que criamos para dar um start
          // na
          // thread filho3,
          // e assim iniciar a thread que eh filha dessa aqui, aqui dentro

          break;

        case 50:
          tc.EscolherImagem("velho", "pai");
          break;

        case 80: {
          tc.EscolherImagem("idoso", "pai");
          break;
        }
        case 90:
          tc.EscolherImagem("morto", "pai");
          tc.TornarMorto("pai"); // metodo para ajudar na representacao da morte da thread, passando apenas um
          // parametro
          break;
      }
      tc.MostrarAno(i);
      tc.MostrarIdadePai(i); // passando o i que esta sendo incrementado para o metodo que serve para exibir
      // na tela essa idade, a cada iteracao do for vai sendo chamado o metodo e sendo
      // o valor correpondente de i
      try {
        sleep(1000); // aqui nos fazemos a thread descansar por 1s, para que possa rodar no tempo
        // correto, usamos um metodo do proprio do java, e ela vai dormir por 1s a cada
        // execucao do for
      } catch (InterruptedException e) { // o metodo sleep nos obriga a tratar essa excecao

        e.printStackTrace();
      }

    }

    System.out.println("Pai morre");
    return;
  }
}
