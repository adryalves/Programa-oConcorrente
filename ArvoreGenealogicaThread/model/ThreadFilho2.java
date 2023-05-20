/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 19/03/2022
* Ultima alteracao.: 24/03/2022
* Nome.............: Avore Genealogica
* Funcao...........: Tem-se a simulacao de uma arvore genealogica por meio da criacao de varias threads, 
* para mostrar o funcionamento de threads que ocorrem de forma concorrente. Essa classe eh onde criamos 
* a ThreadFilho2, que sera filha da threadPai, e que sera pai de outra Thread, eh aqui que colocamos o que 
* sera executado quando essa thread filho2 for chamada e eh aqui que criamos a Thread Neto2 que sera filha dela.
*************************************************************** */
package model;

import Controller.TelaController;

public class ThreadFilho2 extends Thread {
  ThreadNeto2 threadNeto2; // aqui criamos um objeto do tipo neto2, ja que nossa thread filho2 tera uma
  // thread filho que eh a neto2
  TelaController tc; // variavel do tipo tela controller

  public ThreadFilho2(TelaController controller) { // construtor da thread que recebe a tela controller como parametro
    this.tc = controller; // a variavel do tipo tela controller receeb a tela controller que foi passada
                          // // como parametro
    threadNeto2 = new ThreadNeto2(tc); // aqui criamos o objeto da thread que sera filha dessa thread e passamos a tela
    // construtor para ela

  }

  @Override
  public void run() { // metodo proprio thread que eh o que eh executada quando chamamos start da
                      // nossa thread

    tc.EscolherImagem("bebe", "filho2"); // metodo para escolher imagem la da tela controller passando os 2 parametros
    // correspondentes
    System.out.println("Filho 2 nasce");
    for (int i = 0; i <= 55; i++) { // usamos o for para que a thread possa ser executada no tempo correspondente
                                    // que deve estar rodando e viva e
      // possa ir sendo incrementada nesse laço, sendo feita as devidas alteracoes
      switch (i) { // utilizamos um switch para analisar a idade que se encontra a thread(i
        // representa a idade) e qual acao deve ser tomada a depender disso

        case 7:
          tc.EscolherImagem("crianca", "filho2");
          break;
        case 20:
          threadNeto2.start(); // usamos o objeto do tipo neto2 que criamos para dar um start na thread neto2,
                               // e assim iniciar a thread que eh filha dessa aqui, aqui dentro
          tc.EscolherImagem("jovem", "filho2");
          break;

        case 43:
          tc.EscolherImagem("adulto", "filho2");
          break;

        case 48:
          tc.EscolherImagem("velho", "filho2");
          break;

        case 50:
          tc.EscolherImagem("idoso", "filho2");
          break;
        case 55:
          tc.EscolherImagem("morto", "filho2");
          tc.TornarMorto("filho2"); // metodo para ajudar na representacao da morte da thread, passando apenas um
          // parametro
          break;

      }
      tc.MostrarIdadeFilho2(i); // passando o i que esta sendo incrementado para o metodo que serve para exibir
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
    System.out.println("Filho 2 morre");
  }

}