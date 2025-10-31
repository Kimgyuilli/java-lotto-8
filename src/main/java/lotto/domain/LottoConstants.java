package lotto.domain;

public final class LottoConstants {
	public static final int LOTTO_NUMBER_COUNT = 6;
	public static final int MIN_NUMBER = 1;
	public static final int MAX_NUMBER = 45;
	public static final int LOTTO_PRICE = 1000;

	private LottoConstants() {
		throw new AssertionError("상수 클래스는 인스턴스화할 수 없습니다.");
	}
}
