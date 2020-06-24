package ans.amir.repository

import ans.amir.entity.CountLimitedLink
import org.springframework.data.repository.CrudRepository

interface CountLimitedLinkRepository: CrudRepository<CountLimitedLink,Long> {
    fun findByLinkHash(linkHash: String): CountLimitedLink?
}