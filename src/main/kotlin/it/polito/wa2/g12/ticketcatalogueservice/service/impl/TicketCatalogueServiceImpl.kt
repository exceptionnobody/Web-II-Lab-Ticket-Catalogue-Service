package it.polito.wa2.g12.ticketcatalogueservice.service.impl

import it.polito.wa2.g12.ticketcatalogueservice.dto.OrderDTO
import it.polito.wa2.g12.ticketcatalogueservice.dto.TicketDTO
import it.polito.wa2.g12.ticketcatalogueservice.entity.toDTO
import it.polito.wa2.g12.ticketcatalogueservice.repository.OrderRepository
import it.polito.wa2.g12.ticketcatalogueservice.repository.TicketRepository
import it.polito.wa2.g12.ticketcatalogueservice.service.TicketCatalogueService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override fun getAllTickets(): Flow<TicketDTO> {
        return ticketRepository.findAllTickets().map { it.toDTO() }
    }

    override fun getAllOrders(): Flow<OrderDTO> {
        return orderRepository.findAllOrders().map { it.toDTO() }
    }

    override fun getAllUserOrders(username: String): Flow<OrderDTO> {
        return orderRepository.findAllUserOrders(username).map { it.toDTO() }
    }

    override suspend fun getUserOrder(username: String, orderId: Long): OrderDTO? {
        return orderRepository.findUserOrderById(username, orderId)?.toDTO()
    }
}