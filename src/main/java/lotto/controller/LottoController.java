package lotto.controller;

import static lotto.util.Conversion.stringToInt;
import static lotto.util.Conversion.stringToList;
import static lotto.util.message.Guide.BONUS_NUMBER;
import static lotto.util.message.Guide.PURCHASE_AMOUNT;
import static lotto.util.message.Guide.WINNING_NUMBER;
import static lotto.view.InputView.input;

import java.util.List;
import lotto.model.BonusNumber;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Purchase;
import lotto.model.WinningLotto;
import lotto.view.OutputView;

public class LottoController {

    private Purchase purchase;
    private Lottos lottos;
    private WinningLotto winningLotto;
    private BonusNumber bonusNumber;

    public void run() {
        payMoney();
        buyLottos();
        winningLotto();
        bonusLotto();
    }

    private void payMoney() {
        try {
            int money = stringToInt(input(PURCHASE_AMOUNT.getMessage()));
            purchase = new Purchase(money);
        } catch (IllegalArgumentException exception) {
            OutputView.error(exception.getMessage());
            payMoney();
        }
    }

    private void buyLottos() {
        int count = purchase.getCount();

        List<Lotto> lotto = Lotto.generator(count);
        lottos = new Lottos(lotto);

        OutputView.lottoInfo(lotto, count);
    }

    private void winningLotto() {
        String winningNumber = input(WINNING_NUMBER.getMessage());

        try {

            winningLotto = new WinningLotto(stringToList(winningNumber));

        } catch (IllegalArgumentException exception) {
            OutputView.error(exception.getMessage());
            winningLotto();
        }
    }

    private void bonusLotto() {
        String bonus = input(BONUS_NUMBER.getMessage());

        try {
            bonusNumber = new BonusNumber(stringToInt(bonus), winningLotto.getWinning());
        } catch (IllegalArgumentException exception) {
            OutputView.error(exception.getMessage());
            bonusLotto();
        }
    }
}
