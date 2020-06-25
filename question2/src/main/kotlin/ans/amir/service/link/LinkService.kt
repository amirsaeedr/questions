package ans.amir.service.link

import ans.amir.entity.CountLimitedLink


interface LinkService<T> {
    fun validate(hash: String, limitation: T): Boolean
    fun addLink(hash: String): CountLimitedLink
}