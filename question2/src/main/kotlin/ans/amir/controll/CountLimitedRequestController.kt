package ans.amir.controll

import ans.amir.service.hash.HashService
import ans.amir.service.link.LinkService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping(path = ["/request/count"])
class CountLimitedRequestController(private val linkService: LinkService<Long>, private val hashService: HashService) {

    @Value("\${response.CountLimitedRoute}")
    val responseCountLimitedRoute = "request/count/"

    @GetMapping(path = ["{hash}/{count}"])
    fun useLink(@PathVariable hash: String, @PathVariable count: String): ResponseEntity<String> {
        val parts: List<String> = listOf(responseCountLimitedRoute, count)
        if (hash != hashService.toHash(parts)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "link is broken")
        }
        try {
            if (linkService.validate(hash, count.toLong())) {
                return ResponseEntity.ok("it's valid")
            }
        } catch (e: NoSuchElementException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "link does not exists")
        }
        throw ResponseStatusException(HttpStatus.FORBIDDEN, "permission denied")
    }

}