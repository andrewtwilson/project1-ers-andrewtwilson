import React, { Component } from 'react';
import './App.css';
import { AppNav } from './Components/Nav.component';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import { SignInComponent } from './Components/SignIn.component';
import HomeComponent from './Components/Home.component';
import MyRequestsComponent from './Components/RequestComponents/MyRequests.component';
import AllRequestsComponent from './Components/RequestComponents/AllRequests.component';
import SignOutComponent from './Components/SignOut.component';

class App extends Component {
  constructor(props) {
    super(props);

    this.state = {
      signedIn: false
    }
    // sessionStorage.setItem("signedin", false);
  }

  render() {
    return (
      <>
        <BrowserRouter>
          <>
          <AppNav signedIn={this.state.signedIn}/>
            <div id="main-content-container">
              <Switch>
                <Route path='/sign-in' render={(props) => <SignInComponent {...props} signIn={this.signIn} /> } />
                <Route path='/sign-out' render={(props) => <SignOutComponent {...props} signOut={this.signOut} /> } />
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

  signIn = () => {
    this.setState({
      signedIn: true
    })
  }

  signOut = () => {
    this.setState({
      signedIn: false
    })
  }
}

export default App;
