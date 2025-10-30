package lotto;

import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        try {
            run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void run() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoMachine lottoMachine = new LottoMachine();

        int purchaseAmount = inputView.readPurchaseAmount();
        List<Lotto> lottos = lottoMachine.purchase(purchaseAmount);

        outputView.printPurchaseCount(lottos.size());
        outputView.printLottos(lottos);

        WinningNumbers winningNumbers = new WinningNumbers(inputView.readWinningNumbers());
        BonusNumber bonusNumber = new BonusNumber(inputView.readBonusNumber(), winningNumbers.getNumbers());

        LottoResult result = new LottoResult(lottos, winningNumbers, bonusNumber);
        outputView.printStatistics(result, purchaseAmount);
    }
}
