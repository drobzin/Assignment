import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
 data class Replacement(
    val replacement: String,
    val source:String?
 )
{

}