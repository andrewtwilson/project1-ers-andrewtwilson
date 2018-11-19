import React, { Component } from "react";
import { Link } from "react-router-dom";

class EmployeeSideBarComponent extends Component {
  state = {};
  render() {
    return (
      <nav className="col-md-2 d-none d-md-block bg-light sidebar">
        <div className="sidebar-sticky">
          <ul className="nav flex-column">
            <li className="nav-item active">
              <Link
                to="/my-requests"
                className="unset-anchor nav-link disabled"
              >
                Dashboard
              </Link>
            </li>
            <li className="nav-item">
              <Link to="/my-requests" className="unset-anchor nav-link">
                Reimbursements
              </Link>
            </li>
            <li className="nav-item">
              <Link
                to="/my-requests"
                className="unset-anchor nav-link disabled"
                onClick={e => e.preventDefault()}
              >
                Pay History
              </Link>
            </li>
          </ul>
        </div>
      </nav>
    );
  }
}

export default EmployeeSideBarComponent;
