package lotto.domain;

import lotto.exception.ErrorCode;

public class Money {
	private static final int LOTTO_PRICE = 1000;

	private final int amount;

	public Money(int amount) {
		validateMoney(amount);
		this.amount = amount;
	}

	private void validateMoney(int amount) {
		validateMinimumAmount(amount);
		validateUnit(amount);
	}

	private void validateMinimumAmount(int amount) {
		if (amount < LOTTO_PRICE) {
			throw new IllegalArgumentException(ErrorCode.PURCHASE_AMOUNT_TOO_LOW.getMessage());
		}
	}

	private void validateUnit(int amount) {
		if (amount % LOTTO_PRICE != 0) {
			throw new IllegalArgumentException(ErrorCode.PURCHASE_AMOUNT_INVALID_UNIT.getMessage());
		}
	}

	public int getLottoCount() {
		return amount / LOTTO_PRICE;
	}

	public int getAmount() {
		return amount;
	}
}
