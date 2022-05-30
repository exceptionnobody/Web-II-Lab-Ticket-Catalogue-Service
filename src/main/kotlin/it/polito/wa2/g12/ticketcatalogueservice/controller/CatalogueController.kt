package it.polito.wa2.g12.ticketcatalogueservice.controller

import it.polito.wa2.g12.ticketcatalogueservice.service.TicketCatalogueService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CatalogueController(val catalogueService: TicketCatalogueService) {

    @GetMapping("/tickets")
    fun getAllCustomers() : ResponseEntity<Any> {
        val res = catalogueService.getTickets()
        return ResponseEntity(res, HttpStatus.OK)
    }
}