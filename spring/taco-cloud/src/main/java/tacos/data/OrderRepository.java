package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.model.TacoOrder;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

}
