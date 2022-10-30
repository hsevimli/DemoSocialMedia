package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.NewCreateUserDto;
import com.bilgeadam.dto.request.UpdateRequestDto;
import com.bilgeadam.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {


    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);

    UserProfile toUserProfile(final NewCreateUserDto dto);

    UserProfile toUserProfile(final UpdateRequestDto dto);

}
