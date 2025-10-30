package lotto.controller;

import java.util.List;

import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.WinningNumbers;
import lotto.domain.LottoResult;
import lotto.domain.LottoResultCalculator;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoService = new LottoService();
    }

    public void run() {
        Money money = readMoney();
        List<Lotto> lottos = lottoService.purchase(money);

        printPurchasedLottos(lottos);

        WinningNumbers winningNumbers = readWinningNumbers();
        BonusNumber bonusNumber = readBonusNumber(winningNumbers);

        printResult(lottos, winningNumbers, bonusNumber, money);
    }

    private Money readMoney() {
        while (true) {
            try {
                int amount = inputView.readPurchaseAmount();
                return new Money(amount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printPurchasedLottos(List<Lotto> lottos) {
        outputView.printPurchaseCount(lottos.size());
        outputView.printLottos(lottos);
    }

    private WinningNumbers readWinningNumbers() {
        while (true) {
            try {
                return new WinningNumbers(inputView.readWinningNumbers());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private BonusNumber readBonusNumber(WinningNumbers winningNumbers) {
        while (true) {
            try {
                return new BonusNumber(inputView.readBonusNumber(), winningNumbers.getNumbers());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printResult(List<Lotto> lottos, WinningNumbers winningNumbers,
                             BonusNumber bonusNumber, Money money) {
        LottoResultCalculator calculator = new LottoResultCalculator();
        LottoResult result = calculator.calculate(lottos, winningNumbers, bonusNumber);
        outputView.printStatistics(result, money.getAmount());
    }
}
