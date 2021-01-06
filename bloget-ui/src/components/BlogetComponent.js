import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import { Sticky } from 'semantic-ui-react';
//import axios from 'axios'
import HeaderComponent from "./HeaderComponent";
import PageComponent from "./PageComponent";
import LoginComponent from "./LoginComponent";
import RegisterComponent from "./RegisterComponent";
import LogoutComponent from "./LogoutComponent";
import ForgetPasswordComponent from "./ForgetPasswordComponent";
import ProfileComponent from "./ProfileComponent";
import TermsComponent from "./TermsComponent";
import ErrorComponent from "./ErrorComponent";

export default function BlogetComponent() {

    return (
        <div>
            <Router>
                <>
                    <Sticky>
                        <HeaderComponent />
                    </Sticky>
                    <Switch>
                        <Route path='/' exact component={PageComponent} />
                        <Route path='/login' component={LoginComponent} />
                        <Route path='/register' component={RegisterComponent} />
                        <Route path='/logout' component={LogoutComponent} />
                        <Route path='/forgetpassword' component={ForgetPasswordComponent} />
                        {/*<AuthenticatedRoute path='/profile' component={ProfileComponent} />*/}
                        <Route path='/profile' component={ProfileComponent} />
                        <Route path='/terms' component={TermsComponent} />
                        <Route component={ErrorComponent} />
                    </Switch>
                </>
            </Router>
        </div>
    )
}