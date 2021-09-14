package com.redhat.samples.fruits.rest;

import com.redhat.fruits.catalog.dto.FruitDTO;
import com.redhat.fruits.catalog.dto.FruitRequestDTO;
import com.redhat.samples.fruits.domain.Fruit;
import com.redhat.samples.fruits.domain.Origin;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
/**
 * @author laurent
 */
public interface FruitMapper {

   Fruit fromResource(FruitRequestDTO fruit);

   FruitDTO toResource(Fruit fruit);

   List<FruitDTO> toResources(List<Fruit> fruits);

   default Origin fromResource(FruitDTO.OriginEnum origin) {
      return Origin.fromValue(origin.getValue());
   }

   default FruitDTO.OriginEnum toResource(Origin origin) {
      return FruitDTO.OriginEnum.fromValue(origin.getValue());
   }
}
