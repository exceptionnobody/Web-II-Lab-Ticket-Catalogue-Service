package it.polito.wa2.g12.ticketcatalogueservice.controller

import it.polito.wa2.g12.ticketcatalogueservice.dto.OrderDTO
import it.polito.wa2.g12.ticketcatalogueservice.dto.PaymentInfoDTO
import it.polito.wa2.g12.ticketcatalogueservice.dto.TicketDTO
import it.polito.wa2.g12.ticketcatalogueservice.service.impl.TicketCatalogueServiceImpl
import kotlinx.coroutines.flow.Flow
import org.springframework.security.access.prepost.PreAuthorize
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

    // Use a JSON like this one to test this endpoint:
    // {"ticket_type":"ordinal","price":"20","zones":"XYZ","minimum_age":18,"maximum_age":30,"duration":"24","only_weekends":false}
    @PostMapping("/admin/tickets")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    suspend fun addNewTicket(@RequestBody ticket: TicketDTO): TicketDTO? {
        return ticketCatalogueService.addNewTicket(ticket)
    }

    @PostMapping("/shop")
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
    suspend fun shopTickets(
        @RequestHeader("Authorization") authorizationHeader: String,
        @RequestBody paymentInfo: PaymentInfoDTO,
        principal: Principal
    ): OrderDTO {
        return ticketCatalogueService.shopTickets(
                    principal.name,
                    paymentInfo,
                    authorizationHeader
                )
    }
}