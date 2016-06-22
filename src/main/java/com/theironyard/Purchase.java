package com.theironyard;

import javax.persistence.*;

/**
 * Created by jonathandavidblack on 6/22/16.
 */
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue
    int id;

    String date;
    String credit_card;
    String cvv;
    String category;

    @ManyToOne
    Customer customer;

    public Purchase(String date, String credit_card, String cvv, String category, Customer customer) {
        this.date = date;
        this.credit_card = credit_card;
        this.cvv = cvv;
        this.category = category;
        this.customer = customer;
    }

    public Purchase() {
    }
}
