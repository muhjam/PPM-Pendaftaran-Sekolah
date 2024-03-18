package id.ac.unpas.agenda.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Register(
    @PrimaryKey
    val id: String,
    val NISN: String,
    val Nama: String,
    val Email: String,
    val Alamat: String
)
