package id.nonime.app.models

import com.google.gson.annotations.SerializedName

data class DetailAnimeModel(

	@field:SerializedName("studio")
	val studio: String? = null,

	@field:SerializedName("genre_list")
	val genreList: List<GenreModel?>? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("synopsis")
	val synopsis: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("japanase")
	val japanese: String? = null,

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("score")
	val score: Double? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("episode_list")
	val episodeList: List<EpisodeListItem?>? = null,

	@field:SerializedName("producer")
	val producer: String? = null,

	@field:SerializedName("total_episode")
	val totalEpisode: Int? = null,

	@field:SerializedName("anime_id")
	val animeId: String? = null,

	@field:SerializedName("batch_link")
	val batchLink: BatchLink? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class EpisodeListItem(

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("uploaded_on")
	val uploadedOn: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class BatchLink(

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
