package ans.amir.service.link


import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Service
class TimeLimitedLinkService : LinkService<List<String>> {
    override fun validate(hash: String, limitation: List<String>): Boolean {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss")
        val currentDate: LocalDateTime? = LocalDateTime.now()
        return currentDate?.isAfter(LocalDateTime.parse(limitation[0], formatter))!! &&
                currentDate.isBefore(LocalDateTime.parse(limitation[1], formatter))
    }

    override fun addLink(hash: String): Boolean {
        TODO("not implemented")
    }
}