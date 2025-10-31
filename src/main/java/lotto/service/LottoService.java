package lotto.service;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.domain.LottoConstants;
import lotto.domain.Money;

public class LottoService {

	public List<Lotto> purchase(Money money) {
		int count = money.getLottoCount();
		return generateLottos(count);
	}

	private List<Lotto> generateLottos(int count) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			lottos.add(generateLotto());
		}
		return lottos;
	}

	private Lotto generateLotto() {
		List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
			LottoConstants.MIN_NUMBER,
			LottoConstants.MAX_NUMBER,
			LottoConstants.LOTTO_NUMBER_COUNT
		);
		return new Lotto(numbers);
	}
}
