package com.e_commerce_microservice_app.user_service.dto;


import com.e_commerce_microservice_app.user_service.validation.TCNoValid;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDTO {

    @TCNoValid
    @JsonProperty("tc_no")
    private String tcNo;

    @NotBlank(message = "Username must not be null and must contain 1 or more characters")
    @JsonProperty("user_name")
    private String userName;

    @NotBlank(message = "First name can not be null and must have size greater than 0")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "Last name can not be null and must have size greater than 0")
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank(message = "email can not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank
    // The regex specifies that the password can contain characters from a to z, A to Z and 0-9 only,
    // also it must be in between and 6 to 10 characters long.
    @Pattern(regexp = "^[a-zA-Z0-9]{6,10}$")
    private String password;

    @NotBlank(message = "phone can not be empty!")
    private String phone;

    private String gender;

    @NotNull(message = "Age can not be empty")
    @Min(value = 18, message = "Age must be at least 18")
    private Integer age;

    @NotEmpty(message = "address can not be empty")
    private String address;

    @NotBlank(message = "role can not be empty")
    @Pattern(regexp = "^(customer|admin)$", message = "Role must be either 'customer' or 'admin'")
    private String role;

    @JsonProperty("is_active")
    private boolean isActive;




}
