package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

	@ParameterizedTest
	@ValueSource(ints = {1000, 2000, 5000, 10000, 100000})
	void 정상적인_금액으로_Money_객체를_생성한다(int amount) {
		Money money = new Money(amount);

		assertThat(money.getAmount()).isEqualTo(amount);
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 500, 999})
	void 천원_미만의_금액으로_Money_객체_생성_시_예외가_발생한다(int amount) {
		assertThatThrownBy(() -> new Money(amount))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[ERROR]");
	}

	@ParameterizedTest
	@ValueSource(ints = {1100, 2500, 3333, 10001})
	void 천원_단위가_아닌_금액으로_Money_객체_생성_시_예외가_발생한다(int amount) {
		assertThatThrownBy(() -> new Money(amount))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[ERROR]");
	}

	@Test
	void 구매_가능한_로또_개수를_정확히_계산한다() {
		Money money1 = new Money(8000);
		Money money2 = new Money(14000);
		Money money3 = new Money(1000);

		assertThat(money1.getLottoCount()).isEqualTo(8);
		assertThat(money2.getLottoCount()).isEqualTo(14);
		assertThat(money3.getLottoCount()).isEqualTo(1);
	}
}
