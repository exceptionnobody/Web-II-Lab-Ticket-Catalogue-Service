package it.polito.wa2.g12.ticketcatalogueservice.service

import it.polito.wa2.g12.ticketcatalogueservice.entity.Order
import reactor.core.publisher.Flux
import it.polito.wa2.g12.ticketcatalogueservice.entity.Ticket

interface TicketCatalogueService {
    fun getTickets(): Flux<Ticket?>
    fun getOrders(): Flux<Order?>
}