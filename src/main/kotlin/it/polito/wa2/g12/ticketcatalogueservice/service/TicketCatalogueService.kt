package it.polito.wa2.g12.ticketcatalogueservice.service

import it.polito.wa2.g12.ticketcatalogueservice.dto.OrderDTO
import it.polito.wa2.g12.ticketcatalogueservice.dto.PaymentInfoDTO
import it.polito.wa2.g12.ticketcatalogueservice.dto.TicketDTO
import kotlinx.coroutines.flow.Flow

interface TicketCatalogueService {
    fun getAllTickets(): Flow<TicketDTO>
    fun getAllOrders(): Flow<OrderDTO>
    fun getAllUserOrders(username: String): Flow<OrderDTO>
    suspend fun getUserOrder(username: String, orderId: Long): OrderDTO?
    suspend fun addNewTicket(t: TicketDTO): TicketDTO?
    suspend fun shopTickets(username: String, ticketdId: Long, quantity: Int, paymentInfo: PaymentInfoDTO, jwt: String): Boolean
}