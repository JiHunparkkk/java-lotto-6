package lotto.model;

import java.util.List;

public class User {

    private final Purchase purchase;
    private final Lottos lottos;

    public User(Purchase purchase, Lottos lottos) {
        this.purchase = purchase;
        this.lottos = lottos;
    }

    public int getCount() {
        return purchase.getCount();
    }

    public int getMoney() {
        return purchase.getMoney();
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }
}
