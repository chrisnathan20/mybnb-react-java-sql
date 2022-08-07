import React , { useEffect, useState } from "react";
import CommentCard from "./comment_card";
import './comments.css'

function Comment() {
    const [comments, setComments] = useState([]);

    useEffect(() => {
        // this is where we should send the fetch request, sample code below
        /*fetch("http://localhost:5000/fetch-service-providers", {credentials: 'include'}).then(response =>
          response.json().then(data => {
            setAllSp(data);
          })
        );*/
        setComments([
            {
                "username": "renterA",
                "title": "Best Place Ever",
                "rating": 4.6,
                "content": "Extremely luxurious bed with a great view of the oceanside, amazing hosts that make you feel at home"
            },
            {
                "username": "renterA",
                "title": "Best Place Ever",
                "rating": 4.6,
                "content": "Extremely luxurious bed with a great view of the oceanside, amazing hosts that make you feel at home"
            },
            {
                "username": "renterA",
                "title": "Best Place Ever",
                "rating": 4.6,
                "content": "Extremely luxurious bed with a great view of the oceanside, amazing hosts that make you feel at home"
            },
            {
                "username": "renterA",
                "title": "Best Place Ever",
                "rating": 4.6,
                "content": "Extremely luxurious bed with a great view of the oceanside, amazing hosts that make you feel at home"
            },
        ]
        )
    }, []);

    return (
        <body id="comments">
            <div id="special_prices_heading">Comments</div>
            <div id="all_special_prices">
                <ul>
                    {comments.map(comment => (
                    <CommentCard comment={comment}></CommentCard>
                    ))}
                </ul>
            </div>
        </body>
    )
}

export default Comment