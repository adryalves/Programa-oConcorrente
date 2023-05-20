/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 19/03/2022
* Ultima alteracao.: 24/03/2022
* Nome.............: Avore Genealogica
* Funcao...........: Tem-se a simulacao de uma arvore genealogica por meio da criacao de varias threads, 
* para mostrar o funcionamento de threads que ocorrem de forma concorrente. Essa classe eh onde criamos 
* a ThreadNeto1, que sera filha da ThreadFilho, e que sera pai de outra Thread, eh aqui que colocamos o que 
* sera executado quando essa thread filho for chamada e eh aqui que criamos a ThreadBisneto que sera filha dela.
*************************************************************** */
package model;

import Controller.TelaController;

public class ThreadNeto1 extends Thread { // extende a classe de thread
  ThreadBisneto threadBisneto; // aqui criamos um objeto do tipo bisneto, ja que nossa thread neto1 tera uma
  // thread filho que eh a bisneto
  TelaController tc; // variavel da tela controller

  public ThreadNeto1(TelaController controller) { // construtor da thread que recebe a tela controller como parametro
    this.tc = controller; // a variavel do tipo tela controller receeb a tela controller que foi passada
                          // como parametro
    threadBisneto = new ThreadBisneto(tc); // aqui criamos o objeto da thread que sera filha dessa thread e passamos a
                                           // tela
    // construtor para ela

  }

  @Override
  public void run() { // metodo proprio thread que eh o que eh executada quando chamamos start da
                      // nossa thread
    tc.EscolherImagem("bebe", "neto1"); // chamando metodo escolher imagem passando os 2 parametros paar que
    // la no tela controller de acordo com os parametros seja colocada a
    // imagem correspondente na interface
    System.out.println("Neto1 nasce");
    // TODO Auto-generated method stub
    for (int i = 0; i <= 35; i++) { // usamos o for para que a thread possa ser executada no tempo correspondente
                                    // que deve estar rodando e viva e
      // possa ir sendo incrementada nesse laço, sendo feita as devidas alteracoes

      switch (i) { // utilizamos um switch para analisar a idade que se encontra a thread(i
        // representa a idade) e qual acao deve ser tomada a depender disso
        case 7:
          tc.EscolherImagem("crianca", "neto1");
          break;
        case 30:
          threadBisneto.start(); // usamos o obejto do tipo bisneto que criamos para dar um start na thread
                                 // bisneto,
          // e assim iniciar a thread que eh filha dessa aqui, aqui dentro
          tc.EscolherImagem("jovem", "filho2");
          tc.EscolherImagem("adulto", "neto1");
          break;

        case 35:
          tc.EscolherImagem("morto", "neto1");
          tc.TornarMorto("neto1"); // metodo para ajudar na representacao da morte da thread, passando apenas um
          // parametro
      }
      tc.MostrarIdadeNeto1(i); // passando o i que esta sendo incrementado para o metodo que serve para exibir
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
    // morre e mudo a foto
    System.out.println("Neto morre");

  }

}
