package id.ac.unpas.agenda.ui.screens

import ListRegisterScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.room.Room
import id.ac.unpas.agenda.persistences.AppDatabase
import androidx.compose.runtime.*
import id.ac.unpas.agenda.models.Register

@Composable
fun RegisterScreen() {
    val context = LocalContext.current

    val db = Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
    val registerDao = db.registerDao()

    var itemToEdit by remember { mutableStateOf<Register?>(null) } // set null
    Column(modifier = Modifier.padding(10.dp).fillMaxWidth()) {
        Text(text = "Pendaftaran SMA 3 Pasundan Cimahi", style = MaterialTheme.typography.titleLarge)
        FormRegisterScreen(registerDao, itemToEdit,  onEditItem = { itemToEdit = it })

        ListRegisterScreen(registerDao, onEditItem = { itemToEdit = it })
    }
}