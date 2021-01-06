import React, { useState } from 'react';
import { Link, Redirect } from 'react-router-dom'
import { Container, Menu, Icon, Dropdown } from 'semantic-ui-react'

export default function HeaderComponent() {

    const [activeItem, setActiveItem] = useState('home')
    const [isLogged, setIsLogged] = useState(false)

    const handleLogout = (event) => {
        setActiveItem('home')
        setIsLogged(false)
        //AuthService.logout()
        return <Redirect to='/logout' />
    }

    return (
        <Container fluid>
            <Menu pointing secondary inverted color={'blue'} size='massive'>

                <Menu.Item
                    name='home'
                    active={activeItem === 'home'}
                    onClick={e => setActiveItem('home')}
                    as={Link} to='/'
                ><Icon name='announcement' />Bloget
                </Menu.Item>

                {!isLogged && <Menu.Menu position="right" width={6}>
                    <Menu.Item
                        name='signin'
                        active={activeItem === 'signin'}
                        onClick={e => setActiveItem('signin')}
                        as={Link} to='/login'
                    >Sign In
                    </Menu.Item>
                    <Menu.Item
                        name='signup'
                        active={activeItem === 'signup'}
                        onClick={e => setActiveItem('signup')}
                        as={Link} to='/register'>Sign up
                    </Menu.Item>
                </Menu.Menu>}

                {isLogged && <Menu.Menu position="right" width={6}>
                    <Dropdown item trigger={<span><Icon name='user' /> Profile</span>}>
                        <Dropdown.Menu>
                            <Dropdown.Item as={Link} to='/lists'><Icon name='list ul' /> Lists</Dropdown.Item>
                            <Dropdown.Item as={Link} to='/profile'><Icon name='user circle' /> Profile</Dropdown.Item>
                            <Dropdown.Item as={Link} to='/settings'><Icon name='setting' /> Settings</Dropdown.Item>
                        </Dropdown.Menu>
                    </Dropdown>
                    <Menu.Item
                        name='profile'
                        active={activeItem === 'profile'}
                        onClick={e => setActiveItem('profile')}
                        as={Link} to='/profile'>Profile
                    </Menu.Item>
                    <Menu.Item
                        name='logout'
                        active={activeItem === 'logout'}
                        onClick={handleLogout}
                        as={Link} to='/logout'>Logout
                    </Menu.Item>
                </Menu.Menu>}
            </Menu>
        </Container>
    );
}