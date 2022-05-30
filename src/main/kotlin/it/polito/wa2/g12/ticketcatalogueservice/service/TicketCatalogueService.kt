package it.polito.wa2.g12.ticketcatalogueservice.service

import it.polito.wa2.g12.ticketcatalogueservice.entity.Order
import reactor.core.publisher.Flux
import it.polito.wa2.g12.ticketcatalogueservice.entity.Ticket
import kotlinx.coroutines.flow.Flow

interface TicketCatalogueService {
    fun getAllTickets(): Flow<Ticket>
    fun getAllOrders(): Flow<Order>
}