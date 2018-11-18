import React, { PureComponent } from 'react';
import * as $ from 'jquery';

class ReimbursementComponent extends PureComponent {
    componentDidMount() {
        $('[data-toggle="tooltip"]').tooltip();
      }

    render() {
        const { reimbursementId, amount, submitted, resolved, description, author, resolver, status, type } = this.props.reimbursement;

        let determineColor = () => {
            if (status.status === 'PENDING') {
                return "table-warning";
            } else if (status.status === 'APPROVED') {
                return "table-success";
            } else if (status.status === 'DENIED') {
                return "table-danger";
            }
        };

        return (
            <tr className={determineColor()}>
                <th scope="row">{reimbursementId}</th>
                <td>{amount}</td>
                <td>{submitted}</td>
                <td>{(resolved !== null) ? resolved : "N/A"}</td>
                {description.length <= 20 ?
                <td> {description} </td> :
                this.descriptionSubstring(description)
                }
                <td>{author}</td>
                <td>{(resolved !== null) ? resolver : "N/A"}</td>
                <td>{type.type}</td>
                <td>{status.status}</td>
                {this.action()}
            </tr>
        );
    }

    action() {
        if (this.props.component === "all") {
            return (
                <td>
                    {
                    this.props.reimbursement.status.status === "PENDING"
                    ?
                    <div>
                        <button className="btn btn-success btn-sm" type="button" onClick={this.approve}>
                            Approve
                        </button>
                        <button className="btn btn-danger btn-sm" type="button" onClick={this.deny}>
                            Deny
                        </button>
                    </div>
                    :
                    <></>
                    }
                </td>
            )
        }
    }

    approve = () => {
        fetch(`http://localhost:8080/ers/reimbursements/${this.props.reimbursement.reimbursementId}`, {
            method: 'PATCH',
            body: JSON.stringify(2),
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        })
            .then(res => {
                if (res.status === 200) {
                    this.props.reimbursementsUpdate();
                }
            })
            .catch(err => {
                console.log(err);
            })
    }

    deny = () => {
        fetch(`http://localhost:8080/ers/reimbursements/${this.props.reimbursement.reimbursementId}`, {
            method: 'PATCH',
            body: JSON.stringify(3),
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        })
            .then(res => {
                if (res.status === 200) {
                    this.props.reimbursementsUpdate();
                }
            })
            .catch(err => {
                console.log(err);
            })
    }

    descriptionSubstring = (description) => {
        let descriptionSubstring = description.substring(0,19);

        return <td data-toggle="tooltip" data-placement="top" title={description}> {descriptionSubstring}... </td>
    }
}

export default ReimbursementComponent;