package com.example.internz.data.comment

import org.w3c.dom.Comment

class CommentDataTemporal {
    fun getCommentData() : List<CommentData> {
        return listOf(
            CommentData(
                nickname = "하은하은",
                comments = "좋은 글 잘 보고갑니다. 정말 유용했어요!",
                date = "01.03",
                time = "14:18"
            ),
            CommentData(
                nickname = "소미닝",
                comments = "좋은 글 잘 보고갑니다. 정말 유용했어요!",
                date = "01.03",
                time = "14:18"
            ),
            CommentData(
                nickname = "소미닝",
                comments = "좋은 글 잘 보고갑니다. 정말 유용했어요!",
                date = "01.03",
                time = "14:18"
            )
        )
    }
}