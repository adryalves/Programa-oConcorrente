/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor....: Adryellen Alves de Souza
Matrícula: 202110189
Inicio...: 15 de marco de 2023
Alteracao: 18 de marco de 2023
Nome.....: Principal.cpp
Funcao...: Criacao de um modelo de arvore genealofica utilizando o comando fork em C++
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
#include <iostream>
#include <sys/types.h>
#include <unistd.h>
using namespace std;
pid_t filho1, filho2, filho3, neto1, neto2, bisneto;
int main()
{

  cout << "Processo Pai nasce com ID= " << getpid() << endl;
  sleep(22); // Se passa 22 anos para o filho 1 nascer

  filho1 = fork();
  switch (filho1)
  {        // Coloquei um switch aqui e dentro usei if's para ser mais facil de identificar em qual laco condicional se encontra o processo principal(do pai)
  case -1: // Caso o fork retorne menos, significa que houve um erro na criacao, e ai ele entrara nesse condicional para encerrar o processo
    exit(1);

  case 0: // Caso o processo foi bem sucedido, retorna 0, e aqui estamos no processo filho1

    cout << "Processo primeiro Filho ID= " << getpid() << " pai ID= " << getppid() << endl;
    sleep(16);      // Agora o pai tera (22 + 16 = 38 anos), a idade que o neto nasce
    neto1 = fork(); // Criando processo neto1

    if (neto1 == -1) // Analisando se foi criado corretamente o processo
    {
      exit(1);
    }

    else if (neto1 == 0)
    { // Dentro do processo Neto1
      cout << "Processo primeiro Neto ID= " << getpid() << " pai ID= " << getppid() << endl;
      sleep(30);        // Ja se passaram 38 anos, ai falta 30 para o bisneto nascer, por isso o sleep(30)
      bisneto = fork(); // Passou-se 68 anos

      if (bisneto == -1) // Analisando se foi criado corretamente o processo
      {
        exit(1);
      }
      else if (bisneto == 0) // Dentro do processo do bisneto
      {
        cout << "Processo bisneto ID= " << getpid() << " pai ID= " << getppid() << endl;

        sleep(12); // o bisneto morre aos 12, passa 12 anos apos o seu nascimento
        cout << "Processo bisneto ID= " << getpid() << " morre aos 12 anos" << endl;
        exit(0); // Aqui estamos finalizando o processo bisneto
      }
      else
      {           // Saimos do processo bisneto e Aqui ta dentro do processo do Neto1
        sleep(5); // O neto morre com 35, ja se passaram 30 anos que nasceu(o sleep antes do fork do bisneto) agora fazemos esperar mais 5
        cout << endl
             << "Processo primeiro Neto ID= " << getpid() << " morre aos 35 anos" << endl;
        exit(0); // Aqui estamos finalizando o processo neto
      }
    }

    else
    {            // Saimos do processo neto e agora estamos no Processo do filho 1
      sleep(45); // O filho 1 morre com 61 anos, se passou 16 anos que foi quando nasceu o neto, ai fica 61-16 = 45, 45 eh o tempo que precisa passar para ele morrer
      cout << "Processo primeiro Filho ID= " << getpid() << " morre aos 61 anos" << endl;
      exit(0); // Aqui estamos finalizando o processo filho1
      break;
    }

  // Caso nao retorn 0, nem -1, significa que estamos dentro do processo pai, e nao mais no do fork() criado
  default: // Isso significa que aqui estamos no Processo do pai

    sleep(3);        // Se passarm 22 anos para o pai, como o filho 2 nasce quando o pai tem 25 anos, fazemos esperar mais 3 "anos"
    filho2 = fork(); // Criamos o processo do filho2

    if (filho2 == -1) // Analisando se foi criado corretamente o processo
    {
      exit(1);
    }
    else if (filho2 == 0)
    {
      cout << "Processo segundo Filho ID= " << getpid() << " pai ID= " << getppid() << endl;
      sleep(20);      // ja se passou 25 anos, e como o neto dois nasce depois de 45 anos do processo pai, fazemos esperar 20 "anos" (20 + 25 = 45)
      neto2 = fork(); // Criando o processo do neto2

      if (neto2 == -1) // Analisando se foi criado corretamente o processo
      {
        exit(1);
      }
      else if (neto2 == 0)
      {
        cout << "Processo segundo Neto ID= " << getpid() << " pai ID= " << getppid() << endl;
        sleep(33); // o segundo neto morre aos 33, ai ele nasce e tem a espera de 33 anos aqui

        cout << "Processo segundo Neto ID= " << getpid() << " morre aos 33 anos " << endl;
        exit(0); // Finalizando o processo do neto1
      }

      else
      {            // Processo do filho 2
        sleep(35); // Se passou 20 anos que o filho nasceu ja, e como ele morre com 55, fazemos esperar mais 35 anos
        cout << "Processo segundo Filho ID= " << getpid() << " morre aos 55 anos " << endl;
        exit(0); // Finalizando o processo do filho 2
      }
    }

    // Voltamos ao processo Pai
    sleep(7);        // Ja se passaram 25 anos do pai, e como ele tem o terceiro filho aos 32, fazemos esperar 7 anos
    filho3 = fork(); // Criamos o processo do filho3

    if (filho3 == -1)
    { // Analisando se foi criado corretamente o processo
      exit(1);
    }
    else if (filho3 == 0)
    {
      cout << "Processo terceiro Filho ID= " << getpid() << " pai ID= " << getppid() << endl;
      sleep(55); // o filho 3 morre com 55 anos, entao depois que nasce fazemos esperar 55 anos
      cout << "Processo terceiro Filho ID= " << getpid() << " morre aos 55 anos " << endl;
      exit(0); // Finalizando o processo filho3
    }

    sleep(58); // o pai aqui ja viveu 32 anos, como ele morre com 90 anos, falta 58 anos para o pai morrer, eh o tempo que fazemos esperar para o pai morrer
    cout << "Processo Pai ID=  " << getpid() << " morre aos 90 anos";
    return 0; // O programa finaliza e o pai morre aos 90 anos
    break;
  }
}
