package ans.amir.controll

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(path = ["/"], produces = ["application/json"])
class LinkGeneratorController {

    val salt: Int = 100
    val baseAddress: String = "localhost:8080/"
    @GetMapping(path = ["/limitedTime/{startDate}/{expirationDate}"])
    fun getTimeLimitedLink(@PathVariable startDate: Date, @PathVariable expirationDate: Date): ResponseEntity<String> {
        //TODO it's just a scratch
        return ResponseEntity.ok(baseAddress + (baseAddress + startDate + expirationDate + salt).hashCode().toString() + "/" + startDate + "/" + expirationDate)
    }

    @GetMapping(path = ["/limitedCount/{count}"])
    fun getCountLimitedLink(@PathVariable count: Long): ResponseEntity<String> {
        //TODO it's just a scratch
        return ResponseEntity.ok(baseAddress + (baseAddress + count + salt).hashCode().toString() + "/" + count)
    }
}