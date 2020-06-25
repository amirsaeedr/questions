package ans.amir.service.hash

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SimpleHashService : HashService {
    @Value("\${salt}")
    val salt: String? = null

    override fun toHash(parts: List<String>): String {
        var raw: String = salt!!
        parts.forEach { s: String? -> raw+=s }
        return raw.hashCode().toString()
    }
}