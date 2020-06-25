package ans.amir.service.link

import ans.amir.entity.CountLimitedLink
import ans.amir.repository.CountLimitedLinkRepository
import org.springframework.stereotype.Service

@Service
class CountLimitedLinkService(private val repository: CountLimitedLinkRepository) :
    LinkService<Long> {
    override fun validate(hash: String, limitation: Long): Boolean {
        val limitedLink = repository.findByLinkHash(hash) ?: throw NoSuchElementException()
        if (limitation > limitedLink.used) {
            limitedLink.used++
            repository.save(limitedLink)
            return true
        }
        return false
    }

    override fun addLink(hash: String) = repository.save(CountLimitedLink(hash, 0))
}