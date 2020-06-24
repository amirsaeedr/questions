package ans.amir

import java.util.*

fun main(args: Array<String>) {
    val testArray: IntArray
    with(Scanner(System.`in`)) {
        println("enter size of array")
        val arraySize: Int = nextInt()
        testArray = IntArray(arraySize)
        for (i in 0 until arraySize) {
            println("input next number")
            testArray[i] = nextInt()
        }
    }
    println("ans is:")
    println(findDuplicate(testArray))
}

fun findDuplicate(inputArray: IntArray): Int {
    val countArray = IntArray(inputArray.size - 1)
    for (i in inputArray) {
        countArray[i - 1]++
        if (countArray[i - 1] == 2) {
            return i
        }
    }
    return 0
}

