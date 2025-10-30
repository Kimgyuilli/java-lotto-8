package lotto.domain;

import java.util.List;

public class BonusNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final int number;

    public BonusNumber(String input, List<Integer> winningNumbers) {
        this.number = parseNumber(input);
        validate(winningNumbers);
    }

    private int parseNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }
    }

    private void validate(List<Integer> winningNumbers) {
        validateRange();
        validateDuplicate(winningNumbers);
    }

    private void validateRange() {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> winningNumbers) {
        if (winningNumbers.contains(number)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public int getNumber() {
        return number;
    }
}
