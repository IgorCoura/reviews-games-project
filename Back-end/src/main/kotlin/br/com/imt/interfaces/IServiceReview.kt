package br.com.imt.interfaces

import br.com.imt.dto.CreateReviewDTO
import br.com.imt.dto.ReviewDTO
import br.com.imt.dto.UpdateReviewDTO
import br.com.imt.models.Review

interface IServiceReview {
    fun insert(obj: CreateReviewDTO, userId: String)
    fun update(obj: UpdateReviewDTO, userId: String)
    fun get(id: String, userId: String): ReviewDTO
    fun delete(id: String, userId: String)
}