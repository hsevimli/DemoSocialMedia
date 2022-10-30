package com.bilgeadam.service;

import com.bilgeadam.dto.request.ActivateRequestDto;
import com.bilgeadam.dto.request.NewCreateUserDto;
import com.bilgeadam.dto.request.UpdateRequestDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.mapper.IUserProfileMapper;
import com.bilgeadam.repository.IUserProfileRepository;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.repository.enums.Status;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile, Long> {

    private final IUserProfileRepository userProfileRepository;
    private final JwtTokenManager jwtTokenManager;

    public UserProfileService(IUserProfileRepository userProfileRepository, JwtTokenManager jwtTokenManager) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public UserProfile create(NewCreateUserDto dto) {

        return save(IUserProfileMapper.INSTANCE.toUserProfile(dto));
    }

    public Boolean activateStatus(ActivateRequestDto dto) {
        Optional<UserProfile> userProfile = userProfileRepository.findOptionalByAuthid(dto.getId());

        if (userProfile.isEmpty()) {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);

        }
        userProfile.get().setStatus(Status.ACTIVE);
        save(userProfile.get());
        return true;
    }

    public boolean updateUser(UpdateRequestDto dto) {

        Optional<Long> authid = jwtTokenManager.getUserId(dto.getToken());
        //       UserProfile userProfile = IUserProfileMapper.INSTANCE.toUserProfile(dto);

        if (authid.isPresent()) {
            Optional<UserProfile> userProfileDb = userProfileRepository.findOptionalByAuthid(authid.get());
            if (userProfileDb.isPresent()) {
                userProfileDb.get().setEmail(dto.getEmail());
                userProfileDb.get().setAddress(dto.getAddress());
                userProfileDb.get().setAbout(dto.getAbout());
                userProfileDb.get().setName(dto.getName());
                userProfileDb.get().setUsername(dto.getUsername());
                userProfileDb.get().setPhone(dto.getPhone());
                save(userProfileDb.get());
                return true;

            } else {
                throw new UserManagerException(ErrorType.USER_NOT_FOUND);
            }
        } else {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);

        }
    }
}
