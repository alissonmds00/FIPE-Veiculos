package dev.alissonmds00.FIPE.Veiculos.main;

import dev.alissonmds00.FIPE.Veiculos.model.Dados;
import dev.alissonmds00.FIPE.Veiculos.model.Modelos;
import dev.alissonmds00.FIPE.Veiculos.model.Veiculo;
import dev.alissonmds00.FIPE.Veiculos.services.API;
import dev.alissonmds00.FIPE.Veiculos.services.ConvertDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    Scanner input = new Scanner(System.in);
    API api = new API();
    ConvertDados conversor = new ConvertDados();
    final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void iniciarMenu() {

        System.out.println("""
                Qual das opções você deseja consultar?
                1 - Carros
                2 - Motos
                3 - Caminhoes""");
        String escolha = input.nextLine();
        String endereco;

        if (escolha.equals("1") || escolha.contains("carr")) {
            endereco = URL_BASE + "carros/marcas";
        }
        else if (escolha.equals("2") || escolha.contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        var json = api.obterDados(endereco);
        var modelos = conversor.obterLista(json, Dados.class);
        //Todas opções de veículos do tipo escolhido

        System.out.println("\nModelos de veículo:");
        modelos.stream().sorted(Comparator.comparing(Dados::nome)).
                forEach(System.out::println);

        System.out.println("Qual o código da marca para consultar?");
        var codigoMarca = input.nextLine();
        endereco = "%s/%s/modelos".formatted(endereco, codigoMarca);
        json = api.obterDados(endereco);
        var modelosLista = conversor.converteDados(json, Modelos.class);
        System.out.println("aqui");
        System.out.println(modelosLista);
        System.out.println("\nModelos dessa marca: ");
        modelosLista.modelos().stream().sorted(Comparator.comparing(Dados::nome)).
                forEach(System.out::println);

        System.out.println("Digite um trecho do nome do carro a ser buscado: ");
        var nomeVeiculo = input.nextLine();
        List<Dados> modelosFiltrados = modelosLista.modelos().stream().
                filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase())).
                collect(Collectors.toList());
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite o código do modelo desejado para obter os valores de avaliação: ");
        var codigoModelo = input.nextLine();
        endereco = "%s/%s/anos".formatted(endereco, codigoModelo);
        json = api.obterDados(endereco);
        List<Dados> anos = conversor.obterLista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();
        for (int i = 0; i < anos.size(); i++) {
            var anosEndereco = "%s/%s".formatted(endereco, anos.get(i).codigo());
            json = api.obterDados(anosEndereco);
            Veiculo veiculo = conversor.converteDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }
        System.out.println("Todos os veículos filtrados com avaliações por ano");
        veiculos.forEach(System.out::println);


    }
}
