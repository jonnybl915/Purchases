package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jonathandavidblack on 6/22/16.
 */
@Controller
public class PurchasesController {


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home() {

        return "home";
    }
}
