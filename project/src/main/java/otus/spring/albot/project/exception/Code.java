package otus.spring.albot.project.exception;

public enum Code {
    NO_SUCH_ORDER("C-001"),
    NO_SUCH_PRODUCT("C-002"),
    NO_SUCH_NOTE("C-003"),
    DEPENDENT_ORDERS_EXIST("C-004");

    private String code;

    Code(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
