import React, { Component } from "react";

class HomeComponent extends Component {
  state = {};
  render() {
    return (
      <main role="main" className="col-md-9 col-lg-10 px-4">
        <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
          <h1 className="h2">Home Page</h1>
          <div className="btn-toolbar mb-2 mb-md-0" />
        </div>

        <p>Welcome to the Employee Reimbursement System!</p>
      </main>
    );
  }
}

export default HomeComponent;
