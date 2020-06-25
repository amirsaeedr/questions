package ans.amir.controll

import ans.amir.service.hash.HashService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDateTime
import java.time.format.DateTimeFormatter


@RestController
@RequestMapping(path = ["/request/time"])
class TimeLimitedRequestController(private val hashService: HashService) {

    @GetMapping(path = ["/{hash}/{startDate}/{expirationDate}"])
    fun getTimeLimitedLink(@PathVariable startDate: String, @PathVariable expirationDate: String, @PathVariable hash: String): ResponseEntity<String> {

        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        val parts: List<String> = listOf("request/time/", startDate, expirationDate)
        if (hash != hashService.toHash(parts)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "link is broken")
        }
        val currentDate: LocalDateTime? = LocalDateTime.now()
        if (currentDate?.isAfter(LocalDateTime.parse(startDate, formatter))!!
            && currentDate.isBefore(LocalDateTime.parse(expirationDate, formatter))
        ) {
            return ResponseEntity.ok("it's valid")
        }
        throw ResponseStatusException(HttpStatus.FORBIDDEN, "permission denied")
    }
}