package com.assignment.game.recommendation.mapper;

import com.assignment.game.recommendation.dto.GamesDTO;
import com.assignment.game.recommendation.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface GamesMapper {

    GamesMapper INSTANCE = Mappers.getMapper(GamesMapper.class);

    GamesDTO entityToDto(Customer customer);

}
