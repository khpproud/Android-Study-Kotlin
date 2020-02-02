package chap17.rxJava

import chap17.TempInfo
import io.reactivex.rxjava3.core.Observable
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors.toList

object TempObservable {

    fun getTemperature(town: String): Observable<TempInfo> {
        return Observable.create { emitter ->
            Observable.interval(1, TimeUnit.SECONDS).subscribe { i ->
                if (!emitter.isDisposed) {
                    if (i >= 5) {
                        emitter.onComplete()
                    } else {
                        try {
                            emitter.onNext(TempInfo.fetch(town))
                        } catch (e: Exception) {
                            emitter.onError(e)
                        }
                    }
                }
            }
        }
    }

    fun getCelsiusTemperature(town: String): Observable<TempInfo> {
        return getTemperature(town)
            .map { temp -> TempInfo(temp.town, (temp.temp - 32) * 5 / 9) }
    }

    fun getNegativeTemperature(town: String): Observable<TempInfo> {
        return getCelsiusTemperature(town)
            .filter { temp -> temp.temp < 0 }
    }

    fun getCelsiusTemperatures(vararg towns: String): Observable<TempInfo> {
        val observableList = Arrays.stream(towns)
            .map(::getCelsiusTemperature)
            .collect(toList())
        return Observable.merge(observableList)
    }
}