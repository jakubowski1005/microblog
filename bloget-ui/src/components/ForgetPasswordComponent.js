import React, { useState } from 'react';
import { Container, Segment, Header, Icon, Form, Message } from 'semantic-ui-react';

export default function ForgetPasswordComponent() {

    const [email, setEmail] = useState('')
    const [message, setMessage] = useState(false)

    return (
        <Container textAlign='center' style={{minHeight: '700px', paddingTop: '100px'}}>
            <Segment textAlign='center' style={{width: '600px', paddingTop: '50px', margin: '0 250px 0'}}>
                <Header as='h1'><Icon name='key' />Forget password</Header>
                {message && <Message error header='This feature does not work yet. Please create new account.' />}
                <Form>
                    <Form.Input placeholder='Enter your e-mail' name='email' value={email} onChange={e => setEmail(e.target.value)} />
                    <Form.Button color='blue' size='huge' onClick={() => setMessage(true)}>Submit</Form.Button>
                </Form>
            </Segment>
        </Container>
    );
}