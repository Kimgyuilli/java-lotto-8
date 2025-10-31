package lotto.domain;

import java.util.HashSet;
import java.util.List;

import lotto.exception.ErrorCode;

public class Lotto {
	private static final int LOTTO_NUMBER_COUNT = 6;
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;

	private final List<Integer> numbers;

	public Lotto(List<Integer> numbers) {
		validateLotto(numbers);
		this.numbers = numbers;
	}

	private void validateLotto(List<Integer> numbers) {
		validateSize(numbers);
		validateDuplicate(numbers);
		validateRange(numbers);
	}

	private void validateSize(List<Integer> numbers) {
		if (numbers.size() != LOTTO_NUMBER_COUNT) {
			throw new IllegalArgumentException(ErrorCode.LOTTO_SIZE_INVALID.getMessage());
		}
	}

	private void validateDuplicate(List<Integer> numbers) {
		if (numbers.size() != new HashSet<>(numbers).size()) {
			throw new IllegalArgumentException(ErrorCode.LOTTO_DUPLICATE.getMessage());
		}
	}

	private void validateRange(List<Integer> numbers) {
		for (Integer number : numbers) {
			validateNumber(number);
		}
	}

	private void validateNumber(Integer number) {
		if (number < MIN_NUMBER || number > MAX_NUMBER) {
			throw new IllegalArgumentException(ErrorCode.LOTTO_RANGE_INVALID.getMessage());
		}
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public int countMatches(List<Integer> winningNumbers) {
		return (int)numbers.stream()
			.filter(winningNumbers::contains)
			.count();
	}

	public boolean contains(int number) {
		return numbers.contains(number);
	}
}
