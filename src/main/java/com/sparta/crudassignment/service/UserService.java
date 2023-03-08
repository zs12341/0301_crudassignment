package com.sparta.crudassignment.service;

import com.sparta.crudassignment.dto.LoginRequestDto;
import com.sparta.crudassignment.dto.MessageResponse;
import com.sparta.crudassignment.dto.SignupRequestDto;
import com.sparta.crudassignment.dto.StatusEnum;
import com.sparta.crudassignment.entity.User;
import com.sparta.crudassignment.entity.UserRoleEnum;
import com.sparta.crudassignment.jwt.JwtUtil;
import com.sparta.crudassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    // 모든 쿼리가 하나의 트랜잭션으로 묶이게 된다.
    @Transactional
    public MessageResponse signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        //Optional : java.util / 예외처리 , null값 처리를 위한 클래스, null값 반환보다 optional 객체를
        //반환하는 것이 안전하기 때문에 사용한다.
        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) { // == found가 null이 아니라면 (isPresent는 Optional의 메소드)
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);
        userRepository.save(user);
        return new MessageResponse(StatusEnum.OK) ;
    }

    @Transactional(readOnly = true)
    public MessageResponse login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                //왜 비밀번호 확인에는 throw 를 쓰고 여기는 안쓰지? findByUsername이 Optional로 정의되어서
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        // 비밀번호 확인
        if(!user.getPassword().equals(password)){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
        return new MessageResponse(StatusEnum.OK);
    }
}