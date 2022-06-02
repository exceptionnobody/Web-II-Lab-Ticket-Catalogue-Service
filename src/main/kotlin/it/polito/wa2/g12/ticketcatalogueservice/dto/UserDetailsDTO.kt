package it.polito.wa2.g12.ticketcatalogueservice.dto

data class UserDetailsDTO(
    val username: String,
    val roles : List<String>
)