package com.sistema;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class SistemaEventos {
    private List<Evento> listaEventos = new ArrayList<>();

    public void carregarEventos() {
        try (BufferedReader br = new BufferedReader(new FileReader("events.data"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String nome = dados[0];
                String endereco = dados[1];
                String categoria = dados[2];
                LocalDateTime horario = LocalDateTime.parse(dados[3]);
                String descricao = dados[4];

                Evento evento = new Evento(nome, endereco, categoria, horario, descricao);
                listaEventos.add(evento);
            }
        } catch (FileNotFoundException e) {
            // Arquivo não existe, ignora
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvarEventos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("events.data"))) {
            for (Evento e : listaEventos) {
                String linha = e.getNome() + "," + e.getEndereco() + "," + e.getCategoria() + "," + e.getHorario().toString() + "," + e.getDescricao();
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cadastrarEvento(Evento e) {
        listaEventos.add(e);
    }

    public List<Evento> listarEventos() {
        return listaEventos;
    }
}