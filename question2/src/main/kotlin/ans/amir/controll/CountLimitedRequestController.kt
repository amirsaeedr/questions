package ans.amir.controll

import ans.amir.service.LinkService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping(path = ["/request/count"])
class CountLimitedRequestController(private val service: LinkService) {

    val salt: Int = 100
    @PostMapping(path = ["{hash}/{count}"])
    fun useLink(@PathVariable hash: String, @PathVariable count: String): ResponseEntity<String> {
        if (hash != ("request/count/$count$salt").hashCode().toString()){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "link is broken")
        }

        if(service.validate(hash,count.toLong())){
            return ResponseEntity.ok("it's valid")
        }
        throw ResponseStatusException(HttpStatus.FORBIDDEN, "permission denied")
    }
}