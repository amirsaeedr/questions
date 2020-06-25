package ans.amir.service.link

import org.springframework.stereotype.Service



interface LinkService<T> {
    fun validate(hash: String, limitation: T): Boolean
    fun addLink(hash: String): Boolean
}