package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class LottoResultCalculatorTest {

	private final LottoResultCalculator calculator = new LottoResultCalculator();

	@Test
	void 일등_당첨을_계산한다() {
		List<Lotto> lottos = List.of(
			new Lotto(List.of(1, 2, 3, 4, 5, 6))
		);
		WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
		BonusNumber bonusNumber = new BonusNumber(7, winningNumbers.getNumbers());

		LottoResult result = calculator.calculate(lottos, winningNumbers, bonusNumber);

		assertThat(result.getCount(Rank.FIRST)).isEqualTo(1);
		assertThat(result.getCount(Rank.SECOND)).isEqualTo(0);
		assertThat(result.getCount(Rank.NONE)).isEqualTo(0);
	}

	@Test
	void 이등_당첨을_계산한다() {
		List<Lotto> lottos = List.of(
			new Lotto(List.of(1, 2, 3, 4, 5, 7))
		);
		WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
		BonusNumber bonusNumber = new BonusNumber(7, winningNumbers.getNumbers());

		LottoResult result = calculator.calculate(lottos, winningNumbers, bonusNumber);

		assertThat(result.getCount(Rank.SECOND)).isEqualTo(1);
		assertThat(result.getCount(Rank.FIRST)).isEqualTo(0);
		assertThat(result.getCount(Rank.THIRD)).isEqualTo(0);
	}

	@Test
	void 삼등_당첨을_계산한다() {
		List<Lotto> lottos = List.of(
			new Lotto(List.of(1, 2, 3, 4, 5, 8))
		);
		WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
		BonusNumber bonusNumber = new BonusNumber(7, winningNumbers.getNumbers());

		LottoResult result = calculator.calculate(lottos, winningNumbers, bonusNumber);

		assertThat(result.getCount(Rank.THIRD)).isEqualTo(1);
		assertThat(result.getCount(Rank.SECOND)).isEqualTo(0);
		assertThat(result.getCount(Rank.FOURTH)).isEqualTo(0);
	}

	@Test
	void 사등_당첨을_계산한다() {
		List<Lotto> lottos = List.of(
			new Lotto(List.of(1, 2, 3, 4, 8, 9))
		);
		WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
		BonusNumber bonusNumber = new BonusNumber(7, winningNumbers.getNumbers());

		LottoResult result = calculator.calculate(lottos, winningNumbers, bonusNumber);

		assertThat(result.getCount(Rank.FOURTH)).isEqualTo(1);
		assertThat(result.getCount(Rank.THIRD)).isEqualTo(0);
		assertThat(result.getCount(Rank.FIFTH)).isEqualTo(0);
	}

	@Test
	void 오등_당첨을_계산한다() {
		List<Lotto> lottos = List.of(
			new Lotto(List.of(1, 2, 3, 8, 9, 10))
		);
		WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
		BonusNumber bonusNumber = new BonusNumber(7, winningNumbers.getNumbers());

		LottoResult result = calculator.calculate(lottos, winningNumbers, bonusNumber);

		assertThat(result.getCount(Rank.FIFTH)).isEqualTo(1);
		assertThat(result.getCount(Rank.FOURTH)).isEqualTo(0);
		assertThat(result.getCount(Rank.NONE)).isEqualTo(0);
	}

	@Test
	void 미당첨을_계산한다() {
		List<Lotto> lottos = List.of(
			new Lotto(List.of(1, 2, 8, 9, 10, 11))
		);
		WinningNumbers winningNumbers = new WinningNumbers(List.of(3, 4, 5, 6, 7, 12));
		BonusNumber bonusNumber = new BonusNumber(13, winningNumbers.getNumbers());

		LottoResult result = calculator.calculate(lottos, winningNumbers, bonusNumber);

		assertThat(result.getCount(Rank.NONE)).isEqualTo(1);
		assertThat(result.getCount(Rank.FIFTH)).isEqualTo(0);
	}

	@Test
	void 여러_로또의_당첨_결과를_계산한다() {
		List<Lotto> lottos = List.of(
			new Lotto(List.of(1, 2, 3, 4, 5, 6)),  // 1등
			new Lotto(List.of(1, 2, 3, 4, 5, 7)),  // 2등
			new Lotto(List.of(1, 2, 3, 4, 5, 8)),  // 3등
			new Lotto(List.of(1, 2, 3, 4, 8, 9)),  // 4등
			new Lotto(List.of(1, 2, 3, 8, 9, 10)), // 5등
			new Lotto(List.of(8, 9, 10, 11, 12, 13)) // 미당첨
		);
		WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
		BonusNumber bonusNumber = new BonusNumber(7, winningNumbers.getNumbers());

		LottoResult result = calculator.calculate(lottos, winningNumbers, bonusNumber);

		assertThat(result.getCount(Rank.FIRST)).isEqualTo(1);
		assertThat(result.getCount(Rank.SECOND)).isEqualTo(1);
		assertThat(result.getCount(Rank.THIRD)).isEqualTo(1);
		assertThat(result.getCount(Rank.FOURTH)).isEqualTo(1);
		assertThat(result.getCount(Rank.FIFTH)).isEqualTo(1);
		assertThat(result.getCount(Rank.NONE)).isEqualTo(1);
	}
}
