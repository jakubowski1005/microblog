import { POST_API_URL } from './constants.js';

export const sendPost = async post => {
    const response = await fetch(POST_API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authentication': 'Bearer ' + sessionStorage.getItem('token')
        },
        body: post.content
    });
    return response.json();
}

export const receivePosts = async () => {
    const response = await fetch(POST_API_URL, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    return response.json();
}

const postslist = [
    {id: 1, owner: 'user1', content: 'post1', createdAt: new Date(2021,0,20,3,24,0).toLocaleTimeString()},
    {id: 2, owner: 'user1', content: 'post2', createdAt: new Date(2021,0,20,3,25,0).toLocaleTimeString()},
    {id: 3, owner: 'user2', content: 'post3', createdAt: new Date(2021,0,20,3,23,0).toLocaleTimeString()},
    {id: 4, owner: 'user3', content: 'post4', createdAt: new Date(2021,0,20,3,26,0).toLocaleTimeString()},
    {id: 5, owner: 'user1', content: 'post5', createdAt: new Date(2021,0,20,3,20,0).toLocaleTimeString()},
    {id: 6, owner: 'user2', content: 'post6', createdAt: new Date(2021,0,20,3,28,0).toLocaleTimeString()},
]