package lotto.exception;

public enum ErrorCode {
    LOTTO_SIZE_INVALID("로또 번호는 6개여야 합니다."),
    LOTTO_DUPLICATE("로또 번호는 중복될 수 없습니다."),
    LOTTO_RANGE_INVALID("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    BONUS_NUMBER_INVALID_FORMAT("보너스 번호는 숫자여야 합니다."),
    BONUS_NUMBER_RANGE_INVALID("보너스 번호는 1부터 45 사이의 숫자여야 합니다."),
    BONUS_NUMBER_DUPLICATE("보너스 번호는 당첨 번호와 중복될 수 없습니다."),
    WINNING_NUMBERS_INVALID_FORMAT("당첨 번호는 숫자여야 합니다."),
    PURCHASE_AMOUNT_INVALID_FORMAT("구입 금액은 숫자여야 합니다."),
    PURCHASE_AMOUNT_TOO_LOW("구입 금액은 1,000원 이상이어야 합니다."),
    PURCHASE_AMOUNT_INVALID_UNIT("구입 금액은 1,000원 단위여야 합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
