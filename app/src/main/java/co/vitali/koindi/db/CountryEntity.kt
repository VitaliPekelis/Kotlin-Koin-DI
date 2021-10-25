package co.vitali.koindi.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.vitali.koindi.models.Country
import co.vitali.koindi.models.Flag
import co.vitali.koindi.models.Name


@Entity(tableName = "countries")
data class CountryEntity (
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "flag_icon_url") val flagUrl: String?
) {
    companion object {
        fun from (user: Country): CountryEntity {
            return CountryEntity(user.name.common, user.flags.png)
        }
    }

    fun toCountry(): Country {
        return Country(Name(name), Flag(flagUrl))
    }
}

