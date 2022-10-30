package com.bilgeadam.controlller;

import com.bilgeadam.dto.request.ActivateRequestDto;
import com.bilgeadam.dto.request.NewCreateUserDto;
import com.bilgeadam.dto.request.UpdateRequestDto;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserProfileController {

    private final UserProfileService userProfileService;


    @PostMapping("/create")
    public ResponseEntity<Boolean> create(@RequestBody NewCreateUserDto dto) {
        try {
            userProfileService.create(dto);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/findall")
    public ResponseEntity<List<UserProfile>> findall() {

        return ResponseEntity.ok(userProfileService.findAll());
    }

    @PostMapping("/activate")
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateRequestDto dto) {

        return ResponseEntity.ok(userProfileService.activateStatus(dto));
    }


    @PostMapping("/update")
    public ResponseEntity<Boolean> updateProfile(@RequestBody @Valid UpdateRequestDto dto) {

        return ResponseEntity.ok(userProfileService.updateUser(dto));
    }

}
