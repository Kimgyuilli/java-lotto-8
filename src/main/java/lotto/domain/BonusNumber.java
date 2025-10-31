package lotto.domain;

import java.util.List;

import lotto.constants.LottoConstants;
import lotto.exception.ErrorCode;

public class BonusNumber {
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
		if (number < LottoConstants.MIN_NUMBER || number > LottoConstants.MAX_NUMBER) {
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
