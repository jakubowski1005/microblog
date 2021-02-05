import { POST_API_URL } from './constants.js';

export const sendPost = post => {
    return  fetch(POST_API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + sessionStorage.getItem('token')
        },
        body: post.content
    });
}

export const receivePosts = () => {
    return fetch(POST_API_URL, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });
}