package dev.alissonmds00.FIPE.Veiculos.services;

import java.util.List;

public interface IConvertDados {
    <T> T converteDados(String json, Class<T> classe);
    <T> List<T> obterLista(String json, Class<T> classe);
}
