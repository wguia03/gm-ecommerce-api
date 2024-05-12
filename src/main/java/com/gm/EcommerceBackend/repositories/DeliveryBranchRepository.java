package com.gm.EcommerceBackend.repositories;

import com.gm.EcommerceBackend.entities.DeliveryBranch;
import com.gm.EcommerceBackend.entities.DeliveryBranchEnum;
import com.gm.EcommerceBackend.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryBranchRepository extends JpaRepository<DeliveryBranch, Integer> {

    DeliveryBranch findByName(DeliveryBranchEnum name);
}
