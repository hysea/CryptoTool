package com.hysea.cryptotool

import java.util.*


object PseudoRandomSubstitution {

    var substitutionMap: Map<Char, Char> = emptyMap()
    var reverseSubstitutionMap: Map<Char, Char> = emptyMap()

    // 生成替换表
    fun generateSubstitutionMap(seed: Int): Map<Char, Char> {
        val random = Random(seed.toLong())
        val map: MutableMap<Char, Char> = HashMap()
        val charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()

        // 创建一个字符数组并打乱顺序
        val shuffled = charset.clone()
        for (i in shuffled.indices) {
            val randomIndex = random.nextInt(shuffled.size)
            val temp = shuffled[i]
            shuffled[i] = shuffled[randomIndex]
            shuffled[randomIndex] = temp
        }

        // 生成替换表
        for (i in charset.indices) {
            map[charset[i]] = shuffled[i]
        }
        substitutionMap = map
        return map
    }

    // 生成反向替换表
    fun generateReverseSubstitutionMap(substitutionMap: Map<Char, Char>): Map<Char, Char> {
        val reverseMap: MutableMap<Char, Char> = HashMap()
        for ((key, value) in substitutionMap) {
            reverseMap[value] = key
        }
        reverseSubstitutionMap = reverseMap
        return reverseMap
    }

    // 通过字符替换来混淆或还原字符串
    fun substitute(input: String, map: Map<Char, Char>): String {
        val inputChars = input.toCharArray()
        val result = CharArray(input.length)

        for (i in inputChars.indices) {
            result[i] = map.getOrDefault(inputChars[i], inputChars[i])
        }

        return String(result)
    }
}
