package co.vitali.koindi.models

open class Country (val name: Name, val flags: Flag)
data class Flag(val png: String?)
data class Name(val common: String)