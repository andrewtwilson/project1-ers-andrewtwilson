import React, { Component } from "react";
import "./App.css";
import { AppNav } from "./Components/Nav.component";
import { BrowserRouter } from "react-router-dom";
import MainDisplayComponent from "./Components/MainDisplay.component";
class App extends Component {
  constructor(props) {
    super(props);

    this.state = {
      signedIn: false
    };
  }

  render() {
    return (
      <>
        <BrowserRouter>
          <>
            <AppNav signedIn={this.state.signedIn} />

              <MainDisplayComponent signedIn={this.state.signedIn} signIn={this.signIn} signOut={this.signOut} />
              
          </>
        </BrowserRouter>
      </>
    );
  }

  signIn = () => {
    this.setState({
      signedIn: true
    });
  };

  signOut = () => {
    this.setState({
      signedIn: false
    });
  };

}

export default App;
