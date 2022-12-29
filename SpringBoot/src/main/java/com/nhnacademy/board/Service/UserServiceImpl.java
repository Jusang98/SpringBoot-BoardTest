package com.nhnacademy.board.Service;


import com.nhnacademy.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public boolean matches(String name, String pwd) {
        return userRepository.existsByNameAndPwd(name, pwd);
    }
}
