package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.ErrorCode;

public class InputView {
	private static final String PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
	private static final String WINNING_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
	private static final String BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";

	public int readPurchaseAmount() {
		System.out.println(PURCHASE_AMOUNT_MESSAGE);
		String input = Console.readLine();
		return parseInteger(input, ErrorCode.PURCHASE_AMOUNT_INVALID_FORMAT);
	}

	public List<Integer> readWinningNumbers() {
		System.out.println();
		System.out.println(WINNING_NUMBERS_MESSAGE);
		String input = Console.readLine();
		return parseWinningNumbers(input);
	}

	public int readBonusNumber() {
		System.out.println();
		System.out.println(BONUS_NUMBER_MESSAGE);
		String input = Console.readLine();
		return parseInteger(input, ErrorCode.BONUS_NUMBER_INVALID_FORMAT);
	}

	private int parseInteger(String input, ErrorCode errorCode) {
		try {
			return Integer.parseInt(input.trim());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(errorCode.getMessage());
		}
	}

	private List<Integer> parseWinningNumbers(String input) {
		try {
			return Arrays.stream(input.split(","))
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ErrorCode.WINNING_NUMBERS_INVALID_FORMAT.getMessage());
		}
	}
}
