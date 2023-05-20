/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 19/03/2022
* Ultima alteracao.: 24/03/2022
* Nome.............: Avore Genealogica
* Funcao...........: Tem-se a simulacao de uma arvore genealogica por meio da criacao de varias threads, 
* para mostrar o funcionamento de threads que ocorrem de forma concorrente. Essa classe representa a ThreadBisneto, 
* que eh filha da threadNeto, aqui que temos o metodo run dela e as tarefas que ela executa.
*************************************************************** */
package model;

import Controller.TelaController;

public class ThreadBisneto extends Thread {
  TelaController tc; // variavel de tela controller

  public ThreadBisneto(TelaController controller) { // construtor da thread que recebe o parametro de tela controlle que
                                                    // passamos la na tela controller, para que seja possivel ele
                                                    // acessar ela e essa thread modificar a interface
    this.tc = controller;

  }

  @Override
  public void run() { // metodo proprio e obrigatorio de uma thread,eh onde tem as linhas que sao
                      // executadas quando damos um start nela
    // TODO Auto-generated method stub
    tc.EscolherImagem("bebe", "bisneto"); // aqui usamos o nosso metodo que temos para escolher a imagem que aparece na
                                          // tela, passamos primeiro a epoca e depois qual é a thread
    System.out.println("Bisneto nasce");

    // foto....
    for (int i = 0; i <= 12; i++) { // esse for eh quem vai contabilizar o tempo da thread e quem vai fazer ela
                                    // rodar pelo tempo correto e pedido

      ///
      switch (i) { // usamos um switch para que possamos avaliar qual a idade que a thread se
                   // encontra e fazer as devidas modificacoes na tela
        case 10:
          tc.EscolherImagem("crianca", "bisneto"); // passamos os parametros para o metodo que serve para trocar a
                                                   // imagem da tela, chamamos o metodo por meio do objeto de tela
                                                   // controller
          break;

        case 12:
          tc.EscolherImagem("morto", "bisneto");
          tc.TornarMorto("bisneto"); // esse eh o metodo para ajudar na representacao das mortes das threads,
                                     // passamos apenas qual a thread que eh(pai, filho1...)
      }
      tc.MostrarIdadeBisneto(i); // chamamos o metodo que serve para mostrar a idade do bisneto na tela, ele vai
                                 // ser mudado, entao passamos o i como parametro que a cada execucao do for, tem
                                 // um valor diferente e vai sendo incrementado e passado como parametro para
                                 // esse metodo
      try {
        sleep(1000); // aqui nos fazemos a thread descansar por 1s, para que possa rodar no tempo
                     // correto, usamos um metodo do proprio do java, e ela vai dormir por 1s a cada
                     // execucao do for
      } catch (InterruptedException e) { // o proprio metodo nos obriga a tratar essa excecao
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    System.out.println("Bisneto morreu"); // apenas para controle, so imprime no console
  }
}
