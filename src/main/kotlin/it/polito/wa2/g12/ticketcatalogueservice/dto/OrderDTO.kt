package it.polito.wa2.g12.ticketcatalogueservice.dto

class OrderDTO(
    val id: Long,
    val quantity: Int,
    val status: String,
    val username: String,
    val ticket_id: Long
)