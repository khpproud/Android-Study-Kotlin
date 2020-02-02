package chap08

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.stream.Collectors.joining

object CacheExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val lines = listOf(
            " Nel   mezzo del cammin  di nostra  vita ",
            "mi  ritrovai in una  selva oscura",
            " che la  dritta via era   smarrita "
        )

        val messageDigest = MessageDigest.getInstance("SHA-256")

        val dataToHash: MutableMap<String, ByteArray> = hashMapOf()

        lines.forEach { line -> dataToHash.computeIfAbsent(line) { key -> calculateDigest(messageDigest, key) } }
        dataToHash.forEach { line, hash -> println(String.format("%s -> %s", line,
            String(hash).chars().map { i -> i and 0xff }.mapToObj(Int::toString).collect(
                joining(", ", "[", "]")
            ) ))}
    }

    private fun calculateDigest(digest: MessageDigest, key: String): ByteArray =
        digest.digest(key.toByteArray(StandardCharsets.UTF_8))
}