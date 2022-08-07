import React from 'react'
import './comment_card.css'
import { useNavigate } from "react-router-dom";
import { Icon } from '@iconify/react';
import { Rating } from '@mui/material';

function CommentCard({comment}) {
    let navigate = useNavigate();
    function handleClick() {
      navigate('/login')
    }

    return (
        <div id="comment_card">
            <div id="comment_title">{comment.title}</div>
            <div id="commenter">{comment.username}</div>
            <div id="rating">
                <span className="rating_num">{comment.rating.toFixed(1)}</span>
                <Rating name="read-only" size="medium" value={comment.rating} precision={0.5} readOnly />
            </div>
            <div id="comment_content">{comment.content}</div>
        </div>
    )
}

export default CommentCard