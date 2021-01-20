import React, { useState } from 'react';
import { Link, Redirect } from 'react-router-dom'
import { Container, Segment, Header, Icon, Form, Message, Loader } from 'semantic-ui-react';
import { login } from '../services/AuthService.js';

export default function LoginComponent() {

    const [usernameOrEmail, setUsernameOrEmail] = useState('')
    const [password, setPassword] = useState('')
    const [message, setMessage] = useState('')
    const [loading, setLoading] = useState(false)
    const [hasFailed, setHasFailed] = useState(false)
    const [redirect, setRedirect] = useState(false)

    const loginClicked = () => {
        setLoading(true)

        if(usernameOrEmail === '' || password === '') {
            setMessage('Fields cannot be empty')
            setHasFailed(true)
            setLoading(false)
        }
        
        login({
            usernameOrEmail: usernameOrEmail, 
            password: password
        }).then(res => {
            console.log(sessionStorage.getItem('token'));
            setLoading(false)
        }).catch(err => {
            setMessage(err.message)
            setHasFailed(true)
            setLoading(false)
        });
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
                            {redirect && <Redirect to='/' />}
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