package ans.amir.service


interface LinkService {
    fun validate(hash: String, count: Long): Boolean
}