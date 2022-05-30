package it.polito.wa2.g12.ticketcatalogueservice.service.impl

import it.polito.wa2.g12.ticketcatalogueservice.entity.Order
import it.polito.wa2.g12.ticketcatalogueservice.entity.Ticket
import it.polito.wa2.g12.ticketcatalogueservice.repository.OrderRepository
import it.polito.wa2.g12.ticketcatalogueservice.repository.TicketCatalogueRepository
import it.polito.wa2.g12.ticketcatalogueservice.service.TicketCatalogueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class TicketCatalogueServiceImpl: TicketCatalogueService {
    @Autowired
    lateinit var ticketCatalogueRepository: TicketCatalogueRepository
    @Autowired
    lateinit var orderRepository: OrderRepository

    override fun getTickets(): Flux<Ticket?> {
        return ticketCatalogueRepository.findAll()
    }

    override fun getOrders(): Flux<Order?> {
        return orderRepository.findAll()
    }
}