package com.e_commerce_microservice_app.user_service.controller;

import com.e_commerce_microservice_app.user_service.common.BaseResponse;
import com.e_commerce_microservice_app.user_service.common.ResponseHandler;
import com.e_commerce_microservice_app.user_service.dto.UserCreateDTO;
import com.e_commerce_microservice_app.user_service.dto.UserDTO;
import com.e_commerce_microservice_app.user_service.dto.UserUpdateDTO;
import com.e_commerce_microservice_app.user_service.enums.MessageEnum;
import com.e_commerce_microservice_app.user_service.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id")
    public BaseResponse<UserDTO> getUserById(@PathVariable Long id){
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, userService.getUserById(id), MessageEnum.USER_FOUND);
    }

    @PostMapping
    public BaseResponse<UserDTO> createUser(@PathVariable @Valid UserCreateDTO userCreateDTO){
        userService.createUser(userCreateDTO);
        return ResponseHandler.responseEntityWrapper(HttpStatus.CREATED, MessageEnum.USER_CREATED_SUCCESSFULLY);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<String> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, MessageEnum.USER_DELETED);
    }

    @PutMapping("/id")
    public BaseResponse<String> updateUser(@RequestBody @Valid UserUpdateDTO userUpdateDTO, @PathVariable Long id){
        userService.updateUser(id, userUpdateDTO);
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, MessageEnum.USER_UPDATED);
    }
}
