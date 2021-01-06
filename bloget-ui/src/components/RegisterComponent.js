import React, { useState } from 'react';
import { Container, Segment, Header, Icon, Form, Message, Loader } from 'semantic-ui-react';

export default function RegisterComponent() {

    const [username, setUsername] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [passwordConfirmation, setPasswordConfirmation] = useState('')
    const [errorMessage, setErrorMessage] = useState('')
    const [loading, setLoading] = useState(false)
    const [check, setCheck] = useState(false)

    const register = () => {
        setLoading(true)

        if (!check) {
            setErrorMessage('You must accept Terms and Conditions.')
            setLoading(false)
            return
        }

        if (password !== passwordConfirmation) {
            setErrorMessage('Password and password confirmation are different.')
            setLoading(false)
            return;
        }

        if (username === '' || email === '' || password === '') {
            setErrorMessage('Fields cannot be empty.')
            setLoading(false)
            return;
        }

        console.log(`Registered with data: ${username}, ${email}, ${password}`)

        // AuthService.registerUser(this.state.username, this.state.email, this.state.password)
        //     .then( () => {
        //         this.setState({
        //             username: this.state.username,
        //             email: this.state.email,
        //             password: this.state.password
        //         })
        //         this.props.history.push('/login')
        //     })
        //     .catch( (err) => {
        //         console.log(err)
        //         this.setState({
        //             errorMessage: 'Please input correct values.',
        //             loading: false
        //         })
        //     })
    }

    return (
        <Container textAlign='center' style={{minHeight: '700px', paddingTop: '100px'}}>
            <Segment textAlign='center' style={{width: '600px', paddingTop: '50px', margin: '0 250px 0'}}>
                <Icon name='user' size='huge'/>
                <Header as='h1'>Sign up</Header>
                {errorMessage !== '' && <Message error header={errorMessage}/>}
                <Form>
                    <Form.Input placeholder='Username' name='username' value={username} onChange={e => setUsername(e.target.value)}/>
                    <Form.Input placeholder='E-mail' name='email' value={email} onChange={e => setEmail(e.target.value)}/>
                    <Form.Input placeholder='Password' name='password' type='password' value={password} onChange={e => setPassword(e.target.value)}/>
                    <Form.Input placeholder='Confirm password' name = 'passwordConfirmation' type='password' value={passwordConfirmation} onChange={e => setPasswordConfirmation(e.target.value)}/>
                    <Form.Checkbox onChange={() => setCheck(!check)} label={<label>I accept the <a href='/terms'>Terms of Service</a></label>} />
                    <Form.Button color='blue' size='huge' onClick={register}>
                        {!loading && 'Submit'}
                        {loading && <Loader active inline size='tiny' />}
                    </Form.Button>
                </Form>
            </Segment>
        </Container>
    );
}