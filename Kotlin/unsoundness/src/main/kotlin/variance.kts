open class Base<out T>
data class Derive<T>(var elem: T) : Base<T>()

val d: Derive<Int> = Derive(0)
val b: Base<Int> = d
val ohno: Base<Any> = b
if (ohno is Derive) {
    ohno.elem = "ohno"
    println(ohno.elem) // Output "ohno"
}
println(d.elem) // java.lang.ClassCastException: class java.lang.String cannot be cast to class java.lang.Number
