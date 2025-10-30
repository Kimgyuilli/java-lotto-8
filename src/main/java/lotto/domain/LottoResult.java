package lotto.domain;

import java.util.Map;

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
        return (double) totalPrize / purchaseAmount * 100;
    }

    private long calculateTotalPrize() {
        long total = 0;
        for (Rank rank : Rank.values()) {
            total += (long) rank.getPrize() * rankCounts.get(rank);
        }
        return total;
    }
}
