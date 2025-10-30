package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.");
        }
    }

    private void validate() {
        new Lotto(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
