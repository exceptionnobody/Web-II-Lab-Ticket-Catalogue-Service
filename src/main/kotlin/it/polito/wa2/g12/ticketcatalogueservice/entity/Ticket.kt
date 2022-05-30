package it.polito.wa2.g12.ticketcatalogueservice.entity

import it.polito.wa2.g12.ticketcatalogueservice.dto.TicketDTO
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("ticket_catalogue")
class Ticket(
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

fun Ticket.toDTO() = TicketDTO(price, ticket_type, zone)