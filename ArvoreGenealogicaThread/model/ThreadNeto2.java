/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 19/03/2022
* Ultima alteracao.: 24/03/2022
* Nome.............: Avore Genealogica
* Funcao...........: Tem-se a simulacao de uma arvore genealogica por meio da criacao de varias threads, 
* para mostrar o funcionamento de threads que ocorrem de forma concorrente. Essa classe representa a ThreadNeto2, 
* que eh filha da threadPai, aqui que temos o metodo run dela e as tarefas que ela executa.
*************************************************************** */
package model;

import Controller.TelaController;

public class ThreadNeto2 extends Thread { // extende thread

  TelaController tc; // variavel tela controller

  public ThreadNeto2(TelaController controller) { // construtor da thread que recebe o parametro de tela controlle que
    // passamos la na tela controller, para que seja possivel ele
    // acessar ela e essa thread modificar a interface
    this.tc = controller; // a variavel de tela controller recebe o objeto do tipo tela controller que foi
    // passsado como parametro
  }

  @Override
  public void run() { // metodo proprio thread que eh o que eh executada quando chamamos start da
    // nossa thread
    tc.EscolherImagem("bebe", "neto2"); // metodo para escolher imagem la da tela controller passando os 2 parametros
    // correspondentes
    System.out.println("Neto2 nasceu");
    for (int i = 0; i <= 33; i++) { // usamos o for para que a thread possa ser executada no tempo correspondente
      // que deve estar rodando e viva e
      // possa ir sendo incrementada nesse laço, sendo feita as devidas alteracoes

      switch (i) { // utilizamos um switch para analisar a idade que se encontra a thread(i
        // representa a idade) e qual acao deve ser tomada a depender disso
        case 23:
          tc.EscolherImagem("jovem", "neto2");
          break;
        case 28:
          tc.EscolherImagem("adulto", "neto2");
          break;
        case 33:
          tc.EscolherImagem("morto", "neto2");
          tc.TornarMorto("neto2"); // metodo para ajudar na representacao da morte da thread, passando apenas um
          // parametro
          break;
      }
      tc.MostrarIdadeNeto2(i); // passando o i que esta sendo incrementado para o metodo que serve para exibir
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
    System.out.println("Neto 2 morreu");
  }
}
