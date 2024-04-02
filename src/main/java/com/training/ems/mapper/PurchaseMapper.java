package com.training.ems.mapper;

import com.training.ems.config.MapstructConfig;
import com.training.ems.dto.PurchaseDto;
import com.training.ems.entities.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public interface PurchaseMapper {
    PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);
    @Mapping(target = "id", ignore = true)
    Purchase toEntity(PurchaseDto purchaseDto);
    PurchaseDto toDto(Purchase purchase);
}
