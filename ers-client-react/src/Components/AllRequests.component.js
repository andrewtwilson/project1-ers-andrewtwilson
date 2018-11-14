import React, { Component } from 'react';
import ReimbursementComponent from './Reimbursement.component';

class AllRequestsComponent extends Component {
    state = {
        reimbursements: []
    }

    componentDidMount() {
        fetch('http://localhost:8080/ers/reimbursements', {
          credentials: 'include'
        })
          .then(resp => resp.json())
          .then(data => {
            this.setState({
              reimbursements: data
            })
          });
      }

    render() {
        return (
            <div>
                <table className="table table-bordered table-hover">
                    <thead className="thead-dark">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Amount</th>
                            <th scope="col">Time Submitted</th>
                            <th scope="col">Time Resolved</th>
                            <th scope="col">Description</th>
                            <th scope="col">Author ID</th>
                            <th scope="col">Resolver</th>
                            <th scope="col">Status</th>
                            <th scope="col">Type</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.reimbursements.map(reimbursement =>
                            <ReimbursementComponent
                                key={reimbursement.reimbursementId}
                                reimbursement={reimbursement} />
                        )}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default AllRequestsComponent;