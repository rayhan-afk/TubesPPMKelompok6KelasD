package id.ac.unpas.tubesppmkelompok6kelasd.repositories

import com.benasher44.uuid.uuid4
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.sandwich.message
import id.ac.unpas.tubesppmkelompok6kelasd.model.Mahasiswa
import id.ac.unpas.tubesppmkelompok6kelasd.networks.MahasiswaApi
import id.ac.unpas.tubesppmkelompok6kelasd.persistences.MahasiswaDao
import javax.inject.Inject

class MahasiswaRepository @Inject constructor(
    private val api: MahasiswaApi,
    private val dao: MahasiswaDao
) : Repository {
    suspend fun loadItems(
        onSuccess: (List<Mahasiswa>) -> Unit,
        onError: (List<Mahasiswa>, String) -> Unit
    ) {
        val list: List<Mahasiswa> = dao.getList()
        api.all()
// handle the case when the API request gets a success response.
            .suspendOnSuccess {
                data.whatIfNotNull {
                    it.data?.let { list ->
                        dao.insertAll(list)
                        val items: List<Mahasiswa> = dao.getList()
                        onSuccess(items)
                    }
                }
            }
// handle the case when the API request gets an error response.
// e.g. internal server error.
            .suspendOnError {
                onError(list, message())
            }
// handle the case when the API request gets an exception response.
// e.g. network connection error.
            .suspendOnException {
                onError(list, message())
            }
    }
    suspend fun insert(
        npm: String,
        nama: String,
        tanggal_lahir: String,
        jenis_kelamin : String,
        onSuccess: (Mahasiswa) -> Unit,
        onError: (Mahasiswa?, String) -> Unit
    ) {
        val id = uuid4().toString()
        val item = Mahasiswa(id, npm, nama, tanggal_lahir, jenis_kelamin)
        dao.insertAll(item)
        api.insert(item)
// handle the case when the API request gets a success response.
            .suspendOnSuccess {
                onSuccess(item)
            }
// handle the case when the API request gets an rror response.
// e.g. internal server error.
            .suspendOnError {
                onError(item, message())
            }
// handle the case when the API request gets an xception response.
// e.g. network connection error.
            .suspendOnException {
                onError(item, message())
            }
    }
    suspend fun update(
        id : String,
        npm: String,
        nama: String,
        tanggal_lahir: String,
        jenis_kelamin : String,
        onSuccess: (Mahasiswa) -> Unit,
        onError: (Mahasiswa?, String) -> Unit
    ) {
        val item = Mahasiswa(id, npm, nama, tanggal_lahir, jenis_kelamin)
        dao.insertAll(item)
        api.update(id, item)
// handle the case when the API request gets a success response.
            .suspendOnSuccess {
                onSuccess(item)
            }
// handle the case when the API request gets an rror response.
// e.g. internal server error.
            .suspendOnError {
                onError(item, message())
            }
// handle the case when the API request gets an exception response.
// e.g. network connection error.
            .suspendOnException {
                onError(item, message())
            }
    }

    suspend fun delete(
        id: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        dao.delete(id)
        api.delete(id)
// handle the case when the API request gets a uccess response.
            .suspendOnSuccess {
                data.whatIfNotNull {
                    onSuccess()
                }
            }
// handle the case when the API request gets an error response.
// e.g. internal server error.
            .suspendOnError {
                onError(message())
            }
// handle the case when the API request gets an exception response.
// e.g. network connection error.
            .suspendOnException {
                onError(message())
            }
    }

    suspend fun find(id: String) : Mahasiswa? {
        return dao.find(id)
    }
}