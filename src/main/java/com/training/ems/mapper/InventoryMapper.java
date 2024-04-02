package com.training.ems.mapper;

import com.training.ems.config.MapstructConfig;
import com.training.ems.dto.InventoryDto;
import com.training.ems.entities.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public interface InventoryMapper {
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);
//    @Mapping(target = "id", ignore = true)
    Inventory toEntity(InventoryDto inventoryDto);
    InventoryDto toDto(Inventory inventory);
}
