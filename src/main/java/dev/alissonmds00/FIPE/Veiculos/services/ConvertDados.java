package dev.alissonmds00.FIPE.Veiculos.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConvertDados implements IConvertDados{
    ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T converteDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        //O mapper.getTypeFactory() pega coleção de dados/array do Json e transforma em Array
        CollectionType lista = mapper.getTypeFactory().
                //Criação de coleção do tipo lista contendo objeto da classe escolhida
                constructCollectionType(List.class, classe);
        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
