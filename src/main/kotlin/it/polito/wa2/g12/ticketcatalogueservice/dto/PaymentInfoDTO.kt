package it.polito.wa2.g12.ticketcatalogueservice.dto

class PaymentInfoDTO(
    val cardNumber: String,
    val exp: String,
    val cvv: Int,
    val cardHolder: String
)