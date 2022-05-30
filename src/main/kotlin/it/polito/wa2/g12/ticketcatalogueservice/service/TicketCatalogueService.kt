package it.polito.wa2.g12.ticketcatalogueservice.service

import reactor.core.publisher.Flux
import it.polito.wa2.g12.ticketcatalogueservice.entity.TicketCatalogue

interface TicketCatalogueService {
    fun getTickets(): Flux<TicketCatalogue?>
}