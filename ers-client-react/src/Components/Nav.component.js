import React from "react";
import { Link } from "react-router-dom";
export class AppNav extends React.Component {
  render() {
    return (
      <nav
        id="nav-bar"
        className="navbar navbar-expand-lg fixed-top navbar-dark bg-dark p-0 shadow"
      >
        <Link to="/home" className="navbar-brand col-sm-3 col-md-2 mr-0" onClick={e => e.preventDefault()}>
          E.R.S.
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon" />
        </button>

        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item active">
              <Link to="/home" className="unset-anchor nav-link">
                Home
              </Link>
            </li>
            <li className="nav-item">
              <Link
                to="/home"
                className="unset-anchor disabled nav-link"
                onClick={e => e.preventDefault()}
              >
                Settings
              </Link>
            </li>
            <li className="nav-item">
              <Link
                to="/home"
                className="unset-anchor disabled nav-link"
                onClick={e => e.preventDefault()}
              >
                Profile
              </Link>
            </li>
            <li className="nav-item">
              <Link
                to=""
                className="unset-anchor disabled nav-link"
                onClick={e => e.preventDefault()}
              >
                Help
              </Link>
            </li>
          </ul>

          {this.signInView()}

        </div>
      </nav>
    );
  }

  signInView() {
    return (
        <div className="my-2 my-lg-0 p-2">
        <button
          className="btn btn-outline-primary my-2 my-sm-0"
        >
        {this.props.signedIn === true ? (
            <Link to="/sign-out" className="wrapped-link" id="login">
            Sign Out
            </Link>
          ) : (
            <Link to="/sign-in" className="wrapped-link" id="login">
            Sign In
            </Link>
          )}
        </button>
      </div>
    )
  }
  
}
