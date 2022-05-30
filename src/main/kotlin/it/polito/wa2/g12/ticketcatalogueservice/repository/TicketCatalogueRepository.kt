package it.polito.wa2.g12.ticketcatalogueservice.repository

import it.polito.wa2.g12.ticketcatalogueservice.entity.Ticket
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TicketCatalogueRepository: ReactiveCrudRepository<Ticket?, Long?>