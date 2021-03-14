package kr.co.seoft.ca.util

import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3

inline fun <reified P1, reified P2> zipToPair(): BiFunction<P1, P2, Pair<P1, P2>> {
    return BiFunction { t1, t2 -> Pair(t1, t2) }
}

inline fun <reified P1, reified P2, reified P3> zipToTriple(): io.reactivex.functions.Function3<P1, P2, P3, Triple<P1, P2, P3>> {
    return Function3 { t1, t2, t3 -> Triple(t1, t2, t3) }
}