package id.ac.unpas.agenda.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.benasher44.uuid.uuid4
import id.ac.unpas.agenda.models.Register
import id.ac.unpas.agenda.persistences.RegisterDao
import kotlinx.coroutines.launch
import androidx.compose.runtime.DisposableEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormRegisterScreen(registerDao: RegisterDao, itemToEdit: Register?, onEditItem: (Register?) -> Unit) {
    val scope = rememberCoroutineScope()

    // Menggunakan data dari itemToEdit untuk mengisi nilai awal formulir
    val NISN = remember { mutableStateOf(itemToEdit?.NISN ?: "") }
    val Nama = remember { mutableStateOf(itemToEdit?.Nama ?: "") }
    val Email = remember { mutableStateOf(itemToEdit?.Email ?: "") }
    val Alamat = remember { mutableStateOf(itemToEdit?.Alamat ?: "") }

    DisposableEffect(itemToEdit) {
        NISN.value = itemToEdit?.NISN ?: ""
        Nama.value = itemToEdit?.Nama ?: ""
        Email.value = itemToEdit?.Email ?: ""
        Alamat.value = itemToEdit?.Alamat ?: ""
        onDispose { }
    }
    val modeEdit = itemToEdit != null // Set modeEdit based on whether itemToEdit is not null

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {

        OutlinedTextField(
            label = { Text(text = "NISN") },
            modifier = Modifier.fillMaxWidth(),
            value = NISN.value,
            onValueChange = { NISN.value = it }
        )

        OutlinedTextField(
            label = { Text(text = "Nama") },
            modifier = Modifier.fillMaxWidth(),
            value = Nama.value,
            onValueChange = { Nama.value = it }
        )

        OutlinedTextField(
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth(),
            value = Email.value,
            onValueChange = { Email.value = it }
        )

        OutlinedTextField(
            label = { Text(text = "Alamat") },
            modifier = Modifier.fillMaxWidth(),
            value = Alamat.value,
            onValueChange = { Alamat.value = it }
        )

        Row {
            Button(modifier = Modifier.weight(5f), onClick = {
                val newItem = Register(
                    id = if (modeEdit) itemToEdit?.id ?: uuid4().toString() else uuid4().toString(),
                    NISN = NISN.value,
                    Nama = Nama.value,
                    Email = Email.value,
                    Alamat = Alamat.value
                )
                scope.launch {
                    registerDao.upsert(newItem)
                }
                onEditItem(null)
                NISN.value = ""
                Nama.value = ""
                Email.value = ""
                Alamat.value = ""
            }) {
                Text(text = if (modeEdit) "Ubah" else "Simpan")
            }

            Button(modifier = Modifier.weight(5f), onClick = {
                // Setel kembali nilai state TextField menjadi string kosong saat "Batal" ditekan
                onEditItem(null)
                NISN.value = ""
                Nama.value = ""
                Email.value = ""
                Alamat.value = ""
            }) {
                Text(text = "Batal")
            }
        }
    }
}

