package ans.amir.controll

import ans.amir.service.hash.HashService
import ans.amir.service.link.LinkService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(path = ["/generator"])
class LinkGeneratorController(val countLimitedLinkService: LinkService<Long>, private val hashService: HashService) {
    @Value("\${baseAddress}")
    val baseAddress: String = "localhost:8080/"
    @Value("\${response.CountLimitedRoute}")
    val responseCountLimitedRoute = "request/count/"
    @Value("\${response.TimeLimitedRoute}")
    val responseTimeLimitedRoute = "request/time/"

    @GetMapping(path = ["/limitedTime/{startDate}/{expirationDate}"])
    fun getTimeLimitedLink(@PathVariable startDate: String, @PathVariable expirationDate: String): ResponseEntity<String> {
        val parts: List<String> = listOf("request/time/", startDate, expirationDate)
        val hash = hashService.toHash(parts)
        val link = "$baseAddress$responseTimeLimitedRoute$hash/$startDate/$expirationDate"
        return ResponseEntity.ok(link)
    }

    @GetMapping(path = ["/limitedCount/{limitation}"])
    fun getCountLimitedLink(@PathVariable limitation: Long): ResponseEntity<String> {
        val parts: List<String> = listOf("request/count/", limitation.toString())
        val hash = hashService.toHash(parts)
        val link = "$baseAddress$responseCountLimitedRoute$hash/$limitation"
        if (!countLimitedLinkService.addLink(hash)) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "link already exists")
        }
        return ResponseEntity.ok(link)
    }
}