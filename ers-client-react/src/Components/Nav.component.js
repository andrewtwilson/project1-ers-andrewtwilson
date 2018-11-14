import React from 'react';
import { Link } from 'react-router-dom';

export class AppNav extends React.Component {

    render() {
        return (
            <div>
                <nav id="nav-bar" className="navbar navbar-toggleable-md navbar-expand-lg navbar-light bg-light display-front nav-pad">
                    <div className="navbar-header c-pointer shift-left">
                    </div>

                    <div className="collapse navbar-collapse" id="navbarsExample04">
                        <ul className="navbar-nav margin-nav">
                            <li className="nav-item">
                                <Link to="/home" className="unset-anchor nav-link">Home</Link>
                            </li>

                            <li className="nav-item dropdown">
                                <div className="nav-link nav-item dropdown-toggle pointer" id="examples-dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">View Reimbursement Requests</div>
                                <div className="dropdown-menu" aria-labelledby="examples-dropdown">
                                    <div className="dropdown-item"><Link to="/my-requests" className="unset-anchor nav-link">My Requests</Link></div>
                                    <div className="dropdown-item"><Link to="/all-requests" className="unset-anchor nav-link">All Requests</Link></div>
                                </div>
                            </li>
                        </ul>
                    </div>

                    <div className="collapse navbar-collapse" id="navbarsExample04">
                        <ul className="navbar-nav ml-auto margin-nav">
                            <li className="nav-item">
                                <Link to="/sign-in" className="unset-anchor nav-link">Sign In</Link>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div >
        );
    }
}