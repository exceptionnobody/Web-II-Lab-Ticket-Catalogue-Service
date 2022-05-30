package it.polito.wa2.g12.ticketcatalogueservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table
class TicketCatalogue(
    @Column
    var price: Double,
    @Column
    var ticket_type: String,
    @Column
    var zone: String
) {
    @Id
    var id: Long? = null
}

fun TicketCatalogue.toDTO() = TicketCatalogue(price, ticket_type, zone)