package ans.amir.controll

import ans.amir.service.LinkService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/generator"])
class LinkGeneratorController(val countLimitedLinkService: LinkService<Long>) {

    val salt: Int = 100
    val baseAddress: String = "localhost:8080/"
    @GetMapping(path = ["/limitedTime/{startDate}/{expirationDate}"])
    fun getTimeLimitedLink(@PathVariable startDate: String, @PathVariable expirationDate: String): ResponseEntity<String> {
        //TODO it's just a sketch
        return ResponseEntity.ok(baseAddress + "request/time/" + ("request/time/$startDate$expirationDate$salt").hashCode().toString() + "/" + startDate + "/" + expirationDate)
    }

    @GetMapping(path = ["/limitedCount/{limitation}"])
    fun getCountLimitedLink(@PathVariable limitation: Long): ResponseEntity<String> {
        //TODO it's just a sketch
        val hash = ("request/count/$limitation$salt").hashCode().toString()
        countLimitedLinkService.addLink(hash)
        return ResponseEntity.ok(baseAddress + "request/count/" + hash + "/" + limitation)
    }
}