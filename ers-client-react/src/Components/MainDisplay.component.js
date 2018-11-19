import React, { Component } from "react";
import SideBarComponent from "./SideBarComponents/SideBar.component";
import { Route, Switch } from "react-router-dom";
import { SignInComponent } from "./SignIn.component";
import HomeComponent from "./Home.component";
import MyRequestsComponent from "./RequestComponents/MyRequests.component";
import AllRequestsComponent from "./RequestComponents/AllRequests.component";
import SignOutComponent from "./SignOut.component";

class MainDisplayComponent extends Component {
  state = {};
  render() {
    if (this.props.signedIn) {
      return (
        <div className="container-fluid">
          <div className="row">
            <SideBarComponent />
            
            <Switch>
              <Route
                path="/sign-in"
                render={props => (
                  <SignInComponent {...props} signIn={this.props.signIn} />
                )}
              />
              <Route
                path="/sign-out"
                render={props => (
                  <SignOutComponent {...props} signOut={this.props.signOut} />
                )}
              />
              <Route path="/home" component={HomeComponent} />
              <Route path="/my-requests" component={MyRequestsComponent} />
              <Route path="/all-requests" component={AllRequestsComponent} />
            </Switch>
          </div>
        </div>
      );
    } else {
      return (
        <>
          <Switch>
            <Route
              path="/sign-in"
              render={props => (
                <SignInComponent {...props} signIn={this.props.signIn} />
              )}
            />
            <Route
              path="/sign-out"
              render={props => (
                <SignOutComponent {...props} signOut={this.props.signOut} />
              )}
            />
            <Route path="/home" component={HomeComponent} />
            <Route path="/my-requests" component={MyRequestsComponent} />
            <Route path="/all-requests" component={AllRequestsComponent} />
          </Switch>
        </>
      );
    }
  }

  renderSidebar = () => {
    if (this.props.signedIn) {
      return <SideBarComponent />;
    }
  };
}

export default MainDisplayComponent;
