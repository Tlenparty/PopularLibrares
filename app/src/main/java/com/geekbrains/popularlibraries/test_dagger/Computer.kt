package com.geekbrains.popularlibraries.test_dagger

//DAGGER FROM ANDROID BROAD CAST

class Processor {

    override fun toString(): String = "ABC"
}

class Motherboard(){
    override fun toString(): String = "X7 3000"
}

class RAM{
    override fun toString(): String = "16 GB"
}

data class Computer(
    val processor: Processor,
    val motherboard: Motherboard,
    val ram: RAM
)