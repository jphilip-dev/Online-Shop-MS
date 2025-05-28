package com.jphilips.onlineshop.auth.service.user;

import com.jphilips.onlineshop.auth.entity.User;
import com.jphilips.onlineshop.auth.exception.UserNotFoundException;
import com.jphilips.onlineshop.auth.exception.UserOwnershipException;
import com.jphilips.onlineshop.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.jphilips.onlineshop.shared.exception.ErrorCode.UNAUTHORIZED;
import static com.jphilips.onlineshop.shared.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceHelper {

    private final UserRepository userRepository;

    public User validateUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }

    public User validateUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }

    public void ownershipCheck(String userEmail, String headerEmail){
        if (!userEmail.equals(headerEmail)){
            throw new UserOwnershipException(UNAUTHORIZED);
        }
    }
}
