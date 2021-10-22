package com.geekbrains.popularlibraries.test_dagger


import android.util.Log
import dagger.Component
import dagger.Module
import dagger.Provides
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * @Inject указывает на метод, конструктор или поле класса, которые (или в которые) надо что-то внедрить.
 * @Provides указывает на метод, который предоставляет (возвращает) зависимость для дальнейшего внедрения.
 * @Module помечает класс с набором методов, которые отмечаются аннотациями @Provides.
 * @Component отмечает интерфейс, который позже используется для генерации кода. В нём определяется, куда что-либо внедрять, а также методы для прямого доступа к зависимостям. */

/*
- Помечаются поля
@Inject = "вставь сюда зависимость"
@Inject lateinit var a:Class

 - Помечаются методы
@Provides = "предоставляю зависимость"

- Помечаются классы
@Module = "несколько зависимостей, которые предоставляются через @Provider

 - Помечаются интерфейсы
@Component = "несколько модулей"


fun main() {
    // Получаем компонент
    val exampleComponent = DaggerExampleComponent.builder().buld()
    // Ссылка на ExampleClass
    val example = Example()
    exampleComponent.inject(example)
    Timber.d(example.abc)
    Timber.d(example.cde)
    //println(example.a) //Выведится abc
}


@Module
class ExampleModule {
    //Провайдс методы отдают зависимости (должен иметь тот же тип то и инъекция)
    @Provides
    @Named("String1") // Можем дать имя аннотации, помогает различить иньекции в предоставляемом классе
    fun exampleProvides(): String  = "ABC"
    @Named("String2")
    @Provides
    fun exampleProvides2(): String  = "CDE"

}
// Указываем модули
//@Singleton // Фабрика
@Component(modules = [ExampleModule::class])
interface ExampleComponent{
    // Оснавная вещь в компоненте - это создавать методы инъекции.
    // В аргументы пердаем класс который хотим вставить зависимость
    fun inject(e:Example)
}

class Example {
    @Inject
    @Named("String1")
    lateinit var abc: String  // Провести иньекцию некоторой String

    @Named("String1")
    @Inject lateinit var cde: String
}
*/


//



