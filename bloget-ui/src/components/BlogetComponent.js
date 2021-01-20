import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import { Sticky } from 'semantic-ui-react';
import HeaderComponent from "./HeaderComponent";
import PageComponent from "./PageComponent";
import LoginComponent from "./LoginComponent";
import RegisterComponent from "./RegisterComponent";
import ForgetPasswordComponent from "./ForgetPasswordComponent";
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
                        <Route path='/forgetpassword' component={ForgetPasswordComponent} />
                        <Route path='/terms' component={TermsComponent} />
                        <Route component={ErrorComponent} />
                    </Switch>
                </>
            </Router>
        </div>
    )
}