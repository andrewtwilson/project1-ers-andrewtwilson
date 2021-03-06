import React from "react";

export class SignInComponent extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      username: "",
      password: ""
    };
  }

  passwordChange = e => {
    this.setState({
      ...this.state,
      password: e.target.value
    });
  };

  usernameChange = e => {
    this.setState({
      ...this.state,
      username: e.target.value
    });
  };

  submit = e => {
    e.preventDefault();
    let cred = this.state;
    fetch("http://localhost:8080/ers/users/login", {
      method: "POST",
      body: JSON.stringify(cred),
      headers: {
        "Content-Type": "application/json"
      },
      credentials: "include"
    })
      .then(res => {
        if (res.status === 200) {
          this.props.history.push("/home");
        }
        return res.json();
      })
      .then(data => {
        sessionStorage.setItem("userId", data[0]);
        sessionStorage.setItem("userRole", data[1]);
        this.props.signIn();
      })
      .catch(err => {
        console.log(err);
      });
  };

  render() {
    return (
      <>
        <main role="main" className="col-md-9 col-lg-10 px-4 auto-margin">
          <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
            <h1 className="h2 auto-margin">Sign In</h1>
            <div className="btn-toolbar mb-2 mb-md-0" />
          </div>

          <form className="form-signin col-md-3 auto-margin" onSubmit={this.submit}>

            <label htmlFor="input-username" className="sr-only">
              Username
            </label>
            <input
              type="text"
              id="input-username"
              className="form-control"
              placeholder="Username"
              required
              value={this.state.username}
              onChange={this.usernameChange}
            />

            <label htmlFor="inputPassword" className="sr-only">
              Password
            </label>
            <input
              type="password"
              id="inputPassword"
              className="form-control"
              placeholder="Password"
              required
              value={this.state.password}
              onChange={this.passwordChange}
            />

            <button className="btn btn-lg btn-primary btn-block" type="submit">
              Sign in
            </button>
          </form>
        </main>
      </>
    );
  }
}
