package com.sistema;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaEventos sistema = new SistemaEventos();

        // Carrega os eventos salvos no arquivo
        sistema.carregarEventos();

        while (true) {
            System.out.println("\n--- Sistema de Eventos ---");
            System.out.println("1. Cadastrar evento");
            System.out.println("2. Listar eventos");
            System.out.println("3. Sair");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpa o buffer do teclado

            switch (opcao) {
                case 1:
                    // Cadastro de evento
                    System.out.print("Nome do evento: ");
                    String nome = scanner.nextLine();

                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();

                    System.out.print("Categoria (ex: festa, show, esportivo): ");
                    String categoria = scanner.nextLine();

                    System.out.print("Horário (yyyy-MM-ddTHH:mm): ");
                    String horarioStr = scanner.nextLine();

                    // Converte para LocalDateTime
                    LocalDateTime horario = LocalDateTime.parse(horarioStr);

                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();

                    Evento evento = new Evento(nome, endereco, categoria, horario, descricao);
                    sistema.cadastrarEvento(evento);
                    System.out.println("Evento cadastrado com sucesso!");
                    break;

                case 2:
                    // Listar eventos
                    List<Evento> eventos = sistema.listarEventos();
                    if (eventos.isEmpty()) {
                        System.out.println("Nenhum evento cadastrado.");
                    } else {
                        System.out.println("Lista de eventos:");
                        for (int i = 0; i < eventos.size(); i++) {
                            Evento e = eventos.get(i);
                            System.out.println((i + 1) + ". " + e.getNome() + " - " + e.getHorario());
                        }
                    }
                    break;

                case 3:
                    // Sair: salvar e encerrar
                    sistema.salvarEventos();
                    System.out.println("Sistema salvo. Até logo!");
                    scanner.close(); // fecha o Scanner ao sair
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        // Fechar o scanner ao invés de criar nova conexão
        // (Na prática, o System.exit(0) já encerra o programa, mas boas práticas dizem fechar o scanner)
        // Como o System.exit já sai do programa, o scanner será fechado pelo sistema, mas é bom colocar o close antes de System.exit.  
        // Alternativamente, colocar scanner.close() antes do System.exit(0) na opção 3.
    }
}