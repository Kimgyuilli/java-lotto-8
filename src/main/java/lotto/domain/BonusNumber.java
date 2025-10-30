package lotto.domain;

import java.util.List;

import lotto.exception.ErrorCode;

public class BonusNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final int number;

    public BonusNumber(int number, List<Integer> winningNumbers) {
        this.number = number;
        validate(winningNumbers);
    }

    private void validate(List<Integer> winningNumbers) {
        validateRange();
        validateDuplicate(winningNumbers);
    }

    private void validateRange() {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(ErrorCode.BONUS_NUMBER_RANGE_INVALID.getMessage());
        }
    }

    private void validateDuplicate(List<Integer> winningNumbers) {
        if (winningNumbers.contains(number)) {
            throw new IllegalArgumentException(ErrorCode.BONUS_NUMBER_DUPLICATE.getMessage());
        }
    }

    public int getNumber() {
        return number;
    }
}
