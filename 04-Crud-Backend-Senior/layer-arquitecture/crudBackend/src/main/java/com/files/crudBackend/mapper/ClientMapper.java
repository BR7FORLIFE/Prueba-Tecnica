package com.files.crudBackend.mapper;

import com.files.crudBackend.Entity.ClientEntity;
import com.files.crudBackend.model.ClientModel;

public class ClientMapper {

    public static ClientModel toDomain(ClientEntity entity) {
        ClientModel clientModel = new ClientModel();

        clientModel.setId(entity.getId());
        clientModel.setName(entity.getName());
        clientModel.setEmail(entity.getEmail());
        clientModel.setCellphone(entity.getCellphone());
        clientModel.setActive(entity.getActive());
        clientModel.setDeleteAt(entity.getDeleteAt());
        clientModel.setCreateAt(entity.getCreateAt());
        clientModel.setUpdateAt(entity.getUpdateAt());

        return clientModel;
    }

    public static ClientEntity toEntity(ClientModel clientModel) {
        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setName(clientModel.getName());
        ;
        clientEntity.setEmail(clientModel.getEmail());
        clientEntity.setCellphone(clientEntity.getCellphone());
        clientEntity.setActive(clientEntity.getActive());
        clientEntity.setDeleteAt(clientEntity.getDeleteAt());
        clientEntity.setUpdateAt(clientEntity.getUpdateAt());

        return clientEntity;
    }
}
