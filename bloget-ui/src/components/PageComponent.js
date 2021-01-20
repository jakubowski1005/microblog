import React from 'react';
import FeedComponent from './FeedComponent.js';
import ToolbarComponent from './ToolbarComponent.js';
import { Segment } from 'semantic-ui-react';

export default function PageComponent() {
    return (
        <>
            <Segment>
                <ToolbarComponent />
            </Segment>
            <Segment>
                <FeedComponent />
            </Segment>
        </>
    );
}