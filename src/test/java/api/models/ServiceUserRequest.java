package api.models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceUserRequest {
    private String email;
    private String password;
}
