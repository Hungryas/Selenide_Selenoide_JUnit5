package helper;

import com.mifmif.common.regex.Generex;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GenerexVault {
    @NonFinal
    static GenerexVault instance;
    static Generex CORRECT_FIO = new Generex("[А-Я][а-пр-я]{3,10}\s[А-Я][а-пр-я]{3,10}");
    static Generex CORRECT_PHONE = new Generex("8[0-9]{9}");
    static Generex CORRECT_EMAIL = new Generex("\\w{6,12}\\@[a-z0-9]{6,12}\\.[a-z]{2,6}");

    public static synchronized GenerexVault getInstance() {
        if (instance == null) {
            instance = new GenerexVault();
        }
        return instance;
    }

    public String getCorrectFIO() {
        return CORRECT_FIO.random();
    }

    public String getCorrectPhone() {
        return CORRECT_PHONE.random();
    }

    public String getCorrectEmail() {
        return CORRECT_EMAIL.random();
    }
}
