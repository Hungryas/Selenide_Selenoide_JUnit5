package api.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceUserRequest {
    private String email;
    private String password;

    public ServiceUserRequest(String user_email) {
        this.email = user_email;
    }
}
