package com.geekbrains.popularlibraries.test_dagger

import dagger.Component
import dagger.Module
import dagger.Provides

// Граф зависимостей.
@Component(modules = [AppModule::class]) // Этот интрефейс является компоненом и из него можно будет получать зависимости
interface AppComponentDagger {

    // Из AppComponent будем получать комьютер
    // fun computer():Computer
    // Можно сделат и как проперти
    val computer:Computer
}

@Module // Обьект или класс, который обьявляет зависимости
object AppModule {

    // Предоставляет компьютер
    @Provides // Функция, которая что-то предаставляет
    fun provideComputer(
        processor: Processor,
        ram: RAM,
        motherboard: Motherboard
    ): Computer {
        // Создадим компьютер
        return Computer(
            processor = processor,
            ram = ram,
            motherboard = motherboard,
        )
    }

    // Предоставляет процессор
    @Provides
    fun provideProcessor():Processor{
        return Processor()
    }

    // Предоставляет ОП
    @Provides
    fun provideRam():RAM{
        return RAM()
    }

    // Предоставялет МП
    @Provides
    fun provideMotherboard():Motherboard{
        return Motherboard()
    }

}
