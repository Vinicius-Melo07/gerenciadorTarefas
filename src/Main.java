import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        int proximoId = 1;
        int opcao = 0;

        // isso e um cardapio de opções para ser escolhidas no switch.
        while (opcao !=5) {
            System.out.println("\n===== GERENCIADOR DE TAREFAS =====");
            System.out.println("1 - Adicionar tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Concluir tarefa");
            System.out.println("4 - Remover tarefa");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            // o scanner captura na variavel "opcao" o que foi digitado (a escolha do cardapio acima)
            //essa e uma verificacao para que o usuario digite sempre numeros inteiros e nao outros caracteres
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("digite um número válido.");
                scanner.nextLine();
                continue;
            }

            // isso e o que cada opção do cardapio faz

            switch (opcao) {

                //a opção 1 adiciona uma descrição e um ID (ID automaticamente) ao array de Tarefas
                case 1:
                    System.out.println("Digite a descrição da tarefa: ");
                    String descricao = scanner.nextLine();
                    Tarefa tarefa = new Tarefa(proximoId,descricao);
                    tarefas.add(tarefa);
                    proximoId++;
                    System.out.println("Tarefa Adicionada com sucesso");
                    break;

                    // a opção 2, lista as tarefas.
                case 2:
                    if (tarefas.isEmpty()){
                    System.out.println("Nenhuma Tarefa Cadastrada.");
                        // "isEmpty" é um metodo que verifica se a tarefa esta vazia.
                } else {
                        System.out.println("====== Suas Tarefas ======");
                        for (Tarefa t : tarefas) {
                            String status;
                            if (t.getConcluida()) {
                                status = "concluida";
                            } else {
                                status = "pendente";
                                //basicamente "para cara objeto do tipo "Tarefa" no array de "tarefas", ele sera referenciado na variavel "t"
                                //e faça a logica: se a propriedade "concluida" de tarefa for verdadeira, salve na variavel "status" a string "concluida"
                                // e se for falsa (que e o padrão), salve "pendente".
                            }

                            System.out.println(
                                    "ID: " + t.getId()
                                            + "| Descrição: " + t.getDescricao()
                                            + "| Status: " + status
                            );
                        }
                    }
                    break;

                // a opção 3 conclui uma tarefa cadastrada.
                case 3:
                    System.out.print("Digite o ID da tarefa que deseja Concluir: ");
                    //chamamos o metodo de verificar e inserir a entrada de inteiros validos
                   int idConcluir = verInteiro(scanner);

                    //consideramos que a tarefa nao foi encontrada
                    boolean encontrado = false;

                    for (Tarefa t : tarefas) {
                        if (t.getId() == idConcluir) {
                            if (t.getConcluida()) {
                                System.out.println("Essa tarefa ja esta concluida.");
                            } else {
                                t.concluir();
                                System.out.println("Tarefa concluida com sucesso.");
                            }

                            // aqui consideramos que ela foi encontrada pois entrou no loop, logo podemos alterar "encontrado" para true.
                            encontrado = true;
                            break;
                        }
                    }

                        //isso e uma verificação, se o "encontrado" continuar false, quer dizer que nao entrou no loop que o tranforma em true.
                        // logo ela nao foi encontrada/não existe.
                        if (!encontrado){
                            System.out.println("Tarefa não encontrada.");
                        }
                    break;



                // a opção 4 exclui uma tarefa.
                case 4:
                    System.out.print("Digite o ID da tarefa que deseja remover: ");

                    //chamamos o metodo de verificar e inserir a entrada de inteiros validos
                    int idRemover = verInteiro(scanner);

                    // aqui criamos uma variavel que guarda a tarefa que queremos remover, porem o valor null significa que ela agora nao esta
                    // apontando para nenhum objeto.
                    Tarefa tarefaRemover = null;

                    for (Tarefa t : tarefas) {
                        if (t.getId() == idRemover) {
                            tarefaRemover = t;
                            break;
                        }
                    }

                    if (tarefaRemover != null) {
                        tarefas.remove(tarefaRemover);
                        System.out.println("Tarefa removida com sucesso!");
                    } else {
                        System.out.println("Tarefa não encontrada.");
                    }
                    break;

                case 5:
                    System.out.println("Encerrando o programa");
                    break;

                default:
                    System.out.println("Opção inválida");

            }
        }
        scanner.close();
    }

    //essa e um metodo de verificacao para inputs de inteiros
    // o metodo recebe a entrada do scanner, verifica se e valido, le o numero valido e devolve no return
    public static int verInteiro(Scanner scanner) {

        while (!scanner.hasNextInt()) {
            System.out.println("digite um número inteiro válido.");
            scanner.nextLine();
            System.out.print("Tente novamente: ");
        }

        int numero = scanner.nextInt();
        scanner.nextLine();

        return numero;
    }
}