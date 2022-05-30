package it.polito.wa2.g12.ticketcatalogueservice.service.impl

import it.polito.wa2.g12.ticketcatalogueservice.entity.Order
import it.polito.wa2.g12.ticketcatalogueservice.entity.Ticket
import it.polito.wa2.g12.ticketcatalogueservice.repository.OrderRepository
import it.polito.wa2.g12.ticketcatalogueservice.repository.TicketRepository
import it.polito.wa2.g12.ticketcatalogueservice.service.TicketCatalogueService
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

// TODO: use this guide to pass from webflux to coroutine
// https://www.baeldung.com/kotlin/spring-boot-kotlin-coroutines

@Service
class TicketCatalogueServiceImpl: TicketCatalogueService {
    @Autowired
    lateinit var ticketRepository: TicketRepository
    @Autowired
    lateinit var orderRepository: OrderRepository

    override fun getAllTickets(): Flow<Ticket> {
        return ticketRepository.findAllTickets()
    }

    override fun getAllOrders(): Flow<Order> {
        return orderRepository.findAllOrders()
    }
}