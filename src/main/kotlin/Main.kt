import kotlinx.serialization.json.*
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.URL

fun main() {
    var data = getData()
    val text = File("task/replacement.json").readText()
    val parsed = Json.parseToJsonElement(text)
    val replaced: MutableList<Replacement> = mutableListOf()
    for (item in parsed.jsonArray){
        replaced += Json.decodeFromJsonElement<Replacement>(item)
    }
    for (item in replaced.reversed()){
        data= data?.replace(item.replacement,item.source?:"")

    }

    val file = File("output/result.json")
    if (data != null) {
        file.writeText(data)
    }

}

private fun getData(): String? {
    val url = URL("https://raw.githubusercontent.com/thewhitesoft/student-2023-assignment/main/data.json")
    val connection = url.openConnection()
    BufferedReader(InputStreamReader(connection.getInputStream())).use { inp ->
        var line: String?
        var data:String? =""
        while (inp.readLine().also { line = it } != null) {
             data += "${line}\n"
        }
        return data
    }
}
