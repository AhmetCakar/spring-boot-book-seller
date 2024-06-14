package com.sha.spring_boot_book_seller.repository.projection;

import java.time.LocalDateTime;

public interface IPurchaseItem {

    String getTitle();
    Double getPrice();
    LocalDateTime getPurchaseTime();
}
