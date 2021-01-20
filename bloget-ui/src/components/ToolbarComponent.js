import React, { useState } from 'react';
import { Form, Button, TextArea, Grid, Message } from 'semantic-ui-react';
import { sendPost } from '../services/PostService.js'

export default function ToolbarComponent() {

    const [providedText, setProvidedText] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const sendMessage =  () => {
        if (sessionStorage.getItem('token') === null) {
            setErrorMessage('You have to login first.');
            return;
        }
        if (providedText === '') {
            setErrorMessage('Message cannot be empty.');
            return;
        }
        sendPost(providedText);
    }

    return (
        <>
            <Grid columns='equal'>
                <Grid.Column></Grid.Column>
                <Grid.Column width={8}>
                    { errorMessage && <Message negative>
                        <Message.Header>Error occurs</Message.Header>
                        <p>{errorMessage}</p>
                    </Message>}
                    <Form>
                        <TextArea onChange={e => setProvidedText(e.target.value)} placeholder='Write something here' />
                    </Form>
                </Grid.Column>
                <Grid.Column>
                    <Button primary onClick={sendMessage}>Send</Button>
                </Grid.Column>
            </Grid>  
        </>
    );
}