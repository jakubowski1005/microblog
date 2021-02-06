import React, { useEffect, useState } from 'react';
import { Card, Grid } from 'semantic-ui-react';
import { receivePosts } from '../services/PostService.js';

export default function FeedComponent() {

    const [posts, setPosts] = useState([]);

    const mockPosts = [
        {
            id: 1,
            content: "Hello world 1",
            owner: "Artur Jakubowski",
            createdAt: new Date().toDateString()
        },
        {
            id: 2,
            content: "Hello world 2",
            owner: "Artur Jakubowski",
            createdAt: new Date().toDateString()
        },
        {
            id: 3,
            content: "Hello world 3",
            owner: "Artur Jakubowski",
            createdAt: new Date().toDateString()
        },
        {
            id: 4,
            content: "Hello world 4",
            owner: "Artur Jakubowski",
            createdAt: new Date().toDateString()
        },
        {
            id: 5,
            content: "Hello world 5",
            owner: "Artur Jakubowski",
            createdAt: new Date().toDateString()
        },
        {
            id: 6,
            content: "Hello world 6",
            owner: "Artur Jakubowski",
            createdAt: new Date().toDateString()
        },
        {
            id: 7,
            content: "Hello world 7",
            owner: "Artur Jakubowski",
            createdAt: new Date().toDateString()
        }
    ]

    useEffect(() => {
        setPosts(mockPosts);
        console.log(mockPosts);
    //     receivePosts()
    //         .then(res => {
    //             console.log(res)
    //             res.json()
    //         })
    //         .then(data => {
    //             console.log('data')
    //             console.log(data)
    //             setPosts(data);
    // })
    //         .catch(err => console.error(err));
    }, [])

    return (
        <>
        { posts.sort((a, b) => b.createdAt.localeCompare(a.createdAt)).map(post =>
            <Grid columns='equal' key={post.id}>
                <Grid.Column></Grid.Column>
                <Grid.Column width={8}>
                    <Card fluid header={post.owner} meta={post.createdAt} description={post.content} />
                </Grid.Column>
                <Grid.Column></Grid.Column>
            </Grid>  
        )}
        </>
    );
}