import React, { useEffect, useState } from 'react';
import { Link, Redirect } from 'react-router-dom'
import { Container, Menu, Icon } from 'semantic-ui-react'
import { logout } from '../services/AuthService.js';

export default function HeaderComponent() {

    const [activeItem, setActiveItem] = useState('home')
    const [isLogged, setIsLogged] = useState(false)

    useEffect(() => {
        setIsLogged(sessionStorage.getItem('token') !== null)
    },[])

    const handleLogout = (event) => {
        setActiveItem('home')
        setIsLogged(false)
        logout();
        return <Redirect to='/logout' />
    }

    return (
        <Container fluid>
            <Menu pointing secondary inverted color={'blue'} size='massive'>

                <Menu.Item
                    name='home'
                    active={activeItem === 'home'}
                    onClick={() => setActiveItem('home')}
                    as={Link} to='/'
                ><Icon name='announcement' />Bloget
                </Menu.Item>

                {!isLogged && <Menu.Menu position="right" width={6}>
                    <Menu.Item
                        name='signin'
                        active={activeItem === 'signin'}
                        onClick={() => setActiveItem('signin')}
                        as={Link} to='/login'
                    >Sign In
                    </Menu.Item>
                    <Menu.Item
                        name='signup'
                        active={activeItem === 'signup'}
                        onClick={() => setActiveItem('signup')}
                        as={Link} to='/register'>Sign up
                    </Menu.Item>
                </Menu.Menu>}

                {isLogged && <Menu.Menu position="right" width={6}>
                    <Menu.Item
                        name='logout'
                        active={activeItem === 'logout'}
                        onClick={handleLogout}>Logout
                    </Menu.Item>
                </Menu.Menu>}
            </Menu>
        </Container>
    );
}