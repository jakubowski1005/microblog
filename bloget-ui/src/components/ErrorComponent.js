import React from 'react';
import { Container, Header, Icon } from 'semantic-ui-react'

export default function ErrorComponent() {

    return (
        <div>
            <Container textAlign='center' style={{fontSize: '4rem', padding: '200px 0 200px'}}>
                <Header size='huge' color='blue'>
                    <Icon name='frown outline' />
                    <p>404 Page not found</p>
                </Header>
            </Container>
        </div>
    );
}