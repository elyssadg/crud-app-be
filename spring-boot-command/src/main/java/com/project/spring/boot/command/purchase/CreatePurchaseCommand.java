package com.project.spring.boot.command.purchase;

import com.project.spring.boot.command.Command;
import com.project.spring.boot.command.model.request.purchase.CreatePurchaseCommandRequest;
import com.project.spring.boot.web.model.response.purchase.PurchaseWebResponse;

public interface CreatePurchaseCommand extends Command<CreatePurchaseCommandRequest, PurchaseWebResponse> {
}
