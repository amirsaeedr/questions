package ans.amir.service.hash

import org.springframework.stereotype.Service

@Service
class SimpleHashService : HashService {

    val salt: String = "100"
    override fun toHash(parts: List<String>): String {
        var raw: String = salt
        parts.forEach { s: String? -> raw = "raw$s" }
        return raw.hashCode().toString()
    }
}