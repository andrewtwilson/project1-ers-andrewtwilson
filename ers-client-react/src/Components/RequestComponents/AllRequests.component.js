import React, { Component } from "react";
import ReimbursementComponent from "./Reimbursement.component";

class AllRequestsComponent extends Component {
  state = {
    reimbursements: [],
    filterByStatus: ""
  };

  componentDidMount() {
    fetch("http://localhost:8080/ers/reimbursements", {
      credentials: "include"
    })
      .then(resp => {
        if (resp.status === 200) {
        } else if (resp.status === 403) {
          this.props.history.push("/home");
        }
        return resp.json();
      })
      .then(data => {
        this.setState({
          reimbursements: data
        });
      });
  }

  render() {
    return (
      <>
        <main role="main" className="col-md-9 ml-sm-auto col-lg-10 px-4">
          <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
            <h1 className="h2">All Reimbursement Requests</h1>
            <div className="btn-toolbar mb-2 mb-md-0" />
          </div>

          <form className="col-md-3">
            <div className="form-group">
              <label htmlFor="exampleFormControlSelect1">Filter By:</label>
              <select
                onChange={this.changeFilterByStatus}
                className="form-control"
                id="exampleFormControlSelect1"
              >
                <option>No Filter</option>
                <option>PENDING</option>
                <option>APPROVED</option>
                <option>DENIED</option>
              </select>
            </div>
          </form>
          <table className="table table-bordered table-hover">
            <thead className="thead-dark">
              <tr>
                <th scope="col">ID</th>
                <th scope="col">Amount</th>
                <th scope="col">Time Submitted</th>
                <th scope="col">Time Resolved</th>
                <th scope="col">Description</th>
                <th scope="col">Author ID</th>
                <th scope="col">Resolver ID</th>
                <th scope="col">Type</th>
                <th scope="col">Status</th>
                <th scope="col"></th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              {this.state.reimbursements.map(reimbursement => (
                <ReimbursementComponent
                  key={reimbursement.reimbursementId}
                  reimbursement={reimbursement}
                  reimbursementsUpdate={this.reimbursementsUpdate}
                  component="all"
                />
              ))}
            </tbody>
          </table>
        </main>
      </>
    );
  }

  reimbursementsUpdate = () => {
    fetch("http://localhost:8080/ers/reimbursements", {
      credentials: "include"
    })
      .then(resp => {
        if (resp.status === 200) {
        } else if (resp.status === 403) {
          this.props.history.push("/home");
        }
        return resp.json();
      })
      .then(data => {
        this.setState({
          reimbursements: data
        });
        this.filterReimbursements();
      });
  };

  changeFilterByStatus = event => {
    if (event.target.value !== "No Filter") {
      this.setState({
        ...this.state,
        filterByStatus: event.target.value
      });
    } else {
      this.setState({
        ...this.state,
        filterByStatus: ""
      });
    }

    this.reimbursementsUpdate();
  };

  filterReimbursements = () => {
    if (this.state.filterByStatus !== "") {
      this.setState({
        ...this.state,
        reimbursements: this.state.reimbursements.filter(
          r => r.status.status === this.state.filterByStatus
        )
      });
    }
  };
}

export default AllRequestsComponent;
