import React, { useState } from 'react';
import { Link } from 'react-router-dom'
import { Container, Segment, Header, Icon, Form, Message, Loader } from 'semantic-ui-react';

export default function LoginComponent() {

    const [usernameOrEmail, setUsernameOrEmail] = useState('')
    const [password, setPassword] = useState('')
    const [message, setMessage] = useState('')
    const [loading, setLoading] = useState(false)
    const [hasFailed, setHasFailed] = useState(false)

    const loginClicked = () => {
        setLoading(true)

        if(usernameOrEmail === '' || password === '') {
            setMessage('Fields cannot be empty')
            setHasFailed(true)
            setLoading(false)
        }
        console.log(`Logged in with username: ${usernameOrEmail} and password: ${password}`)
    }

    return (
        <div>
            <Container textAlign='center' style={{minHeight: '700px', paddingTop: '100px'}}>
                <Segment textAlign='center' style={{width: '600px', paddingTop: '50px', margin: '0 250px 0'}}>
                    <Icon name='user' size='huge'/>
                    <Header as='h1'>Sign in</Header>
                    {hasFailed && <Message error header={message} />}
                    <Form>
                        <Form.Input
                            placeholder='Username or E-mail'
                            name='usernameOrEmail'
                            value={usernameOrEmail}
                            onChange={e => setUsernameOrEmail(e.target.value)} />
                        <Form.Input
                            placeholder='Password'
                            name='password'
                            type='password'
                            value={password}
                            onChange={e => setPassword(e.target.value)} />
                        <Form.Checkbox label='Remember me' />
                        <Form.Button color='blue' size='huge' onClick={loginClicked} style={{minWidth: '140px'}}>
                            {loading && <Loader active inline size='tiny' />}
                            {!loading && 'Submit'}
                        </Form.Button>
                    </Form>
                    <div>
                        <Link to={'/forgetpassword'} style={{padding: '40px 20px 0', color: 'grey'}}>
                            Forget password
                        </Link>
                    </div>
                </Segment>
            </Container>
        </div>
    );
}