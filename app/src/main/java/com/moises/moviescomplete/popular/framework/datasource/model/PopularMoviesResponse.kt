package com.moises.moviescomplete.popular.framework.datasource.model

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<PopularMovie>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)