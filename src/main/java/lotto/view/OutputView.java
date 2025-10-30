package lotto.view;

import lotto.Lotto;
import lotto.LottoResult;
import lotto.Rank;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("#,###");

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

    public void printStatistics(LottoResult result, int purchaseAmount) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        printRankStatistics(result);
        printProfitRate(result, purchaseAmount);
    }

    private void printRankStatistics(LottoResult result) {
        printRank(Rank.FIFTH, result);
        printRank(Rank.FOURTH, result);
        printRank(Rank.THIRD, result);
        printRank(Rank.SECOND, result);
        printRank(Rank.FIRST, result);
    }

    private void printRank(Rank rank, LottoResult result) {
        System.out.println(rank.getDescription() + " (" + MONEY_FORMAT.format(rank.getPrize()) + "원) - "
                + result.getCount(rank) + "개");
    }

    private void printProfitRate(LottoResult result, int purchaseAmount) {
        double profitRate = result.calculateProfitRate(purchaseAmount);
        System.out.println("총 수익률은 " + String.format("%.1f", profitRate) + "%입니다.");
    }
}
