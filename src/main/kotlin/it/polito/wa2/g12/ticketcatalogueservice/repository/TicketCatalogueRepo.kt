package it.polito.wa2.g12.ticketcatalogueservice.repository

import it.polito.wa2.g12.ticketcatalogueservice.entity.TicketCatalogue
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TicketCatalogueRepo: ReactiveCrudRepository<TicketCatalogue?, Long?> {
}