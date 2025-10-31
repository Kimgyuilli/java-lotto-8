package lotto.domain;

import java.util.Map;
import java.util.stream.Stream;

public class LottoResult {
	private final Map<Rank, Integer> rankCounts;

	public LottoResult(Map<Rank, Integer> rankCounts) {
		this.rankCounts = rankCounts;
	}

	public int getCount(Rank rank) {
		return rankCounts.get(rank);
	}

	public double calculateProfitRate(int purchaseAmount) {
		long totalPrize = calculateTotalPrize();
		return (double)totalPrize / purchaseAmount * 100;
	}

	private long calculateTotalPrize() {
		return Stream.of(Rank.values())
			.mapToLong(rank -> (long)rank.getPrize() * rankCounts.get(rank))
			.sum();
	}
}
