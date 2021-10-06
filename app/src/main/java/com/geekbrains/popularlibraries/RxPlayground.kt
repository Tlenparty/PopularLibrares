package com.geekbrains.popularlibraries

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random

/**
 * Источники:
 * Single -  передает item(один) | error
 * Maybe - передает item  or void | complete | error Maybe подходит, если нас устраивает как наличие значения,
 * так и его отсутствие.
 * Completable - передает complete | error Источник подходит, когда получать значения не требуется,
 * а нас интересует сам факт завершения какой-либо операции, например, запись в файл или базу данных.
 * 1.Пораждающие (создание цепочки)
 * just, fromIterable (из List пораждает), fromCollable, create (для оборачивания асинхронного кода):
 * Observable.create(e -> {
    emitter.onNext("Hello");
    emitter.onComplete()
});
2. Модификаторы сигнала
  map - преобразует элементы цепочки согласно переданной лямбде
  flatMаp - похож на map, также применяет функцию к каждому излучаемому элементу, но эта функция
возвращает Obsevable. То есть один излучаемый элемент исходного источника через flatMap порождает другие.
 concatMap - модифицирует цепочку обрабатывает каждый сигнал и позволяет модифицировать ПОСЛЕДОВАТЕЛЬНО
,filter() - фильтрует.
 flatMap()  - обрабатывает каждый сигнал на отдельном потоке независимо и возвращает результат асинхронно
 switchMap() отписывается от upStream перестает слушать upStream  и дальше обрабатывает код внутри switchMap
 take() - говорит сколько сигналов(слов) взять из потока
 throttle() - дроссель , debounce()  - время в течсении которого будут браться слвоа из потока
disting() - уберает дубликаты

 fun nonRx():String = "kek"
 .fromCallable(::nonRx) можем взять функцию и работать как с переменной и модем вызвать ч/з invoke
 fun() - оператор

 При subscribe мы взвращаем disposable обьекты - при которых можно отменить(прервать) цепочку
 private val disposables = CompositeDisposable()
  В onDestroy(в moxyPresenter) - disposable.dispose()  // Для отписки от событий в реактивном потоке
 * */


const val article = """Реактивное программирование обеспечивает доступ к асинхронному программированию. Оно используется, чтобы упростить асинхронную обработку длительных операций. Именно это программирование — способ обработки нескольких событий, ошибок и завершения потока событий. Такой тип программирования обеспечивает также упрощённый способ запуска различных задач в разных потоках."""
val random = Random(System.currentTimeMillis())

@SuppressLint("CheckResult")
fun main() {
    Observable
        .just(article)
        .map { text -> text.split(" ") }
        .concatMap { words -> Observable.fromIterable(words) }
        .map(String::lowercase)
        .flatMap(::clearAllNonLetterChars)
        .subscribeOn(Schedulers.computation()) // рабоатет на UpStream
        .subscribe(::onWord,::onError ) //::onWord -  ссылка на фу-ю верхнего уровня


    Thread.sleep(15000)
}

fun clearAllNonLetterChars(word: String): Observable<String> =
    Observable.fromIterable(word.asIterable())
        .filter(Char::isLetter) // филтрует
        .observeOn(Schedulers.newThread()) // работает на downStream на том планировщие что указан
        .reduce(StringBuilder(), // Обратно собирает
            { stringBuilder: StringBuilder, char: Char -> stringBuilder.append(char) })
        .map(StringBuilder::toString)
        .delay(random.nextLong(500), TimeUnit.MILLISECONDS, Schedulers.io())
        .toObservable()

fun onWord(word: String) = println(word)
fun onError(error: Throwable) = print(error.localizedMessage)