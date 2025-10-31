package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResultCalculator {
	public LottoResult calculate(List<Lotto> lottos, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
		Map<Rank, Integer> rankCounts = initializeRankCounts();
		calculateRankCounts(lottos, winningNumbers, bonusNumber, rankCounts);
		return new LottoResult(rankCounts);
	}

	private Map<Rank, Integer> initializeRankCounts() {
		Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);
		for (Rank rank : Rank.values()) {
			rankCounts.put(rank, 0);
		}
		return rankCounts;
	}

	private void calculateRankCounts(List<Lotto> lottos, WinningNumbers winningNumbers,
		BonusNumber bonusNumber, Map<Rank, Integer> rankCounts) {
		for (Lotto lotto : lottos) {
			Rank rank = determineRank(lotto, winningNumbers, bonusNumber);
			increaseCount(rankCounts, rank);
		}
	}

	private Rank determineRank(Lotto lotto, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
		int matchCount = lotto.countMatches(winningNumbers.getNumbers());
		boolean bonusMatch = lotto.contains(bonusNumber.getNumber());
		return Rank.valueOf(matchCount, bonusMatch);
	}

	private void increaseCount(Map<Rank, Integer> rankCounts, Rank rank) {
		rankCounts.put(rank, rankCounts.get(rank) + 1);
	}
}
