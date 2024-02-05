package com.nikron.tennis.mapper;

public interface Mapper <E1, E2>{
    E1 convertToDto(E2 entity);
    E2 convertToEntity(E1 dto);
}
