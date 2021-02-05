import React, { useEffect, useState } from 'react';
import { Card, Grid } from 'semantic-ui-react';
import { receivePosts } from '../services/PostService.js';

export default function FeedComponent() {

    const [posts, setPosts] = useState([]);

    useEffect(() => {
        receivePosts()
            .then(res => {
                console.log(res)
                res.json()
            })
            .then(data => {
                console.log('data')
                console.log(data)
                setPosts(data);
    })
            .catch(err => console.error(err));
    })

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