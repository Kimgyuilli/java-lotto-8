package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {

	private static final List<Integer> WINNING_NUMBERS = List.of(1, 2, 3, 4, 5, 6);

	@ParameterizedTest
	@ValueSource(ints = {7, 10, 20, 30, 45})
	void 정상적인_보너스_번호로_BonusNumber_객체를_생성한다(int number) {
		BonusNumber bonusNumber = new BonusNumber(number, WINNING_NUMBERS);

		assertThat(bonusNumber.getNumber()).isEqualTo(number);
	}

	@ParameterizedTest
	@ValueSource(ints = {0, -1, -10})
	void 일보다_작은_보너스_번호로_BonusNumber_객체_생성_시_예외가_발생한다(int number) {
		assertThatThrownBy(() -> new BonusNumber(number, WINNING_NUMBERS))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[ERROR]");
	}

	@ParameterizedTest
	@ValueSource(ints = {46, 50, 100})
	void 사십오보다_큰_보너스_번호로_BonusNumber_객체_생성_시_예외가_발생한다(int number) {
		assertThatThrownBy(() -> new BonusNumber(number, WINNING_NUMBERS))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[ERROR]");
	}

	@Test
	void 당첨_번호와_중복된_보너스_번호로_BonusNumber_객체_생성_시_예외가_발생한다() {
		assertThatThrownBy(() -> new BonusNumber(1, WINNING_NUMBERS))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[ERROR]");

		assertThatThrownBy(() -> new BonusNumber(6, WINNING_NUMBERS))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[ERROR]");
	}
}
