package ru.mcclinics.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto extends EntityDto implements Serializable {
    private String sub;
    @JsonProperty("email_verified")
    private String emailVerified;
    @JsonProperty("preferred_username")
    private String preferredUsername;
    @JsonProperty("given_name")
    private String givenName;
    @JsonProperty("family_name")
    private String familyName;
    private String email;
    private String error;
    @JsonProperty("error_description")
    private String errorDescription;
}
