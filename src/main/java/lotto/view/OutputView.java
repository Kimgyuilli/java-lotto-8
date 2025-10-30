package lotto.view;

import lotto.Lotto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public void printPurchaseCount(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            printLotto(lotto);
        }
    }

    private void printLotto(Lotto lotto) {
        List<Integer> sortedNumbers = lotto.getNumbers().stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sortedNumbers);
    }
}
