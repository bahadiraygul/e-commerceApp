package com.e_commerce_microservice_app.user_service.services;

import com.e_commerce_microservice_app.user_service.common.AESUtil;
import com.e_commerce_microservice_app.user_service.common.DemoException;
import com.e_commerce_microservice_app.user_service.common.EncryptionException;
import com.e_commerce_microservice_app.user_service.dto.UserCreateDTO;
import com.e_commerce_microservice_app.user_service.dto.UserDTO;
import com.e_commerce_microservice_app.user_service.dto.UserUpdateDTO;
import com.e_commerce_microservice_app.user_service.entity.User;
import com.e_commerce_microservice_app.user_service.enums.ExceptionEnum;
import com.e_commerce_microservice_app.user_service.mapper.UserMapper;
import com.e_commerce_microservice_app.user_service.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Value("${encryption.secret-key}")
    private String secretKeyBase64;

    private SecretKey secretKey;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @PostConstruct
    public void init(){
        try{
            byte[] decodeKey = Base64.getDecoder().decode(secretKeyBase64);
            secretKey = new SecretKeySpec(decodeKey, 0, decodeKey.length, "AES");
        }catch (IllegalArgumentException e){
            throw new EncryptionException("Failed to decode encryption secret key", e);
        }
    }

    public void createUser(UserCreateDTO userCreateDTO){
        User user = userMapper.toEntity(userCreateDTO);
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));

        encryptSensitiveData(userCreateDTO, user);
        userRepository.save(user);
    }

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            throw new DemoException(UserService.class, ExceptionEnum.NO_DATA_FOUND);
        }
        return userMapper.toUserDTOList(users);
    }

    public UserDTO getUserById(Long id){
        return userMapper.userToUserDTO(userRepository.findById(id)
                .orElseThrow(() -> new DemoException(UserService.class, ExceptionEnum.USER_NOT_FOUND)));

    }

    public void deleteUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new DemoException(UserService.class, ExceptionEnum.USER_NOT_FOUND));
        userRepository.delete(user);
    }

    public User updateUser(Long userId, UserUpdateDTO userUpdateDTO){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DemoException(UserService.class, ExceptionEnum.USER_NOT_FOUND));

        if(userUpdateDTO.getPassword() != null && !userUpdateDTO.getPassword().isBlank()){
            userUpdateDTO.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        }

        userMapper.updateUserFromDto(userUpdateDTO, user);
        encryptSensitiveData(userUpdateDTO, user);
        return userRepository.save(user);
    }

    private void encryptSensitiveData(Object dto, User user){
        try{
            if(dto instanceof UserCreateDTO userCreateDTO){
                encryptAndSet(userCreateDTO.getEmail(), userCreateDTO.getPhone(), user);
            }else if(dto instanceof UserUpdateDTO userUpdateDTO){
                encryptAndSet(userUpdateDTO.getEmail(),userUpdateDTO.getPhone(), user);
            }
        }catch (Exception e){
            throw new EncryptionException("Failed to encrypt sensitive data", e);
        }
    }

    private void encryptAndSet(String email, String phone, User user) throws Exception{
        if(email != null && !email.isBlank()){
            user.setEmail(AESUtil.encrypt(email, secretKey));
        }
        if(phone != null && !phone.isBlank()){
            user.setPhone(AESUtil.encrypt(phone, secretKey));
        }
    }
}
