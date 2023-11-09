package lotto.controller;

import lotto.model.User;
import lotto.model.WinningLotto;
import lotto.model.WinningResult;
import lotto.view.OutputView;

public class LottoController {

    private User user;
    private WinningLotto winningLotto;

    public void run() {
        buyLotto();
        getWinningLotto();
        showWinningResult();
    }

    private void buyLotto() {
        user = InputController.makeUser();
        OutputView.printLottoInfo(user.getLottos(), user.getCount());
    }


    private void getWinningLotto() {
        winningLotto = InputController.makeWinning();
    }

    private void showWinningResult() {
        WinningResult.checkWinning(winningLotto.getBonusNumber(), winningLotto.getWinningNumber(), user.getLottos());
        OutputView.printResultStatistics(user.getMoney());
    }
}
