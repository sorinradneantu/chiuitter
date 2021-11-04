package ro.upt.ac.chiuitter.domain

interface ChiuitRepository {
    suspend fun getAll() : List<Chiuit>
    suspend fun addChiuit(chiuit: Chiuit)
    suspend fun removeChiuit(chiuit: Chiuit)
}