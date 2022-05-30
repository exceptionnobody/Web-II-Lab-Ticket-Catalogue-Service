package it.polito.wa2.g12.ticketcatalogueservice.service.Implementation

import it.polito.wa2.g12.ticketcatalogueservice.entity.TicketCatalogue
import it.polito.wa2.g12.ticketcatalogueservice.repository.TicketCatalogueRepo
import it.polito.wa2.g12.ticketcatalogueservice.service.TicketCatalogueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class TicketCatalogueServiceImpl: TicketCatalogueService {
    @Autowired
    lateinit var catalogueRepo: TicketCatalogueRepo

    override fun getTickets(): Flux<TicketCatalogue?> {
        return catalogueRepo.findAll()
    }
}