package com.sha.spring_boot_book_seller.service;

import com.sha.spring_boot_book_seller.model.PurchaseHistory;
import com.sha.spring_boot_book_seller.repository.projection.IPurchaseItem;

import java.util.List;

public interface IPurchaseHistoryService {

    PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory);

    List<IPurchaseItem> findPurchasedItemsOfUser(Long userId);
}
