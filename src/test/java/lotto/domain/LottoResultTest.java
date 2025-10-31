package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.EnumMap;
import java.util.Map;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @Test
    void 등수별_당첨_개수를_반환한다() {
        Map<Rank, Integer> rankCounts = createRankCounts(1, 1, 1, 1, 1, 1);

        LottoResult result = new LottoResult(rankCounts);

        assertThat(result.getCount(Rank.FIRST)).isEqualTo(1);
        assertThat(result.getCount(Rank.SECOND)).isEqualTo(1);
        assertThat(result.getCount(Rank.THIRD)).isEqualTo(1);
        assertThat(result.getCount(Rank.FOURTH)).isEqualTo(1);
        assertThat(result.getCount(Rank.FIFTH)).isEqualTo(1);
        assertThat(result.getCount(Rank.NONE)).isEqualTo(1);
    }

    @Test
    void 수익률을_계산한다_오등_1개() {
        Map<Rank, Integer> rankCounts = createRankCounts(0, 0, 0, 0, 1, 0);
        LottoResult result = new LottoResult(rankCounts);

        double profitRate = result.calculateProfitRate(8000);

        assertThat(profitRate).isEqualTo(62.5); // 5000 / 8000 * 100
    }

    @Test
    void 수익률을_계산한다_사등_1개() {
        Map<Rank, Integer> rankCounts = createRankCounts(0, 0, 0, 1, 0, 0);
        LottoResult result = new LottoResult(rankCounts);

        double profitRate = result.calculateProfitRate(8000);

        assertThat(profitRate).isEqualTo(625.0); // 50000 / 8000 * 100
    }

    @Test
    void 수익률을_계산한다_삼등_1개() {
        Map<Rank, Integer> rankCounts = createRankCounts(0, 0, 1, 0, 0, 0);
        LottoResult result = new LottoResult(rankCounts);

        double profitRate = result.calculateProfitRate(14000);

        assertThat(profitRate).isCloseTo(10714.29, Offset.offset(0.01)); // 1500000 / 14000 * 100
    }

    @Test
    void 수익률을_계산한다_미당첨() {
        Map<Rank, Integer> rankCounts = createRankCounts(0, 0, 0, 0, 0, 8);
        LottoResult result = new LottoResult(rankCounts);

        double profitRate = result.calculateProfitRate(8000);

        assertThat(profitRate).isEqualTo(0.0);
    }

    @Test
    void 수익률을_계산한다_복합_당첨() {
        // 5등 2개 (10,000원) + 4등 1개 (50,000원) = 총 60,000원
        Map<Rank, Integer> rankCounts = createRankCounts(0, 0, 0, 1, 2, 5);
        LottoResult result = new LottoResult(rankCounts);

        double profitRate = result.calculateProfitRate(8000);

        assertThat(profitRate).isEqualTo(750.0); // 60000 / 8000 * 100
    }

    private Map<Rank, Integer> createRankCounts(int first, int second, int third,
                                                 int fourth, int fifth, int none) {
        Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);
        rankCounts.put(Rank.FIRST, first);
        rankCounts.put(Rank.SECOND, second);
        rankCounts.put(Rank.THIRD, third);
        rankCounts.put(Rank.FOURTH, fourth);
        rankCounts.put(Rank.FIFTH, fifth);
        rankCounts.put(Rank.NONE, none);
        return rankCounts;
    }
}
