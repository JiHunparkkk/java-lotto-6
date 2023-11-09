package lotto.controller;

import static lotto.util.Conversion.stringToInt;
import static lotto.util.Conversion.stringToList;
import static lotto.util.message.Guide.BONUS_NUMBER;
import static lotto.util.message.Guide.PURCHASE_AMOUNT;
import static lotto.util.message.Guide.WINNING_NUMBER;
import static lotto.view.InputView.input;

import lotto.model.BonusNumber;
import lotto.model.Lottos;
import lotto.model.Purchase;
import lotto.model.User;
import lotto.model.WinningLotto;
import lotto.model.WinningNumber;
import lotto.view.OutputView;

public class InputController {

    public static User makeUser() {
        Purchase purchase = payMoney();
        Lottos lottos = buyLottos(purchase);
        
        return new User(purchase, lottos);
    }

    public static WinningLotto makeWinning() {
        WinningNumber winningNumber = getWinningLotto();
        BonusNumber bonusNumber = getBonusLotto(winningNumber);

        return new WinningLotto(winningNumber, bonusNumber);
    }

    private static Purchase payMoney() {
        try {
            int money = stringToInt(input(PURCHASE_AMOUNT.getMessage()));
            return new Purchase(money);
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return payMoney();
        }
    }

    private static Lottos buyLottos(Purchase purchase) {
        int count = purchase.getCount();

        return new Lottos(Lottos.generator(count));
    }

    private static WinningNumber getWinningLotto() {
        String winningNumberInput = input(WINNING_NUMBER.getMessage());

        try {
            return new WinningNumber(stringToList(winningNumberInput));
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return getWinningLotto();
        }
    }

    private static BonusNumber getBonusLotto(WinningNumber winningNumber) {
        String bonus = input(BONUS_NUMBER.getMessage());

        try {
            return new BonusNumber(stringToInt(bonus), winningNumber.getWinning());
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return getBonusLotto(winningNumber);
        }
    }

}
