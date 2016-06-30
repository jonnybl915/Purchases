package com.theironyard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jonathandavidblack on 6/22/16.
 */
public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Integer> { //CrudRepository<Purchase, Integer> --removed this
    Page<Purchase> findByCategory(Pageable pageable, String Category); //changed from Iterable to Page, and inserted Pageable pageable


}
