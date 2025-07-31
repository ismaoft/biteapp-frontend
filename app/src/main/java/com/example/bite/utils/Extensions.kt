package com.example.bite.utils

fun <T> List<T>.circularList(): List<T> {
    return if (size > 2) this + this else this
}
