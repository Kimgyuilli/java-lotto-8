package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RankTest {

	@Test
	void 여섯개_일치_시_1등을_반환한다() {
		Rank rank = Rank.determineBy(6, false);

		assertThat(rank).isEqualTo(Rank.FIRST);
		assertThat(rank.getPrize()).isEqualTo(2_000_000_000);
		assertThat(rank.getDescription()).isEqualTo("6개 일치");
	}

	@Test
	void 다섯개_일치하고_보너스_볼_일치_시_2등을_반환한다() {
		Rank rank = Rank.determineBy(5, true);

		assertThat(rank).isEqualTo(Rank.SECOND);
		assertThat(rank.getPrize()).isEqualTo(30_000_000);
		assertThat(rank.getDescription()).isEqualTo("5개 일치, 보너스 볼 일치");
	}

	@Test
	void 다섯개_일치하고_보너스_볼_불일치_시_3등을_반환한다() {
		Rank rank = Rank.determineBy(5, false);

		assertThat(rank).isEqualTo(Rank.THIRD);
		assertThat(rank.getPrize()).isEqualTo(1_500_000);
		assertThat(rank.getDescription()).isEqualTo("5개 일치");
	}

	@Test
	void 네개_일치_시_4등을_반환한다() {
		Rank rank = Rank.determineBy(4, false);

		assertThat(rank).isEqualTo(Rank.FOURTH);
		assertThat(rank.getPrize()).isEqualTo(50_000);
		assertThat(rank.getDescription()).isEqualTo("4개 일치");
	}

	@Test
	void 세개_일치_시_5등을_반환한다() {
		Rank rank = Rank.determineBy(3, false);

		assertThat(rank).isEqualTo(Rank.FIFTH);
		assertThat(rank.getPrize()).isEqualTo(5_000);
		assertThat(rank.getDescription()).isEqualTo("3개 일치");
	}

	@Test
	void 세개_미만_일치_시_NONE을_반환한다() {
		assertThat(Rank.determineBy(0, false)).isEqualTo(Rank.NONE);
		assertThat(Rank.determineBy(1, false)).isEqualTo(Rank.NONE);
		assertThat(Rank.determineBy(2, false)).isEqualTo(Rank.NONE);
	}

	@Test
	void NONE_등수의_상금은_0원이다() {
		Rank rank = Rank.determineBy(2, false);

		assertThat(rank.getPrize()).isEqualTo(0);
	}
}
