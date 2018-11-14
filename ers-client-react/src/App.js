import React, { Component } from 'react';
import './App.css';
import { AppNav } from './Components/Nav.component';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import { SignInComponent } from './Components/SignIn.component';
import HomeComponent from './Components/Home.component';
import MyRequestsComponent from './Components/MyRequests.component';
import AllRequestsComponent from './Components/AllRequests.component';

class App extends Component {
  render() {
    return (
      <>
        <BrowserRouter>
          <>
            <AppNav />
            <div id="main-content-container">
              <Switch>
                <Route path='/sign-in' component={SignInComponent} />
                <Route path='/home' component={HomeComponent} />
                <Route path='/my-requests' component={MyRequestsComponent} />
                <Route path='/all-requests' component={AllRequestsComponent} />
              </Switch>
            </div>
          </>
        </BrowserRouter>
      </>
    );
  }
}

export default App;
