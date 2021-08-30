package com.cts.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.cts.login.model.LoginRequest;
import com.cts.login.model.LoginResponse;
import com.cts.login.model.ResponseMessage;
import com.cts.login.model.User;
import com.cts.login.repository.UserRepository;
import com.cts.login.service.UserService;
import com.cts.login.utils.JwtUtil;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoginHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    public Mono<ServerResponse> register(ServerRequest serverRequest) {

        Mono<User> userMono = serverRequest.bodyToMono(User.class);

        return userMono
                .flatMap(user -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(userService.createUser(user), User.class));

    }

    public Mono<ServerResponse> login(ServerRequest serverRequest) {

        Mono<LoginRequest> loginRequest = serverRequest.bodyToMono(LoginRequest.class);

        return loginRequest.flatMap(login -> {
            return userService.findByUsername(login.getUsername())
                    .flatMap(user -> {
                        if (user.getPassword().equals(login.getPassword())) {

                            return ServerResponse.ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(Mono.just(new LoginResponse(jwtUtil.generateToken(user))), User.class);
                        }
                        return ServerResponse.status(HttpStatus.UNAUTHORIZED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromObject(ResponseMessage.getResponse(-1, HttpStatus.UNAUTHORIZED, "Login Failed!")),
                                        ResponseMessage.class);
                    });
            })
            .switchIfEmpty(ServerResponse.status(HttpStatus.UNAUTHORIZED)
                        .body(BodyInserters.fromObject(ResponseMessage.getResponse(-1, HttpStatus.UNAUTHORIZED, "Login Failed!"))));

    }

}
