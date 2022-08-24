package com.nongsa.controller.api;

import com.nongsa.config.auth.PrincipalDetails;
import com.nongsa.dto.JoinDto;
import com.nongsa.dto.ResponseDto;
import com.nongsa.dto.SubscribeDto;
import com.nongsa.model.User;
import com.nongsa.service.SubscribeService;
import com.nongsa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final SubscribeService subscribeService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/api/user/{pageUserId}/subscribe")
    public ResponseEntity<?> subscribeList(@PathVariable Long pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<SubscribeDto> subscribeDto = subscribeService.following(principalDetails.getUser().getId(), pageUserId);
        return new ResponseEntity<>(new ResponseDto<>(1, "구독중 리스트 불러오기 성공", subscribeDto), HttpStatus.OK);
    }

    @GetMapping("/api/user/{pageUserId}/subscribed")
    public ResponseEntity<?> subscribedList(@PathVariable Long pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<SubscribeDto> subscribeDto = subscribeService.follower(principalDetails.getUser().getId(), pageUserId);
        return new ResponseEntity<>(new ResponseDto<>(1, "구독자 리스트 불러오기 성공", subscribeDto), HttpStatus.OK);
    }

    @PostMapping("/auth/join")
    public ResponseEntity<?> save(@Valid @RequestBody JoinDto joinDto, BindingResult bindingResult) {
        User user = User.createUser(joinDto, passwordEncoder);
        userService.saveUser(user);
        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입성공", null), HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<?> update(
            @Valid @RequestBody User user,
            BindingResult bindingResult,
            @AuthenticationPrincipal PrincipalDetails principalDetail) {
        User userEntity = userService.update(user);
        principalDetail.setUser(userEntity);
        return new ResponseEntity<>(new ResponseDto<>(1, "회원수정성공", null), HttpStatus.OK);
    }

}
