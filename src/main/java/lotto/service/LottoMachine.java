package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.exception.ErrorCode;
import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public List<Lotto> purchase(int amount) {
        validateAmount(amount);
        int count = calculateLottoCount(amount);
        return generateLottos(count);
    }

    private void validateAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(ErrorCode.PURCHASE_AMOUNT_TOO_LOW.getMessage());
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorCode.PURCHASE_AMOUNT_NOT_UNIT.getMessage());
        }
    }

    private int calculateLottoCount(int amount) {
        return amount / LOTTO_PRICE;
    }

    private List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generateLotto());
        }
        return lottos;
    }

    private Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, LOTTO_NUMBER_COUNT);
        return new Lotto(numbers);
    }
}
