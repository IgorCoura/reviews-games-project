package br.com.imt.service

import br.com.imt.dto.CreateReviewDTO
import br.com.imt.dto.ReviewDTO
import br.com.imt.interfaces.IDaoReview
import br.com.imt.interfaces.IServiceReview
import br.com.imt.models.Review

class ReviewService(val dao: IDaoReview): IServiceReview {

    override fun insert(obj: CreateReviewDTO){
        val review = Review(0, obj.gameId, obj.userId, obj.review, obj.score, obj.date)
        dao.insert(review)
    }

    override fun update(obj: ReviewDTO){
        val review = Review(obj.id, obj.gameId, obj.userId, obj.review, obj.score, obj.date)
        dao.update(review)
    }

    override fun get(id: String): ReviewDTO{
        val review = dao.get(id.toInt())
        val dto = ReviewDTO(review.id, review.gameId, review.userId, review.review, review.score, review.date)
        return dto
    }

    override fun delete(id: String){
        dao.delete(id.toInt())
    }
}