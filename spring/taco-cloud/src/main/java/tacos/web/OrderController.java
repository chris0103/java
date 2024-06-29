package tacos.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/orders")
@SessionAttributes("tackOrder")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping("/current")
    public String orderForm() {
        return "orderForm";
    }
}
