package ans.amir.entity

import javax.persistence.*


@Entity
data class CountLimitedLink(var linkHash: String = "", var used: Long = 0, @Id
@GeneratedValue(strategy = GenerationType.AUTO)
var id: Long?=null)
