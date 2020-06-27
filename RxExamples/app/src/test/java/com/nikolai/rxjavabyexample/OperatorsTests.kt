package com.nikolai.rxjavabyexample

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.internal.operators.observable.ObservableGroupBy
import io.reactivex.observables.GroupedObservable
import org.junit.Test
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit

class OperatorsTests {

    @Test
    fun filterTest() {
        val observable = Observable.fromArray(10, 20, 34, 45, -18, -22, 45, -100)
        observable
            .filter {
                it > 0
            }
            .subscribe {
                println(it)
            }
    }

    @Test
    fun mapTest() {
        val observable = Observable.fromArray(10, 20, 34, 45, -18, -22, 45, -100)
        observable
            .map {
                it * 10
            }
            .subscribe {
                println(it)
            }
    }

    @Test
    fun takeTest() {
        val observable = Observable.fromArray(10, 20, 34, 45, -18, -22, 45, -100)
        observable
            .take(4)
            .subscribe {
                println(it)
            }
    }

    @Test
    fun skipTest() {
        val observable = Observable.fromArray(10, 20, 34, 45, -18, -22, 45, -100)
        observable
            .skip(2)
            .subscribe {
                println(it)
            }
    }

    @Test
    fun mergeTest() {
        val observable1 = Observable.interval(1, TimeUnit.SECONDS)
            .take(2)
            .map {
                it + 1
            }
            .map {
                "Source1 : $it"
            }

        val observable2 = Observable.interval(300, TimeUnit.MILLISECONDS)
            .map {
                it * 300
            }
            .map {
                "Source2 : $it"
            }

        Observable.merge(observable1, observable2)
            .subscribe {
                println("RECEIVED :  $it")
            }

        sleep(5000)
    }

    @Test
    fun concatTest() {
        val observable1 = Observable.interval(1, TimeUnit.SECONDS)
            .take(2)
            .map {
                it + 1
            }
            .map {
                "Source1 : $it"
            }

        val observable2 = Observable.interval(300, TimeUnit.MILLISECONDS)
            .map {
                it * 300
            }
            .map {
                "Source2 : $it"
            }

        Observable.concat(observable1, observable2)
            .subscribe {
                println("RECEIVED :  $it")
            }

        sleep(5000)
    }

    @Test
    fun combineLatestTest() {
        val observable1: Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)
        val observable2: Observable<Long> = Observable.interval(300, TimeUnit.MILLISECONDS)

        Observable.combineLatest<Long, Long, String>(observable1, observable2, BiFunction { t1 : Long, t2: Long ->
            "Result $t1 and $t2"
        }).subscribe {
            println(it)
        }
        sleep(5000)
    }

    @Test
    fun zipTest() {
        val observable1: Observable<String> = Observable.just("Nikolai", "Fedor", "Ivanov", "Anna", "Ivan", "Alex")
        val observable2: Observable<String> = Observable.just("God", "Semi-God", "Manager", "Petrova")

        Observable.zip<String, String, String>(observable1, observable2, BiFunction { t1: String, t2: String ->
            "Received : $t1 is $t2"
        }).subscribe {
            println(it)
        }
    }

    @Test
    fun groupByTest() {
        val observable1: Observable<String> = Observable.just("Nikolai", "Fedor", "Ivanov", "Anna", "Ivan", "Alex")
        val observable2: Observable<GroupedObservable<Int, String>> = observable1.groupBy {
            it.length
        }

        observable2
            .flatMapSingle {
                it.toList()
            }
            .subscribe {
                println(it)
            }
    }

    @Test
    fun distinctTest() {
        val observable = Observable.fromArray(10, 20, 10, 45, -18, -22, 45, -18)
        observable
            .distinct()
            .subscribe {
                println(it)
            }
    }

    @Test
    fun distinctUntilChangeTest() {
        val observable = Observable.fromArray(10, 10, 10, 45, -18, 10, 10, -18, 10)
        observable
            .distinctUntilChanged()
            .subscribe {
                println(it)
            }
    }

    @Test
    fun bufferTest() {
        val observable1 = Observable.interval(100, TimeUnit.MILLISECONDS)
            .map {
                it * 100
            }
            .map {
                "Source1 : $it"
            }
            .take(10)

        val observable2 = Observable.interval(300, TimeUnit.MILLISECONDS)
            .map {
                it * 300
            }
            .map {
                "Source2 : $it"
            }
            .take(3)


        val observable3 = Observable.interval(2000, TimeUnit.MILLISECONDS)
            .map {
                it * 2000
            }
            .map {
                "Source3 : $it"
            }
            .take(2)

        Observable.concat(observable1, observable2, observable3)
            .buffer(2)
            .subscribe {
                println(it)
            }
        sleep(5000)
    }

    @Test
    fun throttleLastTest() {
        val observable1 = Observable.interval(100, TimeUnit.MILLISECONDS)
            .map {
                it * 100
            }
            .map {
                "Source1 : $it"
            }
            .take(10)

        val observable2 = Observable.interval(300, TimeUnit.MILLISECONDS)
            .map {
                it * 300
            }
            .map {
                "Source2 : $it"
            }
            .take(3)


        val observable3 = Observable.interval(2000, TimeUnit.MILLISECONDS)
            .map {
                it * 2000
            }
            .map {
                "Source3 : $it"
            }
            .take(2)

        Observable.concat(observable1, observable2, observable3)
            .throttleLast(1, TimeUnit.SECONDS)
            .subscribe {
                println(it)
            }
        sleep(5000)
    }
}