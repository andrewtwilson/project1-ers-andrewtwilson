import React, { Component } from "react";
import ReimbursementComponent from "./Reimbursement.component";
import NewReimbursementComponent from "./NewReimbursement.component";

class MyRequestsComponent extends Component {
  state = {
    reimbursements: []
  };

  componentDidMount() {
    fetch(
      `http://localhost:8080/ers/reimbursements/user/${sessionStorage.getItem(
        "userId"
      )}`,
      {
        credentials: "include"
      }
    )
      .then(resp => {
        if (resp.status === 200) {
        } else if (resp.status === 403) {
          this.props.history.push("/home");
        }
        return resp.json();
      })
      .then(data => {
        console.log(data);
        this.setState({
          reimbursements: data
        });
      })
      .catch(err => console.log(err));
  }

  render() {
      console.log(this.state.reimbursements === []);
    if (this.state.reimbursements.length === 0) {
      return (
        <div>
          <NewReimbursementComponent
            reimbursementsUpdate={this.reimbursementsUpdate}
          />
        </div>
      );
    } else {
        return (
            <div>
              <NewReimbursementComponent
                reimbursementsUpdate={this.reimbursementsUpdate}
              />
              <table className="table table-bordered mt-1">
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
                  </tr>
                </thead>
                <tbody>
                  {this.state.reimbursements.map(reimbursement => (
                    <ReimbursementComponent
                      key={reimbursement.reimbursementId}
                      reimbursement={reimbursement}
                      component="my"
                    />
                  ))}
                </tbody>
              </table>
            </div>
          );
    }
  }

  reimbursementsUpdate = () => {
    fetch(
      `http://localhost:8080/ers/reimbursements/user/${sessionStorage.getItem(
        "userId"
      )}`,
      {
        credentials: "include"
      }
    )
      .then(resp => {
        if (resp.status === 200) {
        } else if (resp.status === 403) {
          this.props.history.push("/home");
        }
        return resp.json();
      })
      .then(data => {
        console.log(data);
        this.setState({
          reimbursements: data
        });
      })
      .catch(err => console.log(err));
  };
}

export default MyRequestsComponent;
