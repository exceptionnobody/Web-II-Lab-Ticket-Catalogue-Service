package it.polito.wa2.g12.ticketcatalogueservice.controller

import it.polito.wa2.g12.ticketcatalogueservice.service.MyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MyController(val myService: MyService) {

    @GetMapping("/customer")
    fun getAllCustomers() : ResponseEntity<Any> {
        val res = myService.findCustomers()
        return ResponseEntity(res, HttpStatus.OK)
    }

    @GetMapping("/newCustomer")
    fun newCustomer() : ResponseEntity<Any> {
        val res = myService.newCustomer()
        return ResponseEntity(res, HttpStatus.CREATED)
    }

}