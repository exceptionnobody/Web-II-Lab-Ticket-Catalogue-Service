package it.polito.wa2.g12.ticketcatalogueservice.controller

import it.polito.wa2.g12.ticketcatalogueservice.dto.OrderDTO
import it.polito.wa2.g12.ticketcatalogueservice.dto.TicketDTO
import it.polito.wa2.g12.ticketcatalogueservice.service.impl.PaymentInfo
import it.polito.wa2.g12.ticketcatalogueservice.service.impl.TicketCatalogueServiceImpl
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TicketCatalogueController(val ticketCatalogueService: TicketCatalogueServiceImpl) {

    @GetMapping("/tickets")
    suspend fun getAllCustomers() : ResponseEntity<Any> {
        val res = ticketCatalogueService.getAllTickets()
        return if (res != null) ResponseEntity(res, HttpStatus.OK)
        else ResponseEntity("Ticket catalogue empty", HttpStatus.NOT_FOUND)
    }

    @GetMapping("/orders")
    fun getAllUserOrders() : ResponseEntity<Any> {
        // TODO: extract customer username from the principal
        val res = ticketCatalogueService.getAllUserOrders("MarioRossi")
        return if (res != null) ResponseEntity(res, HttpStatus.OK)
        else ResponseEntity("No orders for the specified user", HttpStatus.NOT_FOUND)
    }

    @GetMapping("/orders/{orderId}")
    suspend fun getUserOrder(@PathVariable orderId: Long): ResponseEntity<Any> {
        val res = ticketCatalogueService.getUserOrder("MarioRossi", orderId)
        return if (res != null) ResponseEntity(res, HttpStatus.OK)
        else ResponseEntity("Order not found", HttpStatus.NOT_FOUND)
    }

    @GetMapping("/admin/orders")
    fun getAllOrders(): ResponseEntity<Any> {
        val res = ticketCatalogueService.getAllOrders()
        return if (res != null) ResponseEntity(res, HttpStatus.OK)
        else ResponseEntity("Order table empty", HttpStatus.NOT_FOUND)
    }

    @GetMapping("/admin/orders/{userId}")
    fun getAllUserOrders(@PathVariable userId: String): ResponseEntity<Any> {
        // TODO: we should save in the principal also the user id? IDK
        val res = ticketCatalogueService.getAllUserOrders(userId)
        return if (res != null) ResponseEntity(res, HttpStatus.OK)
        else ResponseEntity("No orders for the specified user", HttpStatus.NOT_FOUND)
    }

    @GetMapping("/toTravelerService")
    suspend fun getUserdet(): Any {
        // TODO: we should save in the principal also the user id? IDK
        // Usually an Id is a Long and not a String
        return if (ticketCatalogueService.shopTickets("admin", 3, 2, PaymentInfo("cardNumber", "expiration", 0, "cardholder"),
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NzYwOTQ3MCwiaWF0IjoxNjU0MDA5NDcwLCJyb2xlcyI6WyJBRE1JTiIsIkNVU1RPTUVSIl19.3PlWqpH-Y_xqNvAKWXmRY3ZqVzm8JAOKONQat6PLILo"))
            true
        else "You're not able to buy this kind of ticket"
    }
}