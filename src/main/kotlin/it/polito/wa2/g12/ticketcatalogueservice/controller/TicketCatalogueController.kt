package it.polito.wa2.g12.ticketcatalogueservice.controller

import it.polito.wa2.g12.ticketcatalogueservice.dto.OrderDTO
import it.polito.wa2.g12.ticketcatalogueservice.dto.TicketDTO
import it.polito.wa2.g12.ticketcatalogueservice.service.impl.PaymentInfo
import it.polito.wa2.g12.ticketcatalogueservice.service.impl.TicketCatalogueServiceImpl
import kotlinx.coroutines.flow.Flow
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
class TicketCatalogueController(val ticketCatalogueService: TicketCatalogueServiceImpl) {

    @GetMapping("/tickets")
    fun getAllTickets() : Flow<TicketDTO> {
        return ticketCatalogueService.getAllTickets()
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
    fun getAllUserOrders(principal: Principal) : Flow<OrderDTO> {
        return ticketCatalogueService.getAllUserOrders(principal.name)
    }

    @GetMapping("/orders/{orderId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
    suspend fun getUserOrderById(@PathVariable orderId: Long, principal: Principal): OrderDTO? {
        return ticketCatalogueService.getUserOrder(principal.name, orderId)
    }

    @GetMapping("/admin/orders")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    fun getAllOrders(): Flow<OrderDTO> {
        return ticketCatalogueService.getAllOrders()
    }

    // We use the unique nickname of the user as userId
    @GetMapping("/admin/orders/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    fun getAllUserOrders(@PathVariable userId: String): Flow<OrderDTO> {
        return ticketCatalogueService.getAllUserOrders(userId)
    }

    @PostMapping("/shop/{ticketId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
    suspend fun shopTickets(
        @PathVariable ticketId: Long,
        @RequestHeader header: String,
        @RequestBody body: String,
        br: BindingResult,
        principal: Principal
    ): Any {
        println(header)
        return if (ticketCatalogueService.shopTickets(principal.name, ticketId, 2, PaymentInfo("cardNumber", "expiration", 0, "cardholder"),
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NzYwOTQ3MCwiaWF0IjoxNjU0MDA5NDcwLCJyb2xlcyI6WyJBRE1JTiIsIkNVU1RPTUVSIl19.3PlWqpH-Y_xqNvAKWXmRY3ZqVzm8JAOKONQat6PLILo"))
            true
        else "You're not able to buy this kind of ticket"
    }
}