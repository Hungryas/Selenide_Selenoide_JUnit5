package model;

import helper.GenerexVault;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder = true, setterPrefix = "with")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Client {
    static final GenerexVault GENEREX_VAULT = GenerexVault.getInstance();

    String FIO = GENEREX_VAULT.getCorrectFIO();
    String phone = GENEREX_VAULT.getCorrectPhone();
    String email = GENEREX_VAULT.getCorrectEmail();
}

