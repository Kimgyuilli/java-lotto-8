package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class WinningNumbersTest {

	@Test
	void 정상적인_당첨_번호로_객체를_생성한다() {
		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

		WinningNumbers winningNumbers = new WinningNumbers(numbers);

		assertThat(winningNumbers.getNumbers()).isEqualTo(numbers);
	}

	@Test
	void 당첨_번호가_6개가_아니면_예외가_발생한다() {
		assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[ERROR]");

		assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6, 7)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[ERROR]");
	}

	@Test
	void 당첨_번호에_중복이_있으면_예외가_발생한다() {
		assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 5)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[ERROR]");
	}

	@Test
	void 당첨_번호가_범위를_벗어나면_예외가_발생한다() {
		assertThatThrownBy(() -> new WinningNumbers(List.of(0, 1, 2, 3, 4, 5)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[ERROR]");

		assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 46)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[ERROR]");
	}

	@Test
	void getNumbers는_불변_리스트를_반환한다() {
		WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
		List<Integer> numbers = winningNumbers.getNumbers();

		assertThatThrownBy(() -> numbers.add(7))
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void null_입력_시_예외가_발생한다() {
		assertThatThrownBy(() -> new WinningNumbers(null))
			.isInstanceOf(NullPointerException.class);
	}
}
