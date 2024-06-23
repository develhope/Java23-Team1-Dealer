package com.develhope.spring;

import com.develhope.spring.purchase_order.dto.PurchaseOrderCreationDTO;
import com.develhope.spring.purchase_order.dto.PurchaseOrderMapper;
import com.develhope.spring.purchase_order.entity.PurchaseOrder;
import com.develhope.spring.purchase_order.entity.PurchaseOrderStatus;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.user.repository.UserRepository;
import com.develhope.spring.vehicles.entity.*;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SpringBootTest
public class PurchaseOrderMapperTest {
    @Mock
    UserRepository userRepository;

    @Mock
    VehicleRepository vehicleRepository;

    @InjectMocks
    PurchaseOrderMapper purchaseMapper;

    private static final Vehicle VEHICLE = new Vehicle(
            0, VehicleKind.CAR, "Tesla", "m1,", 50, "Blue", 40, Gearbox.AUTOMATIC, 2011, FuelType.BATTERY, 10000, false, 0, "", true, VehicleState.PURCHASABLE
    );

    private static final User USER = new User(
            1L, "Mara", "rossi", "3456453",
            "mara@gh.it", "mara", "gianni", UserKind.ADMIN
    );

    private static final PurchaseOrder PURCHASE_ORDER = new PurchaseOrder(
            0, USER, 100, true, new Date(System.currentTimeMillis()) , PurchaseOrderStatus.ORDERED, VEHICLE, USER
    );

    private static final PurchaseOrderCreationDTO ORDER_DTO = new PurchaseOrderCreationDTO(
            1, 100, true, PurchaseOrderStatus.ORDERED, 1,1
    );

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void toDto() {
        when(vehicleRepository.findById(USER.getId()))
                .thenReturn(Optional.ofNullable(VEHICLE));
        when(userRepository.findById(any()))
                .thenReturn(Optional.ofNullable(USER));

        PurchaseOrder returnedPurchaseOrder = purchaseMapper.toPurchaseOrder(ORDER_DTO);
        PURCHASE_ORDER.setCreatedAt(returnedPurchaseOrder.getCreatedAt());
        assertEquals(PURCHASE_ORDER, returnedPurchaseOrder);
    }

}
