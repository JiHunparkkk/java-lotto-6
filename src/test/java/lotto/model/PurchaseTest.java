package lotto.model;

import static lotto.util.message.Error.MUST_MONEY_UNIT;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PurchaseTest {

    @ParameterizedTest
    @CsvSource({"0", "700", "7777", "12500"})
    @DisplayName("구매 금액은 1,000원 단위만 가능하다.")
    public void inputMoneyValidate(int money) {
        Assertions.assertThatThrownBy(() -> new Purchase(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MUST_MONEY_UNIT);
    }

    @ParameterizedTest
    @CsvSource({"1000,1", "7000,7", "10000,10", "777000,777"})
    @DisplayName("금액에 맞게 로또의 갯수를 저장한다.")
    public void purchaseLottoCount(int money, int expect) {
        Purchase purchase = new Purchase(money);

        int result = purchase.getCount();

        Assertions.assertThat(result).isEqualTo(expect);
    }
}