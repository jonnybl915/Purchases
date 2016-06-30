package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by jonathandavidblack on 6/22/16.
 */
@Controller
public class PurchasesController {

    @Autowired
    PurchaseRepository purchases;

    @Autowired
    CustomerRepository customers;

    @PostConstruct
    public void init() throws FileNotFoundException {
        if (customers.count() == 0) {
            File f = new File("customers.csv");
            Scanner fileScanner = new Scanner(f);
            fileScanner.nextLine();
        while (fileScanner.hasNext()) {
            String[] columns = fileScanner.nextLine().split(",");
            Customer customer = new Customer(columns[0], columns[1]);
            customers.save(customer);
            }
        }
        if (purchases.count() == 0) {
            File f = new File("purchases.csv");
            Scanner fileScanner = new Scanner(f);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String[] columns = fileScanner.nextLine().split(",");
                Purchase purchase = new Purchase(columns[1], columns[2], columns[3], columns[4], customers.findOne(Integer.valueOf(columns[0])));
                purchases.save(purchase);
            }
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home (Model model, String category, Integer page) {

        page = (page == null) ? 0 : page; //ternary operator

        PageRequest pr = new PageRequest(page, 10);

        Page<Purchase> purchase;

        if (category != null) {
            purchase = purchases.findByCategory(pr, category);
        }
        else {
           purchase = purchases.findAll(pr);
        }

        model.addAttribute("purchases", purchase);
        model.addAttribute("category", category);

        model.addAttribute("nextPage", page + 1);
        model.addAttribute("showNext". purchase.hasNext());

        model.addAttribute("prevPage", page - 1);
        model.addAttribute("showPrev". purchase.hasPrevious());
        return "home";
    }
}

