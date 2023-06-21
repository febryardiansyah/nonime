package id.nonime.app.models

import com.google.gson.annotations.SerializedName

data class GenreListResponse(

	@field:SerializedName("genreList")
	val genreList: List<GenreListItem?>? = null
)

data class GenreListItem(

	@field:SerializedName("image_link")
	val imageLink: String? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("genre_name")
	val genreName: String? = null
)
