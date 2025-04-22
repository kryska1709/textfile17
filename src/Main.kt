//В файле содержится последовательность натуральных чисел.
// Элементы последовательности могут принимать целые значения от -100000 до 100000 включительно.
// Определите количество пар последовательности, в которых хотя бы одно число меньше
// минимального трехзначного числа делящегося на 7, а произведение чисел пары заканчивается
// на туже цифру, на которую заканчивается минимальное четырехзначное число всей последовательности.
// В ответе запишите количество найденных пар, затем максимальную из сумм элементов таких пар.
// В данной задаче под парой подразумевается два идущих подряд элемента последовательности.
import java.io.File
import kotlin.math.abs

fun main() {
    val numbers = File("17.txt").readLines().map { it.toInt() }

    val minTripleDiv7 = numbers.filter { abs(it) in 100..999 }.minByOrNull { abs(it) }!!
    val min4Digit = numbers.filter { abs(it) in 1000..9999 }.minByOrNull { abs(it) }!!
    val requiredLastDigit = abs(min4Digit) % 10
    var pairCount = 0
    var maxSum = Int.MIN_VALUE

    for (i in 0 until numbers.size - 1) {
        val a = numbers[i]
        val b = numbers[i + 1]

        val hasSmall = a < minTripleDiv7 || b < minTripleDiv7
        val productLastDigit = abs(a * b) % 10

        if (hasSmall && productLastDigit == requiredLastDigit) {
            pairCount++
            maxSum = maxOf(maxSum, a + b)
        }
    }

    println("Количество пар: $pairCount")
    println("Максимальная сумма: $maxSum")
}
