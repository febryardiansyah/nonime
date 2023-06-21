package id.nonime.app.models

data class OnGoingAnimeModel(
    val id: String,
    val title: String,
    val thumb: String,
    val episode: String,
    val uploadedOn: String? = null,
    val updatedOn: String? = null,
    val link: String? = null,
)
