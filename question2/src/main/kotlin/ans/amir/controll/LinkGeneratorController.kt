package ans.amir.controll

import ans.amir.service.hash.HashService
import ans.amir.service.link.LinkService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/generator"])
class LinkGeneratorController(val countLimitedLinkService: LinkService<Long>, private val hashService: HashService) {

    val baseAddress: String = "localhost:8080/"
    @GetMapping(path = ["/limitedTime/{startDate}/{expirationDate}"])
    fun getTimeLimitedLink(@PathVariable startDate: String, @PathVariable expirationDate: String): ResponseEntity<String> {
        //TODO it's just a sketch
        val parts: List<String> = listOf("request/time/", startDate, expirationDate)
        val hash = hashService.toHash(parts)
        val link: String = baseAddress + "request/time/" + hash + "/" + startDate + "/" + expirationDate
        return ResponseEntity.ok(link)
    }

    @GetMapping(path = ["/limitedCount/{limitation}"])
    fun getCountLimitedLink(@PathVariable limitation: Long): ResponseEntity<String> {
        //TODO it's just a sketch
        val parts: List<String> = listOf("request/count/", limitation.toString())
        val hash = hashService.toHash(parts)
        val link: String = baseAddress + "request/count/" + hash + "/" + limitation
        countLimitedLinkService.addLink(hash)
        return ResponseEntity.ok(link)
    }
}