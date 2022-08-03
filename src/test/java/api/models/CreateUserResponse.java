package api.models;

import api.utils.DateTimeDeserializer;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonFormat;
import io.qameta.allure.internal.shadowed.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateUserResponse extends CreateUserRequest {
    private int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ")
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private LocalDateTime createdAt;
}