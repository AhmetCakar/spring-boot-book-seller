package com.sha.spring_boot_book_seller.service.serviceImpl;

import com.sha.spring_boot_book_seller.model.PurchaseHistory;
import com.sha.spring_boot_book_seller.repository.IPurchaseHistoryRepository;
import com.sha.spring_boot_book_seller.repository.projection.IPurchaseItem;
import com.sha.spring_boot_book_seller.service.IPurchaseHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseHistoryServiceImpl implements IPurchaseHistoryService {

    private final IPurchaseHistoryRepository purchaseHistoryRepository;

    public PurchaseHistoryServiceImpl(IPurchaseHistoryRepository purchaseHistoryRepository) {
        this.purchaseHistoryRepository = purchaseHistoryRepository;
    }

    @Override
    public PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory) {
        purchaseHistory.setPurchaseTime(LocalDateTime.now());
        return purchaseHistoryRepository.save(purchaseHistory);
    }

    @Override
    public List<IPurchaseItem> findPurchasedItemsOfUser(Long userId) {
        return purchaseHistoryRepository.findAllPurchasesOfUser(userId);
    }
}
