package com.umadecruz.app.util;

import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

import java.util.List;

@UtilityClass
public class MapperUtil {

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass, ModelMapper mapper) {
        return source
                .stream()
                .map(element -> mapper.map(element, targetClass))
                .toList();
    }
}


