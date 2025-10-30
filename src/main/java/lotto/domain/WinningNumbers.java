package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.exception.ErrorCode;

public class WinningNumbers {
    private final List<Integer> numbers;

    public WinningNumbers(String input) {
        this.numbers = parseNumbers(input);
        validate();
    }

    private List<Integer> parseNumbers(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.WINNING_NUMBERS_NOT_A_NUMBER.getMessage());
        }
    }

    private void validate() {
        new Lotto(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
