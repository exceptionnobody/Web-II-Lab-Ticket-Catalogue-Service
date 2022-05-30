package it.polito.wa2.g12.ticketcatalogueservice.dto

class OrderDTO(
    val quantity: Int,
    val status: String,
    val username: String,
    val ticketId: Long
)