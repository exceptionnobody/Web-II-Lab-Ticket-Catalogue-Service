package it.polito.wa2.g12.ticketcatalogueservice.controller

import it.polito.wa2.g12.ticketcatalogueservice.dto.OrderDTO
import it.polito.wa2.g12.ticketcatalogueservice.dto.TicketDTO
import it.polito.wa2.g12.ticketcatalogueservice.service.impl.TicketCatalogueServiceImpl
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TicketCatalogueController(val ticketCatalogueService: TicketCatalogueServiceImpl) {

    @GetMapping("/tickets")
    fun getAllCustomers() : Flow<TicketDTO> {
        return ticketCatalogueService.getAllTickets()
    }

    @GetMapping("/orders")
    fun getAllUserOrders() : Flow<OrderDTO> {
        // TODO: extract customer username from the principal
        return ticketCatalogueService.getAllUserOrders("MarioRossi")
    }

    @GetMapping("/orders/{orderId}")
    suspend fun getUserOrder(@PathVariable orderId: Long): OrderDTO? {
        return ticketCatalogueService.getUserOrder("MarioRossi", orderId)
    }

    @GetMapping("/admin/orders")
    fun getAllOrders(): Flow<OrderDTO> {
        return ticketCatalogueService.getAllOrders()
    }

    @GetMapping("/admin/orders/{userId}")
    fun getAllUserOrders(@PathVariable userId: String): Flow<OrderDTO> {
        // TODO: we should save in the principal also the user id? IDK
        // Usually an Id is a Long and not a String
        return ticketCatalogueService.getAllUserOrders(userId)
    }
}