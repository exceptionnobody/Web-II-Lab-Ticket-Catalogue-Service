package it.polito.wa2.g12.ticketcatalogueservice.service.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import it.polito.wa2.g12.ticketcatalogueservice.dto.OrderDTO
import it.polito.wa2.g12.ticketcatalogueservice.dto.TicketDTO
import it.polito.wa2.g12.ticketcatalogueservice.entity.Order
import it.polito.wa2.g12.ticketcatalogueservice.entity.toDTO
import it.polito.wa2.g12.ticketcatalogueservice.repository.OrderRepository
import it.polito.wa2.g12.ticketcatalogueservice.repository.TicketRepository
import it.polito.wa2.g12.ticketcatalogueservice.service.TicketCatalogueService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import java.time.LocalDate
import kotlin.math.absoluteValue

// TODO: use this guide to pass from webflux to coroutine
// https://www.baeldung.com/kotlin/spring-boot-kotlin-coroutines

data class PaymentInfo(val cardNumber: String, val exp: String, val cvv: Int, val cardHolder: String)
private data class UserDet(val name: String, val address: String, val date_of_birth: String, val number: String)
private data class AddingTicketReq(val cmd: String, val quantity: Int, val zones: String)

@Service
class TicketCatalogueServiceImpl: TicketCatalogueService {
    @Autowired
    lateinit var ticketRepository: TicketRepository
    @Autowired
    lateinit var orderRepository: OrderRepository

    override fun getAllTickets(): Flow<TicketDTO>? {
        return ticketRepository.findAllTickets().map { it.toDTO() }
    }

    override fun getAllOrders(): Flow<OrderDTO>? {
        return orderRepository.findAllOrders().map { it.toDTO() }
    }

    override fun getAllUserOrders(username: String): Flow<OrderDTO>? {
        return orderRepository.findAllUserOrders(username).map { it.toDTO() }
    }

    override suspend fun getUserOrder(username: String, orderId: Long): OrderDTO? {
        return orderRepository.findUserOrderById(username, orderId)?.toDTO()
    }

    private fun isValidUser(ticket: TicketDTO, user: UserDet): Boolean {
        val localTime = LocalDate.now()
        return if (ticket.ticket_type != "ordinal"){
            when (ticket.ticket_type) {
                "under 18" -> (LocalDate.parse(user.date_of_birth).year - localTime.year).absoluteValue <= 18
                "over 60" -> (LocalDate.parse(user.date_of_birth).year - localTime.year).absoluteValue >= 60
                else -> true
            }
        } else true
    }

    override suspend fun shopTickets(username: String, ticketdId: Long, quantity: Int, paymentInfo: PaymentInfo, jwt: String): Boolean {
        val ticket: TicketDTO? = ticketRepository.findById(ticketdId)?.toDTO()

        val response: String = WebClient.create("http://localhost:8081").get().uri("/my/profile")
            .header("Authorization", jwt)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve().awaitBody()

        val ob = jacksonObjectMapper()
        val om = jacksonObjectMapper()
        val userDet = ob.readValue(response, UserDet::class.java)

        if (ticket != null) {
            if (isValidUser(ticket, userDet)){
                orderRepository.save(Order(quantity, "PENDING", username, ticketdId))
                Order(quantity, "PENDING", username, ticketdId).toDTO()
                //contact the payment service
                //if success, contact the traveler service, to store the new tickets
                /*val paymentResponse: String = WebClient.create("http://localhost:/porta del payment service").post().uri("endpoint payment service controller")
                    //.header() settare header
                    //.body() settare body
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve().awaitBody()
                if ("payment ok") {
                    //update order status
                    val pendingOrder = orderRepository.findAllUserOrders(username).filter { it.status == "PENDING" && it.ticketId == ticketdId }.first()
                    pendingOrder.status = "PAYED"

                    //contacting traveler service to create new tickets
                    val postTicketResponse: String = WebClient.create("http://localhost:8081").post().uri("/my/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwt)
                        .bodyValue(om.writeValueAsString(AddingTicketReq("buy_tickets", quantity, ticket.zone)))
                        .retrieve()
                        .awaitBody<String>()

                }*/
            } else return false
        }
        return true
    }
}