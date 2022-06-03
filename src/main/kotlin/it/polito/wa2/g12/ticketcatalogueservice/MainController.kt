/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.polito.wa2.g12.ticketcatalogueservice
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class MainController {

    private val principal = ReactiveSecurityContextHolder.getContext()
        .map { it.authentication.principal as String }


    //hasrole: CUSTOMER
    @GetMapping("/test/prova")
    fun findMessage(): Mono<String> {
        println("Customer")
        return principal

    }

    //hasrole: ADMIN
    @GetMapping("/admin/testadmin")
    fun findMessageAdmin(): Mono<String> {
        println("Admin")
        return principal

    }

}

