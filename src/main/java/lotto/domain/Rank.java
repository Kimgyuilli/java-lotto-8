package lotto.domain;

import java.util.Arrays;

public enum Rank {
	FIRST(6, false, 2_000_000_000, "6개 일치"),
	SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치"),
	THIRD(5, false, 1_500_000, "5개 일치"),
	FOURTH(4, false, 50_000, "4개 일치"),
	FIFTH(3, false, 5_000, "3개 일치"),
	NO_WIN(0, false, 0, "미당첨");

	private final int matchCount;
	private final boolean bonusMatch;
	private final int prize;
	private final String description;

	Rank(int matchCount, boolean bonusMatch, int prize, String description) {
		this.matchCount = matchCount;
		this.bonusMatch = bonusMatch;
		this.prize = prize;
		this.description = description;
	}

	public static Rank determineBy(int matchCount, boolean bonusMatch) {
		return Arrays.stream(values())
			.filter(rank -> rank.matches(matchCount, bonusMatch))
			.findFirst()
			.orElse(NO_WIN);
	}

	private boolean matches(int matchCount, boolean bonusMatch) {
		return this.matchCount == matchCount && this.bonusMatch == bonusMatch;
	}

	public int getPrize() {
		return prize;
	}

	public String getDescription() {
		return description;
	}
}
