package com.e_commerce_microservice_app.user_service.mapper;

import com.e_commerce_microservice_app.user_service.dto.UserCreateDTO;
import com.e_commerce_microservice_app.user_service.dto.UserDTO;
import com.e_commerce_microservice_app.user_service.dto.UserUpdateDTO;
import com.e_commerce_microservice_app.user_service.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserCreateDTO userCreateDTO);


    List<UserDTO> toUserDTOList(List<User> userList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserUpdateDTO dto, @MappingTarget User user);

}
