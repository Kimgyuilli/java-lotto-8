package lotto.domain;

import java.util.HashSet;
import java.util.List;

import lotto.constants.LottoConstants;
import lotto.exception.ErrorCode;

public class Lotto {
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
		if (numbers.size() != LottoConstants.LOTTO_NUMBER_COUNT) {
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
		if (number < LottoConstants.MIN_NUMBER || number > LottoConstants.MAX_NUMBER) {
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
