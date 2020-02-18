open class Base<out T>
data class Derive<T>(var elem: T) : Base<T>()

val d: Derive<Int> = Derive(0)
val b: Base<Int> = d
val a: Base<Any> = b
if (a is Derive) {
    a.elem = "hi"
    println(a.elem)
}
println(d.elem)