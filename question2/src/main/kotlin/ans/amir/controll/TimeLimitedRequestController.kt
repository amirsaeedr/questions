package ans.amir.controll

import ans.amir.service.hash.HashService
import ans.amir.service.link.TimeLimitedLinkService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException



@RestController
@RequestMapping(path = ["/request/time"])
class TimeLimitedRequestController(
    //TODO (fix autowired)
    private val linkService: TimeLimitedLinkService,
    private val hashService: HashService
) {

    @GetMapping(path = ["/{hash}/{startDate}/{expirationDate}"])
    fun getTimeLimitedLink(@PathVariable startDate: String, @PathVariable expirationDate: String, @PathVariable hash: String): ResponseEntity<String> {
        val parts: List<String> = listOf("request/time/", startDate, expirationDate)
        val limitation: List<String> = listOf(startDate, expirationDate)
        if (hash != hashService.toHash(parts)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "link is broken")
        }
        if (linkService.validate(hash, limitation)) {
            return ResponseEntity.ok("it's valid")
        }
        throw ResponseStatusException(HttpStatus.FORBIDDEN, "permission denied")
    }
}