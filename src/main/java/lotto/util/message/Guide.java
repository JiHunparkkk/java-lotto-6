package lotto.util.message;

public enum Guide {
    PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
    LOTTO_COUNT("%d개를 구매했습니다.");

    private final String message;

    Guide(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(int number) {
        return String.format(message, number);
    }
}
