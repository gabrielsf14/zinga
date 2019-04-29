package br.com.zinga.repositories

interface Repository {
    fun save()
    fun load()
    fun clear()
}