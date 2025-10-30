package lotto;

import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoMachine lottoMachine = new LottoMachine();

        List<Lotto> lottos = readPurchaseAmountAndBuy(inputView, lottoMachine);
        int purchaseAmount = calculatePurchaseAmount(lottos);

        outputView.printPurchaseCount(lottos.size());
        outputView.printLottos(lottos);

        WinningNumbers winningNumbers = readWinningNumbersWithRetry(inputView);
        BonusNumber bonusNumber = readBonusNumberWithRetry(inputView, winningNumbers);

        calculateAndPrintResult(lottos, winningNumbers, bonusNumber, purchaseAmount, outputView);
    }

    private static List<Lotto> readPurchaseAmountAndBuy(InputView inputView, LottoMachine lottoMachine) {
        while (true) {
            try {
                int amount = inputView.readPurchaseAmount();
                return lottoMachine.purchase(amount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int calculatePurchaseAmount(List<Lotto> lottos) {
        return lottos.size() * 1000;
    }

    private static WinningNumbers readWinningNumbersWithRetry(InputView inputView) {
        while (true) {
            try {
                return new WinningNumbers(inputView.readWinningNumbers());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static BonusNumber readBonusNumberWithRetry(InputView inputView, WinningNumbers winningNumbers) {
        while (true) {
            try {
                return new BonusNumber(inputView.readBonusNumber(), winningNumbers.getNumbers());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void calculateAndPrintResult(List<Lotto> lottos, WinningNumbers winningNumbers,
                                                 BonusNumber bonusNumber, int purchaseAmount, OutputView outputView) {
        LottoResult result = new LottoResult(lottos, winningNumbers, bonusNumber);
        outputView.printStatistics(result, purchaseAmount);
    }
}
