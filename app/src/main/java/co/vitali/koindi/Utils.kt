package co.vitali.koindi

import co.vitali.koindi.models.Country


fun removeDuplicates(result: MutableList<Country>): MutableList<Country> {
    val set = HashSet<String>()
    val list = ArrayList<Country>()
    for (country in result) {
        val key = country.name.common
        if (set.add(key))
            list.add(country)
    }
    return list
}