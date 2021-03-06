package com.company.task.mapper;

import com.company.task.dao.entity.User;
import com.company.task.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();

    @Mappings({
            @Mapping(target="role.id", source = "roleId"),
            @Mapping(target="password", source = "password",qualifiedByName = "toEncrypted")
    })
    User toEntity(UserDto userDto);

    @Named(value = "toEncrypted")
    default String toEncrypted(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
