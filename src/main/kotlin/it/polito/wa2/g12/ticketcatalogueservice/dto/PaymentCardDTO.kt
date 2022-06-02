package it.polito.wa2.g12.ticketcatalogueservice.dto

class PaymentCardDTO(
    val number: String,
    val expiration_date: String,
    val cvv: Int,
    val holder: String
)