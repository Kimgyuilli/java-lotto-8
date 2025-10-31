package lotto.domain;

import java.util.List;

public class WinningNumbers {
	private final List<Integer> numbers;

	public WinningNumbers(List<Integer> numbers) {
		validate(numbers);
		this.numbers = numbers;
	}

	private void validate(List<Integer> numbers) {
		new Lotto(numbers);
	}

	public List<Integer> getNumbers() {
		return numbers;
	}
}
