package com.userservice.service;

import com.userservice.dto.UserRequest;
import com.userservice.dto.UserResponse;
import com.userservice.model.User;
import com.userservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor()
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    public void createUser(UserRequest request){
        User user = User.builder()
                .name(request.getName())
                .dob(request.getDob())
                .build();

        userRepository.save(user);
        log.info("User {} is saved", user.getName());
    }

    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToUserResponse).toList();
        //return users.stream().map(p -> mapToUserResponse(p)).toList();
    }

    private UserResponse mapToUserResponse(User u) {
        return UserResponse.builder()
                .id(u.getId())
                .name(u.getName())
                .dob(u.getDob())
                .build();
    }
}
