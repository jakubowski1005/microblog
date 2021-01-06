import React, { useState, useEffect } from 'react';
import { Segment, Grid, Button, Search, Divider } from 'semantic-ui-react';

export default function ToolbarComponent() {

    let name = 'toolbar'

    return (
        <Segment placeholder>
            <Grid columns={2} stackable textAlign='center'>
                <Divider vertical>Or</Divider>

                <Grid.Row verticalAlign='middle'>
                    <Grid.Column>
                        <Search placeholder='Search countries...' />
                    </Grid.Column>

                    <Grid.Column>
                        <Button primary>Create</Button>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </Segment>
    );
}