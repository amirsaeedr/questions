package ans.amir.service

import ans.amir.entity.CountLimitedLink
import ans.amir.repository.CountLimitedLinkRepository
import org.springframework.stereotype.Service

@Service
class CountLimitedLinkService(private val repository: CountLimitedLinkRepository) : LinkService {
    override fun validate(hash: String, count: Long): Boolean {
        val limitedLink = repository.findByLinkHash(hash)?: repository.save(CountLimitedLink(hash,0))
        if (count > limitedLink.used) {
            limitedLink.used++
            repository.save(limitedLink)
            return true
        }
        return false
    }
}