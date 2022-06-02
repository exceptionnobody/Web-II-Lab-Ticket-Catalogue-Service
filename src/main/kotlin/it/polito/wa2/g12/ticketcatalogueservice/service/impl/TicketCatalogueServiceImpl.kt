package it.polito.wa2.g12.ticketcatalogueservice.service.impl

import it.polito.wa2.g12.ticketcatalogueservice.dto.*
import it.polito.wa2.g12.ticketcatalogueservice.entity.Order
import it.polito.wa2.g12.ticketcatalogueservice.entity.Ticket
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
import java.util.*
import kotlin.math.absoluteValue


// TODO: use this guide to pass from webflux to coroutine
// https://www.baeldung.com/kotlin/spring-boot-kotlin-coroutines

//data class PaymentInfo(val cardNumber: String, val exp: String, val cvv: Int, val cardHolder: String)
//private data class UserDet(val name: String, val address: String, val date_of_birth: String, val number: String)
//private data class AddingTicketReq(val cmd: String, val quantity: Int, val zones: String)

@Service
class TicketCatalogueServiceImpl: TicketCatalogueService {
    @Autowired
    lateinit var ticketRepository: TicketRepository
    @Autowired
    lateinit var orderRepository: OrderRepository

    override fun getAllTickets(): Flow<TicketDTO> {
        return ticketRepository.findAllTickets().map { it.toDTO() }
    }

    override fun getAllOrders(): Flow<OrderDTO> {
        return orderRepository.findAllOrders().map { it.toDTO() }
    }

    override fun getAllUserOrders(username: String): Flow<OrderDTO> {
        return orderRepository.findAllUserOrders(username).map { it.toDTO() }
    }

    override suspend fun getUserOrder(username: String, orderId: Long): OrderDTO? {
        return orderRepository.findUserOrderById(username, orderId)?.toDTO()
    }

    override suspend fun addNewTicket(t: TicketDTO): TicketDTO? {
        return ticketRepository.save(
            Ticket(
                t.ticket_type,
                t.price,
                t.zones,
                t.minimum_age,
                t.maximum_age,
                t.duration,
                t.only_weekends
        )).toDTO()
    }

    private fun isValidAge(ticket: TicketDTO, profile: UserProfileDTO): Boolean {
        val calendar = Calendar.getInstance()
        val localTime = LocalDate.now()

        calendar.time = profile.date_of_birth
        val age = (calendar.get(Calendar.YEAR) - localTime.year).absoluteValue
        return age <= ticket.maximum_age && age >= ticket.minimum_age
    }

    override suspend fun shopTickets(username: String, ticketdId: Long, quantity: Int, paymentInfo: PaymentCardDTO, jwt: String): Boolean {
        val ticket: TicketDTO? = ticketRepository.findById(ticketdId)?.toDTO()
        val response: UserProfileDTO = WebClient
            .create("http://localhost:8081")
            .get()
            .uri("/my/profile")
            .header("Authorization", jwt)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .awaitBody()
        println(response) // TODO: remove me

/*
        val ob = jacksonObjectMapper()
        val om = jacksonObjectMapper()
        val userDet = ob.readValue(response, UserDet::class.java)
*/

        // Ticket not found
        if (ticket == null)
            return false

        if (isValidAge(ticket, response)) {
            println("USER VALIDO"); // TODO: remove me

            orderRepository.save(Order(quantity, "PENDING", username, ticketdId))
            Order(quantity, "PENDING", username, ticketdId).toDTO()

            // THE CODE BELOW MUST BE CHANGED:

            // CONSEGNA:
            // "it transmits the billing information and the total cost of the
            // order to the payment service through a kafka topic, and it returns the orderId. When
            // the Kafka listener receives the outcome of the transaction, the status of order is
            // updated according to what the payment service has stated and, if the operation was
            // successful, the purchased products are added to the list of acquired tickets in the
            // TravellerService."

            /*
            // Contacts the payment service
            // If succeeded, contacts the traveler service to store the new tickets
            val paymentResponse: String = WebClient
                .create("http://localhost:/porta del payment service")
                .post()
                .uri("endpoint payment service controller")
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
            }
            */

            return true
        } else {
            println("USER NON VALIDO"); // TODO: remove me
            return false
        }
    }
}
//
//
//@RestController
//@RequestMapping("/students")
//class StudentsController {
//    @Autowired
//    private val student_repository: Student_Repository? = null
//    @PostMapping
//    fun create(@RequestBody student: Student?): Mono<ResponseEntity<Student>> {
//        return student_repository.save(student)
//            .map { savedStudent -> ResponseEntity.ok(savedStudent) }
//            .defaultIfEmpty(ResponseEntity.notFound().build())
//    }
//
//    @get:GetMapping
//    val students: Flux<Any>
//        get() = student_repository.findAll()
//
//    @GetMapping("/{studentId}")
//    fun getStudentById(@PathVariable studentId: Int): Mono<ResponseEntity<Student>> {
//        return student_repository.findById(studentId)
//            .map { student -> ResponseEntity.ok(student) }
//            .defaultIfEmpty(ResponseEntity.notFound().build())
//    }
//
//    @PutMapping("/{studentId}")
//    fun updateStudent(@PathVariable studentId: Int, @RequestBody student: Student): Mono<*> {
//        return student_repository.findById(studentId)
//            .flatMap { selectedStudentFromDB ->
//                selectedStudentFromDB.setName(student.getName())
//                selectedStudentFromDB.setAge(student.getAge())
//                selectedStudentFromDB.setUniversity(student.getUniversity())
//                selectedStudentFromDB.setGpa(student.getGpa())
//                student_repository.save(selectedStudentFromDB)
//            }
//            .map { updatedStudent -> ResponseEntity.ok(updatedStudent) }
//            .defaultIfEmpty(ResponseEntity<T>(HttpStatus.NOT_FOUND))
//    }
//
//    @DeleteMapping("/student/{id}")
//    fun deleteStudent(@PathVariable(value = "id") studentId: Int): Mono<ResponseEntity<Void>> {
//        return student_repository.findById(studentId)
//            .flatMap { selectedStudentFromDB ->
//                student_repository.delete(selectedStudentFromDB)
//                    .then(Mono.just(ResponseEntity<Void>(HttpStatus.OK)))
//            }
//            .defaultIfEmpty(ResponseEntity<T>(HttpStatus.NOT_FOUND))
//    }
//}