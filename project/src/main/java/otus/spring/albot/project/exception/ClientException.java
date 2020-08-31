package otus.spring.albot.project.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientException extends Exception {
    private final Code code;
    private final List<String> params;

    public ClientException(Code code, Object... params) {
        this.code = code;
        this.params = Arrays.stream(params).map(Object::toString).collect(Collectors.toList());
    }

    public Code getCode() {
        return code;
    }

    public List<String> getParams() {
        return params;
    }
}
