package it.polito.wa2.g12.ticketcatalogueservice.controller

import it.polito.wa2.g12.ticketcatalogueservice.entity.Order
import it.polito.wa2.g12.ticketcatalogueservice.service.impl.TicketCatalogueServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class TicketCatalogueController(val ticketCatalogueService: TicketCatalogueServiceImpl) {

    @GetMapping("/tickets")
    fun getAllCustomers() : ResponseEntity<Any> {
        val res = ticketCatalogueService.getTickets()
        return ResponseEntity(res, HttpStatus.OK)
    }

    @GetMapping("/orders")
    fun getAllOrders() : ResponseEntity<Any> {
        val res = ticketCatalogueService.getOrders()
        return ResponseEntity(res, HttpStatus.OK)
    }
}