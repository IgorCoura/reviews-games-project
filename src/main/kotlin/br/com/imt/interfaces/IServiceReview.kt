package br.com.imt.interfaces

import br.com.imt.dto.CreateReviewDTO
import br.com.imt.dto.ReviewDTO
import br.com.imt.models.Review

interface IServiceReview {
    fun insert(obj: CreateReviewDTO)
    fun update(obj: ReviewDTO)
    fun get(id: String): ReviewDTO
    fun delete(id: String)
}