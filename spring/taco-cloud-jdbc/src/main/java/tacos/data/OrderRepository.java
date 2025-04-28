package tacos.data;

import tacos.model.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder order);
}
