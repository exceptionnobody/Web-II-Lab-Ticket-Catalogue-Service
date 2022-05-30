package it.polito.wa2.g12.ticketcatalogueservice.controller

import it.polito.wa2.g12.ticketcatalogueservice.entity.Order
import it.polito.wa2.g12.ticketcatalogueservice.entity.Ticket
import it.polito.wa2.g12.ticketcatalogueservice.service.impl.TicketCatalogueServiceImpl
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TicketCatalogueController(val ticketCatalogueService: TicketCatalogueServiceImpl) {

    @GetMapping("/tickets")
    fun getAllCustomers() : Flow<Ticket> {
        return ticketCatalogueService.getAllTickets()
    }

    @GetMapping("/orders")
    fun getAllOrders() : Flow<Order> {
        return ticketCatalogueService.getAllOrders()
    }
}