package lotto.controller;

import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;
import lotto.service.LottoMachine;
import lotto.service.LottoResultCalculator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoMachine = new LottoMachine();
    }

    public void run() {
        List<Lotto> lottos = purchaseLottos();
        int purchaseAmount = calculatePurchaseAmount(lottos);

        printPurchasedLottos(lottos);

        WinningNumbers winningNumbers = readWinningNumbers();
        BonusNumber bonusNumber = readBonusNumber(winningNumbers);

        printResult(lottos, winningNumbers, bonusNumber, purchaseAmount);
    }

    private List<Lotto> purchaseLottos() {
        while (true) {
            try {
                int amount = inputView.readPurchaseAmount();
                return lottoMachine.purchase(amount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int calculatePurchaseAmount(List<Lotto> lottos) {
        return lottos.size() * 1000;
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
                             BonusNumber bonusNumber, int purchaseAmount) {
        LottoResultCalculator result = new LottoResultCalculator(lottos, winningNumbers, bonusNumber);
        outputView.printStatistics(result, purchaseAmount);
    }
}
