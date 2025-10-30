package lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> result;

    public LottoResult(List<Lotto> lottos, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        this.result = new EnumMap<>(Rank.class);
        initializeResult();
        calculateResult(lottos, winningNumbers, bonusNumber);
    }

    private void initializeResult() {
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
    }

    private void calculateResult(List<Lotto> lottos, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        for (Lotto lotto : lottos) {
            Rank rank = determineRank(lotto, winningNumbers, bonusNumber);
            increaseCount(rank);
        }
    }

    private Rank determineRank(Lotto lotto, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        int matchCount = lotto.countMatches(winningNumbers.getNumbers());
        boolean bonusMatch = lotto.contains(bonusNumber.getNumber());
        return Rank.valueOf(matchCount, bonusMatch);
    }

    private void increaseCount(Rank rank) {
        result.put(rank, result.get(rank) + 1);
    }

    public int getCount(Rank rank) {
        return result.get(rank);
    }

    public double calculateProfitRate(int purchaseAmount) {
        long totalPrize = calculateTotalPrize();
        return (double) totalPrize / purchaseAmount * 100;
    }

    private long calculateTotalPrize() {
        long total = 0;
        for (Rank rank : Rank.values()) {
            total += (long) rank.getPrize() * result.get(rank);
        }
        return total;
    }
}
